package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.valueobjects;

public record Address(String city, String state, String country, String zipCode) {
    public Address() {
        this(null, null, null, null);
    }

    public String getFullAddress() {
        return String.format("%s, %s, %s, %s", city, state, country, zipCode);
    }

    public Address {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be null or blank");
        }
        if (state == null || state.isBlank()) {
            throw new IllegalArgumentException("State cannot be null or blank");
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be null or blank");
        }
        if (zipCode == null || zipCode.isBlank()) {
            throw new IllegalArgumentException("Zip code cannot be null or blank");
        }
    }
}
