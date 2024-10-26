package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.valueobjects;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.shared.domain.model.service.config.SecureIdGenerator;

import java.util.Objects;

public record ProfileId(String profileId) {

    public ProfileId() {
        this(new SecureIdGenerator().generateRandomString());
    }

    public ProfileId {
        if (Objects.equals(profileId, "") || profileId == null) {
            throw new IllegalArgumentException("Profile profileId cannot be null or blank (Profile may not exist)");
        }
    }
}
