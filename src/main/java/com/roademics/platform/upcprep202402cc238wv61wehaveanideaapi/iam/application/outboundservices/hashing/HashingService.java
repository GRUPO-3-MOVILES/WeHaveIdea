package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.application.outboundservices.hashing;

public interface HashingService {
    String encode(CharSequence rawPassword);
    boolean matches(CharSequence rawPassword, String encodedPassword);
}
