package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.interfaces.rest.transform;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.commands.UpdateProfileCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.interfaces.rest.resources.UpdateProfileResource;

import java.util.Date;

public class UpdateProfileCommandFromResourceAssembler {
    public static UpdateProfileCommand toCommandFromResource(UpdateProfileResource resource) {
        return new UpdateProfileCommand(resource.id(), resource.city(), resource.state(), resource.country(), resource.zipCode()
                , resource.phoneNumber(), resource.email(), resource.firstName(), resource.lastName(),
                resource.dateOfBirth(), resource.biography(), resource.profileType());
    }
}