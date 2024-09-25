package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.persistence.sdmdb.repositories;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.entities.Role;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.valueobjects.Roles;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByName(Roles name);
    void saveRole(Role role);
    boolean existsByName(Roles name);
}