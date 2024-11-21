package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.valueobjects;


import lombok.Getter;

@Getter
public record PersonName(String firstName, String lastName) {
    public PersonName() {
        this(null, null);
    }

    public PersonName {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be null or blank");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be null or blank");
        }
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}
