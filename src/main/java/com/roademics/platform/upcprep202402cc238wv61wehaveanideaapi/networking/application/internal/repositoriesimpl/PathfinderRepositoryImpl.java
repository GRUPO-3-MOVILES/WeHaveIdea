package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.application.internal.repositoriesimpl;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.aggregates.Pathfinder;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.infrastructure.persistence.sdmbd.repositories.PathfinderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PathfinderRepositoryImpl implements PathfinderRepository {

    MongoTemplate mongoTemplate;

    @Autowired
    public PathfinderRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void savePathfinders(Pathfinder pathfinder) {
        mongoTemplate.save(pathfinder);
    }

    @Override
    public Optional<Pathfinder> findById(String pathfindersId) {
        return Optional.ofNullable(mongoTemplate.findById(pathfindersId, Pathfinder.class));
    }

    @Override
    public List<Pathfinder> findAll() {
        return mongoTemplate.findAll(Pathfinder.class);
    }
}
