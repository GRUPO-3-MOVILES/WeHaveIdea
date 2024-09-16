package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.infrastructure.persistence.sdmbd.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates.Roadmap;

import java.util.List;

public interface RoadmapRepository extends MongoRepository<Roadmap, String> {
    List<Roadmap> findAllByOwnerId(String ownerId);
}
