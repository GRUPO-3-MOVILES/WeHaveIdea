package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.services;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates.Roadmap;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.commands.CreateRoadmapCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.commands.EndRoadmapCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.commands.UpdateRoadmapCommand;

import java.util.List;

public interface RoadmapService {

    Roadmap handle(CreateRoadmapCommand command);

    Roadmap handle(UpdateRoadmapCommand command);

    void handle(EndRoadmapCommand command);

    Roadmap getRoadmapById(String roadmapId);

    List<Roadmap> getAllRoadmapsForUser(String userId);

    Roadmap updateRoadmapWithAIContent(String roadmapId, String userPrompt);
}
