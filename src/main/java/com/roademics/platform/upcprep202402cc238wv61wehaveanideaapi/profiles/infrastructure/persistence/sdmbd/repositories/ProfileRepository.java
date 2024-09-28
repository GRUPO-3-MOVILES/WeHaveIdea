package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.infrastructure.persistence.sdmbd.repositories;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.aggregates.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {

    Optional<Profile> findByEmail(String email);

    @Override
    List<Profile> findAll();

}
