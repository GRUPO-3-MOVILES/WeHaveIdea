package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.persistence.sdmdb.repositories;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.entities.Role;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.valueobjects.Roles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, Long> {
    Optional<Role> findByName(Roles name);
    boolean existsByName(Roles name);
}

