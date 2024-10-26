package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.application.internal.repositoriesimpl;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.aggregates.Connection;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.infrastructure.persistence.sdmbd.repositories.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ConnectionRepositoryImpl implements ConnectionRepository {

    MongoTemplate mongoTemplate;

    @Autowired
    public ConnectionRepositoryImpl(MongoTemplate _mongoTemplate) {
        this.mongoTemplate = _mongoTemplate;
    }

    @Override
    public void saveConnection(Connection connection) {
        mongoTemplate.save(connection);
    }

    @Override
    public Optional<Connection> findById(String connectionId) {
        return Optional.ofNullable(mongoTemplate.findById(connectionId, Connection.class));
    }

    @Override
    public List<Connection> findAll() {
        return mongoTemplate.findAll(Connection.class);
    }

    @Override
    public List<Connection> findByProfileId(String profileId) {
        return mongoTemplate.find(Query.query(Criteria.where("profileIdRequester").is(profileId)), Connection.class);
    }

    @Override
    public List<Connection> findByProfileIdAndStatus(String profileId, String status) {
        return mongoTemplate.find(Query.query(Criteria.where("profileIdRequester").is(profileId).and("status").is(status)), Connection.class);
    }
}
