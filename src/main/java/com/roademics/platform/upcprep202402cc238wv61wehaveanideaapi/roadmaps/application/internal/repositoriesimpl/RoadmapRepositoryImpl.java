package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.application.internal.repositoriesimpl;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates.Roadmap;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.infrastructure.persistence.sdmbd.repositories.RoadmapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoadmapRepositoryImpl implements RoadmapRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Optional<Roadmap> findByTitle(String title) {
        Query query = new Query();
        query.addCriteria(Criteria.where("title").is(title));
        return Optional.ofNullable(mongoTemplate.findOne(query, Roadmap.class));
    }

    @Override
    public List<Roadmap> findAllByOwnerId(String ownerId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("ownerId").is(ownerId));
        return mongoTemplate.find(query, Roadmap.class);
    }

    @Override
    public void saveRoadmap(Roadmap roadmap) {
        mongoTemplate.save(roadmap);
    }

    @Override
    public boolean existingByTitle(String title) {
        Query query = new Query();
        query.addCriteria(Criteria.where("title").is(title));
        return mongoTemplate.exists(query, Roadmap.class);
    }

    @Override
    public boolean existingById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.exists(query, Roadmap.class);
    }

    @Override
    public void deleteRoadmapById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Roadmap.class);
    }

    @Override
    public void updateById(String id, Roadmap roadmap) {
        Roadmap newRoadmap = mongoTemplate.findById(id, Roadmap.class);
        assert newRoadmap != null;
        newRoadmap.setTitle(roadmap.getTitle());
        newRoadmap.setDescription(roadmap.getDescription());
        newRoadmap.setNodes(roadmap.getNodes());
        newRoadmap.setCompleted(roadmap.isCompleted());
        newRoadmap.setOwnerId(roadmap.getOwnerId());
        mongoTemplate.save(newRoadmap);
    }

    @Override
    public Optional<Roadmap> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, Roadmap.class));
    }
}
