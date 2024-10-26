package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.services;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.aggregates.Pathfinder;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.CreatePathfindersCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.PathfindersMetricOnModuleCompletedCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.UpdatePathfindersMetricOnRoadmapCompletedCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.GetAllPathfindersQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.GetPathfinderByProfileIdQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.valueobjects.ProfileId;

import java.util.List;
import java.util.Optional;

public interface PathFinderService {
    List<Pathfinder> handle(GetAllPathfindersQuery query);
    Optional<Pathfinder> handle(GetPathfinderByProfileIdQuery query);
    ProfileId handle(CreatePathfindersCommand command);
    ProfileId handle(UpdatePathfindersMetricOnRoadmapCompletedCommand command);
    ProfileId handle(PathfindersMetricOnModuleCompletedCommand command);
}
