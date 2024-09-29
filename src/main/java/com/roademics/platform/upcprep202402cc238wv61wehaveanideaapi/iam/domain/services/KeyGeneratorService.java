package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.services;

import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;

@Service
public class KeyGeneratorService {
    public String generateKey() {
        Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256); // or HS384, HS512
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
}