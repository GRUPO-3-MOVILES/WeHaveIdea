package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.infrastructure.persistence.sdmbd.repositories;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates.Roadmap;

import java.util.List;
import java.util.Optional;

public interface RoadmapRepository {
    Optional<Roadmap> findByTitle(String title);
    List<Roadmap> findAllByOwnerId(String ownerId);
    void saveRoadmap(Roadmap roadmap);
    boolean existingByTitle(String title);
    boolean existingById(String id);
    void deleteRoadmapById(String id);
    void updateById(String id, Roadmap roadmap);
    Optional<Roadmap> findById(String id);
}
