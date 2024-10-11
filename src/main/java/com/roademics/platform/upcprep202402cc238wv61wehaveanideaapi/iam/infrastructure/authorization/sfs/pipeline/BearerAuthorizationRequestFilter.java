package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.authorization.sfs.pipeline;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.authorization.sfs.model.UsernamePasswordAuthenticationTokenBuilder;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.tokens.jwt.BearerTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class BearerAuthorizationRequestFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(BearerAuthorizationRequestFilter.class);

    private final BearerTokenService tokenService;

    @Qualifier("defaultUserDetailsService")
    private final UserDetailsService userDetailsService;

    public BearerAuthorizationRequestFilter(BearerTokenService bearerTokenService, UserDetailsService userDetailsService) {
        this.tokenService = bearerTokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = tokenService.getBearerTokenFrom(request);

        if (token != null) {
            LOGGER.info("Processing token: {}", maskToken(token));  // Máscara el token para los logs

            try {
                if (tokenService.validateToken(token)) {
                    // Token válido, procesar autenticación
                    String username = tokenService.getUsernameFromToken(token);
                    var userDetails = userDetailsService.loadUserByUsername(username);

                    // Establecer la autenticación en el contexto de seguridad
                    SecurityContextHolder.getContext()
                            .setAuthentication(UsernamePasswordAuthenticationTokenBuilder.build(userDetails, request));

                    LOGGER.info("Token is valid, user {} authenticated", username);

                } else {
                    LOGGER.warn("Token is invalid");
                }
            } catch (Exception e) {
                LOGGER.error("Error setting user authentication: {}", e.getMessage());
            }
        } else {
            LOGGER.warn("No Bearer Token found in request");
        }

        // Continuar con el filtro
        filterChain.doFilter(request, response);
    }

    // Metodo para enmascarar el token en los logs
    private String maskToken(String token) {
        if (token.length() > 10) {
            return token.substring(0, 6) + "...";
        }
        return token;
    }
}
