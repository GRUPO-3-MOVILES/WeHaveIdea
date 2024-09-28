package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.application.internal.commandservices;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.application.internal.outboundservices.GeminiServiceImpl;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates.AIInteraction;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates.Roadmap;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.commands.CreateRoadmapCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.commands.EndRoadmapCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.commands.UpdateRoadmapCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.services.RoadmapService;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.infrastructure.persistence.sdmbd.repositories.RoadmapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoadmapServiceImpl implements RoadmapService {

    private final RoadmapRepository roadmapRepository;
    private final GeminiServiceImpl geminiService;

    @Autowired
    public RoadmapServiceImpl(RoadmapRepository roadmapRepository, GeminiServiceImpl geminiService) {
        this.roadmapRepository = roadmapRepository;
        this.geminiService = geminiService;
    }

    @Override
    public Roadmap handle(CreateRoadmapCommand command) {
        Roadmap roadmap = new Roadmap(command);
        roadmapRepository.saveRoadmap(roadmap); // Save roadmap
        return roadmap;
    }

    @Override
    public Roadmap handle(UpdateRoadmapCommand command) {
        Roadmap roadmap = roadmapRepository.findById(command.roadmapId())
                .orElseThrow(() -> new IllegalArgumentException("Roadmap no encontrado"));
        roadmap.setTitle(command.title());
        roadmap.setDescription(command.description());
        roadmapRepository.updateById(command.roadmapId(), roadmap);
        return roadmap;
    }

    @Override
    public void handle(EndRoadmapCommand command) {
        Roadmap roadmap = roadmapRepository.findById(command.roadmapId())
                .orElseThrow(() -> new IllegalArgumentException("Roadmap no encontrado"));
        roadmap.setCompleted(true); // Mark as completed
        roadmapRepository.updateById(command.roadmapId(), roadmap);
    }

    @Override
    public Roadmap getRoadmapById(String roadmapId) {
        return roadmapRepository.findById(roadmapId)
                .orElseThrow(() -> new IllegalArgumentException("Roadmap no encontrado"));
    }

    @Override
    public List<Roadmap> getAllRoadmapsForUser(String userId) {
        return roadmapRepository.findAllByOwnerId(userId);
    }

    public Roadmap updateRoadmapWithAIContent(String roadmapId, String userPrompt) {
        Roadmap roadmap = getRoadmapById(roadmapId);
        AIInteraction aiInteraction = geminiService.getAIInteractionCompletion(userPrompt, roadmapId);
        roadmap.addAIInteraction(aiInteraction);
        roadmapRepository.updateById(roadmapId, roadmap); // Save the updated roadmap
        return roadmap;
    }
}
