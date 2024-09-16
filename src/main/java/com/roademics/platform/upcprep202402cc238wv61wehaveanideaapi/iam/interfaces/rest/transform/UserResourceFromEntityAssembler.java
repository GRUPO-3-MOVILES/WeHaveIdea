package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.interfaces.rest.transform;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.aggregates.User;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.entities.Role;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
        var roles = entity.getRoles().stream().map(Role::getStringName).toList();
        return new UserResource(entity.getId(), entity.getUsername(), roles);
    }
}
