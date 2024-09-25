package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.persistence.sdmdb.repositories;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.aggregates.User;

import java.util.Optional;


public interface UserRepository {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}