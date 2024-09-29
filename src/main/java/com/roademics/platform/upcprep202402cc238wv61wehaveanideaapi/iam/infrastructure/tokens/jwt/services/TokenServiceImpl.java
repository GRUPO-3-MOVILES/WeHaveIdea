package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.tokens.jwt.services;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.services.KeyGeneratorService;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.tokens.jwt.BearerTokenService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
public class TokenServiceImpl implements BearerTokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final int TOKEN_BEGIN_INDEX = 7;

    private final SecretKey key;

    @Value("${authorization.jwt.expiration.days}")
    private int expirationDays;

    public TokenServiceImpl(KeyGeneratorService keyGeneratorService) {
        // Generamos la clave secreta usando Base64 decodificada
        String secretKeyString = keyGeneratorService.generateKey();
        this.key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKeyString));
        // Log the generated key
        LOGGER.info("Generated Key: {}", secretKeyString);

    }

    /**
     * Extrae el token Bearer del encabezado HTTP Authorization
     */
    @Override
    public String getBearerTokenFrom(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        LOGGER.info("Authorization Header: {}", authHeader);
        if (isTokenPresent(authHeader) && isBearerToken(authHeader)) {
            return extractToken(authHeader);
        }
        return null;
    }


    /**
     * Genera un JWT basado en la autenticación del usuario
     */
    @Override
    public String generateToken(Authentication authentication) {
        return buildToken(authentication.getName());
    }

    /**
     * Genera un JWT basado en el nombre de usuario
     */
    @Override
    public String generateToken(String username) {
        return buildToken(username);
    }

    /**
     * Valida el JWT y verifica si es legítimo
     */
    @Override
    public boolean validateToken(String token) {
        LOGGER.info("Validating Token: {}", token);
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            Date expiration = claims.getBody().getExpiration();
            LOGGER.info("Token Expiration: {}", expiration);
            return true;
        } catch (ExpiredJwtException e) {
            LOGGER.error("Expired JWT Token: {}", e.getMessage());
        } catch (JwtException e) {
            LOGGER.error("Invalid JSON Web Token: {}", e.getMessage());
        }
        return false;
    }



    /**
     * Extrae el nombre de usuario (subject) del token JWT
     */
    @Override
    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Métodos Privados

    /**
     * Construye un JWT con los parámetros por defecto.
     */
    private String buildToken(String username) {
        Date issuedAt = new Date();
        Date expiration = new Date(issuedAt.getTime() + expirationDays * 86400000L);  // Expira en "expirationDays"
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(key)  // Firma con la clave secreta
                .compact();
    }

    /**
     * Extrae todas las "claims" del token JWT.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Extrae una "claim" específica del token JWT.
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Verifica si el encabezado de autorización contiene un token.
     */
    private boolean isTokenPresent(String authHeader) {
        return StringUtils.hasText(authHeader);
    }

    /**
     * Verifica si el token presente en el encabezado tiene el prefijo "Bearer".
     */
    private boolean isBearerToken(String authHeader) {
        return authHeader.startsWith(BEARER_PREFIX);
    }

    /**
     * Extrae el token del encabezado de autorización, omitiendo el prefijo "Bearer".
     */
    private String extractToken(String authHeader) {
        return authHeader.substring(TOKEN_BEGIN_INDEX);
    }
}
