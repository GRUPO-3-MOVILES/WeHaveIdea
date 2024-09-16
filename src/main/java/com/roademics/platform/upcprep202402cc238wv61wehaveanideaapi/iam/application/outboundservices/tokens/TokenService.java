package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.application.outboundservices.tokens;

public interface TokenService {
    String generateToken(String username);
    boolean validateToken(String token);
    String getUsernameFromToken(String token);
}

