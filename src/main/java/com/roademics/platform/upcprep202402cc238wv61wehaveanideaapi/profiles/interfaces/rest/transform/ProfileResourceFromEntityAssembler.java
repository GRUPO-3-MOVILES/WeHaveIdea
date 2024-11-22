package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.interfaces.rest.transform;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.aggregates.Profile;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity) {
        return new ProfileResource(entity.getId(), entity.getPersonalInformation().getPersonName().getFirstName(),
                entity.getPersonalInformation().getPersonName().getLastName(),
                entity.getPersonalInformation().getAddress().city(),
                entity.getPersonalInformation().getAddress().state(),
                entity.getPersonalInformation().getAddress().country(),
                entity.getPersonalInformation().getAddress().zipCode(),
                entity.getPersonalInformation().getPhoneNumber(),
                entity.getPersonalInformation().getDateOfBirth(),
                entity.getEmail().toString(), entity.getProfileType().toString(), entity.getBiography());
    }
}