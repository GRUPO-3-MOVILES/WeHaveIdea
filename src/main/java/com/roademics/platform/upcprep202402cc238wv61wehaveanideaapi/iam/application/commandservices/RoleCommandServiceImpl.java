package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.application.commandservices;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.commands.SeedRolesCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.entities.Role;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.valueobjects.Roles;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.services.RoleCommandService;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.persistence.sdmdb.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleCommandServiceImpl implements RoleCommandService {
    private final RoleRepository roleRepository;

    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if (!roleRepository.existsByName(role)) {
                roleRepository.save(new Role(Roles.valueOf(role.name())));
            }
        });
    }
}
