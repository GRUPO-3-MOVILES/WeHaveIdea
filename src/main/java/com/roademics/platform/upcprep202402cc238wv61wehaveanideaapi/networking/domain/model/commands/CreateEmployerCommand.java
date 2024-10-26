package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands;

import java.util.List;

public record CreateEmployerCommand(String companyName, String industry, String profileId, List<String> preferredSkills, List<String> preferredLocations) {
    public CreateEmployerCommand {
        if (companyName == null || companyName.isBlank()) {
            throw new IllegalArgumentException("Company name cannot be null or empty");
        }
        if (industry == null || industry.isBlank()) {
            throw new IllegalArgumentException("Industry cannot be null or empty");
        }
        if (profileId == null || profileId.isBlank()) {
            throw new IllegalArgumentException("Profile ID cannot be null or empty");
        }
        if (preferredSkills == null || preferredSkills.isEmpty()) {
            throw new IllegalArgumentException("Preferred skills cannot be null or empty");
        }
        if (preferredLocations == null || preferredLocations.isEmpty()) {
            throw new IllegalArgumentException("Preferred locations cannot be null or empty");
        }
    }
}