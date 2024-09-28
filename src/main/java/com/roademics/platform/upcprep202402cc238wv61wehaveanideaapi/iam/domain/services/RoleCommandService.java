package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.services;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.queries.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}