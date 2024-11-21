package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.interfaces.rest.resources;

import java.util.Date;

public record UpdateProfileResource(String id, String city, String state, String country, String zipCode
        , String phoneNumber, String email, String firstName, String lastName,
                                   Date dateOfBirth, String biography, String profileType) {
}