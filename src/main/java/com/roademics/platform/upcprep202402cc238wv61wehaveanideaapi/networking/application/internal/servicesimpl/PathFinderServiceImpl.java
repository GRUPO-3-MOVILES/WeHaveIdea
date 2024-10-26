package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.application.internal.servicesimpl;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.aggregates.Pathfinder;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.CreatePathfindersCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.PathfindersMetricOnModuleCompletedCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.UpdatePathfindersMetricOnRoadmapCompletedCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.GetAllPathfindersQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.GetPathfinderByProfileIdQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.valueobjects.ProfileId;
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
    public ProfileId handle(CreatePathfindersCommand command) {
        Pathfinder pathfinder = new Pathfinder(command);
        pathfinderRepository.savePathfinders(pathfinder);
        return pathfinder.getProfileId();
    }

    @Override
    public ProfileId handle(UpdatePathfindersMetricOnRoadmapCompletedCommand command) {
        Pathfinder pathfinder = pathfinderRepository.findById(command.pathfinderId()).orElseThrow();
        return null;
    }

    @Override
    public ProfileId handle(PathfindersMetricOnModuleCompletedCommand command) {
        return null;
    }
}
