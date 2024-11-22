package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.interfaces.rest.resources;

import java.util.Date;

public record CreateProfileResource(String firstName, String lastName, String city, String state, String country, String zipCode, String phoneNumber, Date dateOfBirth, String email, String profileType, String biography) {
}