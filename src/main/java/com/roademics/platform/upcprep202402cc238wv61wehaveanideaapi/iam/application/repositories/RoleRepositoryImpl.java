package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.application.repositories;

import com.mongodb.client.MongoClient;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.entities.Role;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.valueobjects.Roles;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.persistence.sdmdb.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @Autowired
    MongoTemplate mongoTemplate;
    private MongoClient mongo;

    @Override
    public Optional<Role> findByName(Roles name) {
        return Optional.ofNullable(mongoTemplate.findOne(Query.query(Criteria.where("name").is(name)), Role.class));
    }

    @Override
    public void saveRole(Role role) {
        mongoTemplate.save(role);
    }

    @Override
    public boolean existsByName(Roles name) {
        return mongoTemplate.exists(Query.query(Criteria.where("name").is(name)), Role.class);
    }
}
