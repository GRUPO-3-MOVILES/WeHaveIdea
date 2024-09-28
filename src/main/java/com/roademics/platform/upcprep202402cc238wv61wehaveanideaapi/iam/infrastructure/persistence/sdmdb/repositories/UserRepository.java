package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.persistence.sdmdb.repositories;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.aggregates.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository {
    Optional<User> findByUsername(String username);
    Optional<User> findById(String id);
    List<User> findAll();
    void saveUser(User user);
    boolean existsByUsername(String username);
}