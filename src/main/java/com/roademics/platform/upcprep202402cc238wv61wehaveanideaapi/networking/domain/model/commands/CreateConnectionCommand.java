package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands;

import java.util.Objects;

public record CreateConnectionCommand(String firstProfileId, String secondProfileId, String connectionStatus) {
    public CreateConnectionCommand {
        if (Objects.equals(firstProfileId, "0") || firstProfileId.isBlank()) {
            throw new IllegalArgumentException("firstProfileId cannot be null or empty");
        }
        if (Objects.equals(secondProfileId, "0") || secondProfileId.isBlank()) {
            throw new IllegalArgumentException("secondProfileId cannot be null or empty");
        }
        if (connectionStatus == null || connectionStatus.isBlank()) {
            throw new IllegalArgumentException("connectionStatus cannot be null or empty");
        }
    }
}
