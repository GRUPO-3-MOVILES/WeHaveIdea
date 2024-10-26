package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.authorization.sfs.pipeline;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.authorization.sfs.model.UsernamePasswordAuthenticationTokenBuilder;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.tokens.jwt.BearerTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Bearer Authorization Request Filter.
 * <p>
 * Este filtro es responsable de filtrar las solicitudes y establecer la autenticación del usuario.
 * Extiende la clase OncePerRequestFilter.
 * </p>
 * @see OncePerRequestFilter
 */
public class BearerAuthorizationRequestFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BearerAuthorizationRequestFilter.class);
    private final BearerTokenService tokenService;

    @Qualifier("defaultUserDetailsService")
    private final UserDetailsService userDetailsService;

    public BearerAuthorizationRequestFilter(BearerTokenService tokenService, UserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Este metodo es responsable de filtrar las solicitudes y establecer la autenticación del usuario.
     * @param request El objeto de solicitud.
     * @param response El objeto de respuesta.
     * @param filterChain La cadena de filtros.
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String requestURI = request.getRequestURI();

            // Excluir todas las rutas de Swagger y recursos estáticos
            if (isPublicPath(requestURI)) {
                filterChain.doFilter(request, response);
                return;
            }

            // Obtener el token del encabezado Authorization
            String token = tokenService.getBearerTokenFrom(request);

            // Si el token es nulo, solo registrar "Token: null"
            if (token == null) {
                LOGGER.info("Token: null");
            } else if (!tokenService.validateToken(token)) {
                // Si el token está presente pero no es válido, registrar "Token is not valid"
                LOGGER.info("Token is not valid");
            } else {
                // Si el token es válido, establecer la autenticación
                String username = tokenService.getUsernameFromToken(token);
                var userDetails = userDetailsService.loadUserByUsername(username);
                SecurityContextHolder.getContext().setAuthentication(
                        UsernamePasswordAuthenticationTokenBuilder.build(userDetails, request));
            }

        } catch (Exception e) {
            LOGGER.error("Cannot set user authentication: {}", e.getMessage());
        }
        // Continuar con el siguiente filtro en la cadena
        filterChain.doFilter(request, response);
    }

    private boolean isPublicPath(String requestURI) {
        return requestURI.startsWith("/swagger-ui/") ||
                requestURI.startsWith("/v3/api-docs") ||
                requestURI.equals("/api/authentication/sign-in") ||
                requestURI.equals("/api/authentication/sign-up") ||
                requestURI.endsWith(".html") ||
                requestURI.endsWith(".js") ||
                requestURI.endsWith(".css") ||
                requestURI.endsWith(".png") ||
                requestURI.endsWith(".jpg") ||
                requestURI.endsWith(".jpeg") ||
                requestURI.endsWith(".gif") ||
                requestURI.endsWith(".svg") ||
                requestURI.endsWith(".woff2");
    }

}
