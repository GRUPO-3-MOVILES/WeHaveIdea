package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands;

import java.util.List;

public record UpdatePathFinderCommand(String pathFinderId, List<String> skills, Integer experienceInYears, String currentJobTitle, boolean availability, List<String> languages) {
    public UpdatePathFinderCommand {
        if (skills == null) {
            throw new IllegalArgumentException("Skills cannot be null");
        }
        if (experienceInYears == null) {
            throw new IllegalArgumentException("ExperienceInYears cannot be null");
        }
        if (currentJobTitle == null) {
            throw new IllegalArgumentException("CurrentJobTitle cannot be null");
        }
        if (languages == null) {
            throw new IllegalArgumentException("Languages cannot be null");
        }
    }
}

