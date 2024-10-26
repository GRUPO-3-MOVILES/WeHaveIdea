package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.application.internal.servicesimpl;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates.AIInteraction;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates.Roadmap;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.commands.CreateRoadmapCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.commands.UpdateRoadmapCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.services.AIInteractionService;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.services.RoadmapService;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.infrastructure.persistence.sdmdb.repositories.RoadmapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoadmapServiceImpl implements RoadmapService {

    MongoTemplate mongoTemplate;
    private final RoadmapRepository roadmapRepository;
    private final AIInteractionService aiInteractionService;

    @Autowired
    public RoadmapServiceImpl(RoadmapRepository roadmapRepository, AIInteractionService aiInteractionService, MongoTemplate mongoTemplate) {
        this.roadmapRepository = roadmapRepository;
        this.mongoTemplate = mongoTemplate;
        this.aiInteractionService = aiInteractionService;
    }

    @Override
    public Roadmap handle(CreateRoadmapCommand command) {
        Roadmap roadmap = new Roadmap(command);
        roadmapRepository.saveRoadmap(roadmap); // Save roadmap
        updateRoadmapWithAIContent(roadmap, command.aiInteractionId()); // Update roadmap with AI content
        return roadmap;
    }

    @Override
    public Optional<Roadmap> handle(UpdateRoadmapCommand command) {
        Roadmap roadmap = roadmapRepository.findById(command.roadmapId())
                .orElseThrow(() -> new IllegalArgumentException("Roadmap no encontrado"));
        roadmap.setTitle(command.title());
        roadmap.setDescription(command.description());
        roadmapRepository.updateById(command.roadmapId(), roadmap);
        return Optional.of(roadmap);
    }

    @Override
    public Optional<Roadmap> getRoadmapById(String roadmapId) {
        return roadmapRepository.findById(roadmapId);
    }

    @Override
    public Optional<Roadmap> updateRoadmapWithAIContent(Roadmap roadmap, String interactionMadeId) {
        AIInteraction interactionMade = aiInteractionService.getAIInteractionById(interactionMadeId)
                .orElseThrow(() -> new IllegalArgumentException("AIInteraction not found"));
        if (roadmap != null) {
            roadmap.addAIInteraction(interactionMade);
            roadmapRepository.updateById(roadmap.getId(), roadmap); // Save the updated roadmap
            return Optional.of(roadmap);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Roadmap> getRoadmapByTitle(String title) {
        return roadmapRepository.findByTitle(title);
    }

    @Override
    public void deleteRoadmap(String id) {
        roadmapRepository.deleteRoadmapById(id);
    }

    @Override
    public List<Roadmap> getAllRoadmapsForUser(String userId) {
        return roadmapRepository.findAllByOwnerId(userId);
    }

}