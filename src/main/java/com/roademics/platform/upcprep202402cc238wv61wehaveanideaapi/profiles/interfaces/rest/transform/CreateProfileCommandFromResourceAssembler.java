package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.interfaces.rest.transform;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.commands.CreateProfileCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.interfaces.rest.resources.CreateProfileResource;

import java.util.Date;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(resource.city(), resource.state(), resource.country(), resource.zipCode()
                , resource.phoneNumber(), resource.email(), resource.firstName(), resource.lastName(),
                new Date(resource.dateOfBirth()), resource.biography(), resource.profileType());
    }
}