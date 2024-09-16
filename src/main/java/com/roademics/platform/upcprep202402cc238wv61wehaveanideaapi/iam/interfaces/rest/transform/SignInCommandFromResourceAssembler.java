package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.interfaces.rest.transform;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.commands.SignInCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource resource) {
        return new SignInCommand(resource.username(), resource.password());
    }
}
