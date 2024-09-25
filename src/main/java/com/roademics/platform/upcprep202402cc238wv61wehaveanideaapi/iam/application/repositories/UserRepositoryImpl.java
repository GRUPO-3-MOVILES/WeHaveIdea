package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.application.repositories;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.aggregates.User;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.persistence.sdmdb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(mongoTemplate.findOne(Query.query(Criteria.where("username").is(username)), User.class));
    }

    @Override
    public boolean existsByUsername(String username) {
        return mongoTemplate.exists(Query.query(Criteria.where("username").is(username)), User.class);
    }
}
