package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.infrastructure.persistence.sdmdb.repositories;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.aggregates.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository {
    void saveProfile(Profile profile);
    Optional<Profile> findByEmail(String email);
    Optional<Profile> findByFullName(String phone);
    Optional<Profile> findById(String phone);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByName(String name);
    List<Profile> findAll();
}
