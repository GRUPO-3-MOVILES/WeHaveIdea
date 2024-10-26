package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.services;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.aggregates.Pathfinder;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.CreatePathfindersCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.UpdatePathfindersMetricOnModuleCompletedCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.UpdatePathFinderCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.UpdatePathfindersMetricOnRoadmapCompletedCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.GetAllPathfindersQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.GetPathFinderTotalCompletedModulesQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.GetPathFinderTotalCompletedRoadmapsQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.GetPathfinderByProfileIdQuery;

import java.util.List;
import java.util.Optional;

public interface PathFinderService {

    // Query
    List<Pathfinder> handle(GetAllPathfindersQuery query);
    Optional<Pathfinder> handle(GetPathfinderByProfileIdQuery query);
    Integer handle(GetPathFinderTotalCompletedRoadmapsQuery query);
    Integer handle(GetPathFinderTotalCompletedModulesQuery query);

    // Command
    Pathfinder handle(CreatePathfindersCommand command);
    Optional<Pathfinder> handle(UpdatePathFinderCommand command);
    Optional<Pathfinder> handle(UpdatePathfindersMetricOnRoadmapCompletedCommand command);
    Optional<Pathfinder> handle(UpdatePathfindersMetricOnModuleCompletedCommand command);
}