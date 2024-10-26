package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.application.internal.servicesimpl;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.aggregates.Pathfinder;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.CreatePathfindersCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.UpdatePathfindersMetricOnModuleCompletedCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.UpdatePathFinderCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.UpdatePathfindersMetricOnRoadmapCompletedCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.GetAllPathfindersQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.GetPathFinderTotalCompletedModulesQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.GetPathFinderTotalCompletedRoadmapsQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.GetPathfinderByProfileIdQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.services.PathFinderService;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.infrastructure.persistence.sdmbd.repositories.PathfinderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PathFinderServiceImpl implements PathFinderService {

    private final PathfinderRepository pathfinderRepository;

    @Autowired
    public PathFinderServiceImpl(PathfinderRepository pathfinderRepository) {
        this.pathfinderRepository = pathfinderRepository;
    }

    @Override
    public List<Pathfinder> handle(GetAllPathfindersQuery query) {
        return pathfinderRepository.findAll();
    }

    @Override
    public Optional<Pathfinder> handle(GetPathfinderByProfileIdQuery query) {
        return pathfinderRepository.findById(query.profileId());
    }

    @Override
    public Integer handle(GetPathFinderTotalCompletedRoadmapsQuery query) {
        Pathfinder pathfinder = pathfinderRepository.findById(query.pathFinderId()).orElseThrow();
        return pathfinder.getTotalCompletedRoadmaps();
    }

    @Override
    public Integer handle(GetPathFinderTotalCompletedModulesQuery query) {
        Pathfinder pathfinder = pathfinderRepository.findById(query.pathFinderId()).orElseThrow();
        return pathfinder.getTotalCompletedModules();
    }

    @Override
    public Pathfinder handle(CreatePathfindersCommand command) {
        Pathfinder pathfinder = new Pathfinder(command);
        pathfinderRepository.savePathfinders(pathfinder);
        return pathfinder;
    }

    @Override
    public Optional<Pathfinder> handle(UpdatePathFinderCommand command) {
        Optional<Pathfinder> pathfinder = Optional.of(pathfinderRepository.findById(command.pathFinderId()).orElseThrow());
        pathfinder.get().UpdatePathFinder(command);
        return pathfinder;
    }

    @Override
    public Optional<Pathfinder> handle(UpdatePathfindersMetricOnRoadmapCompletedCommand command) {
        Optional<Pathfinder> pathfinder = Optional.of(pathfinderRepository.findById(command.pathfinderId()).orElseThrow());
        pathfinder.get().updatePerformanceOnRoadmapCompleted();
        return pathfinder;
    }

    @Override
    public Optional<Pathfinder> handle(UpdatePathfindersMetricOnModuleCompletedCommand command) {
        Optional<Pathfinder> pathfinder = Optional.of(pathfinderRepository.findById(command.pathfinderId()).orElseThrow());
        pathfinder.get().updatePerformanceOnModuleCompleted();
        return pathfinder;
    }
}
