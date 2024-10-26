package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.services;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates.Roadmap;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.commands.CreateRoadmapCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.commands.UpdateRoadmapCommand;

import java.util.List;
import java.util.Optional;

public interface RoadmapService {

    Roadmap handle(CreateRoadmapCommand command);

    Optional<Roadmap> handle(UpdateRoadmapCommand command);

    Optional<Roadmap> getRoadmapById(String roadmapId);

    List<Roadmap> getAllRoadmapsForUser(String userId);

    void updateRoadmapWithAIContent(Roadmap roadmap, String InteractionMade);

    Optional<Roadmap> getRoadmapByTitle(String title);

    void deleteRoadmap(String id);
}
