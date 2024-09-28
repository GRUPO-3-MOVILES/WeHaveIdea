package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.application.internal.commandservices;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.application.internal.repositoriesimpl.RoleRepositoryImpl;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.entities.Role;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.valueobjects.Roles;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.services.RoleCommandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@AllArgsConstructor
@Service
public class RoleCommandServiceImpl implements RoleCommandService {
    private final RoleRepositoryImpl roleRepository;

    @Override
    public void handle() {
        try {
            Arrays.stream(Roles.values()).forEach(roleEnum -> {
                if (!roleRepository.existsByName(roleEnum)) {
                    Role role = new Role(roleEnum);
                    roleRepository.saveRole(role);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to seed roles", e);
        }
    }
}