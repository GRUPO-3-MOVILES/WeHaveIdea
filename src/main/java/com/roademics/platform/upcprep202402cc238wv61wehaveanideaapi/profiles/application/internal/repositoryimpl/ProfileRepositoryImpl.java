package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.application.internal.repositoryimpl;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.aggregates.Profile;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.infrastructure.persistence.sdmdb.repositories.ProfileRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProfileRepositoryImpl implements ProfileRepository {

    MongoTemplate mongoTemplate;

    public ProfileRepositoryImpl(MongoTemplate _mongoTemplate) {
        this.mongoTemplate = _mongoTemplate;
    }

    @Override
    public void saveProfile(Profile profile) {
        this.mongoTemplate.save(profile);
    }

    @Override
    public Optional<Profile> findByEmail(String email) {
        return Optional.ofNullable(mongoTemplate.findOne(Query.query(Criteria.where("email").is(email)), Profile.class));
    }

    @Override
    public Optional<Profile> findByFullName(String fullName) {
        // Split the full name into first name and last name
        String[] nameParts = fullName.split(" ");
        if (nameParts.length < 2) {
            return Optional.empty(); // Full name should have at least first name and last name
        }
        String firstName = nameParts[0];
        String lastName = nameParts[1];

        // Create a query to match both first name and last name
        Query query = new Query(Criteria.where("personalInformation.firstName").is(firstName)
                .and("personalInformation.lastName").is(lastName));

        return Optional.ofNullable(mongoTemplate.findOne(query, Profile.class));
    }

    @Override
    public Optional<Profile> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, Profile.class));
    }

    @Override
    public boolean existsByEmail(String email) {
        return mongoTemplate.exists(Query.query(Criteria.where("email").is(email)), Profile.class);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return mongoTemplate.exists(Query.query(Criteria.where("phone").is(phone)), Profile.class);
    }

    @Override
    public boolean existsByName(String name) {
        return mongoTemplate.exists(Query.query(Criteria.where("name").is(name)), Profile.class);
    }

    @Override
    public List<Profile> findAll() {
        return mongoTemplate.findAll(Profile.class);
    }
}
