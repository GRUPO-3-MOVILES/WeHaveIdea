package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.tokens.jwt;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.application.outboundservices.tokens.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface BearerTokenService extends TokenService {
    String getBearerTokenFrom(HttpServletRequest request);
    String generateToken(Authentication authentication);
}
