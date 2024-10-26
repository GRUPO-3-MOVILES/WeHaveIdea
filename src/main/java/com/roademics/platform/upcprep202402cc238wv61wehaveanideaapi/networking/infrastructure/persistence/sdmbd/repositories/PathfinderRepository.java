package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.infrastructure.persistence.sdmbd.repositories;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.aggregates.Pathfinder;

import java.util.List;
import java.util.Optional;

public interface PathfinderRepository {
    void savePathfinders(Pathfinder pathfinder);
    Optional<Pathfinder> findById(String pathfindersId);
    List<Pathfinder> findAll();
}
