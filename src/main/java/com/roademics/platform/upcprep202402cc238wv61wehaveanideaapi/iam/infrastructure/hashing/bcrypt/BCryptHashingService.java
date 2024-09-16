package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.hashing.bcrypt;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.application.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}