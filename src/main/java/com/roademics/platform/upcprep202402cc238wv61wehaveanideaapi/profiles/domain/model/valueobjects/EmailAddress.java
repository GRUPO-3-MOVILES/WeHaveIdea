package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.valueobjects;

import jakarta.validation.constraints.Email;

public record EmailAddress(@Email String address) {
    public EmailAddress() {
        this(null);
    }
}

