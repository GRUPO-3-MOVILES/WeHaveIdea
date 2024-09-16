package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.application.internal.commandservices;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates.Roadmap;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.commands.CreateRoadmapCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.commands.EndRoadmapCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.commands.UpdateRoadmapCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.valueobjects.PromptResponse;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.services.AIInteractionService;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.services.RoadmapService;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.infrastructure.persistence.sdmbd.repositories.RoadmapRepository;

import java.util.List;

public class RoadmapServiceImpl implements RoadmapService {

    private final RoadmapRepository roadmapRepository;
    private final AIInteractionService aiInteractionService;
    private PromptResponse promptResponse;

    public RoadmapServiceImpl(RoadmapRepository roadmapRepository, AIInteractionService aiInteractionService) {
        this.roadmapRepository = roadmapRepository;
        this.aiInteractionService = aiInteractionService;
    }

    public Roadmap handle(CreateRoadmapCommand command) {
        // Crear nuevo Roadmap
        Roadmap roadmap = new Roadmap(command);

        // Llamar al servicio de AI para obtener recomendaciones iniciales
        String prompt = "Generar roadmap para " + promptResponse.getUserPrompt() + " con estas especificaciones: " + promptResponse.getResponse() + ".";
        String aiResponse = "example"; //aiInteractionService.getCompletion(prompt);

        // Procesar la respuesta de la AI y generar nodos y edges
        roadmap.addNodesAndEdgesFromAIResponse(aiResponse);

        // Guardar el roadmap en la base de datos
        return roadmapRepository.save(roadmap);
    }

    @Override
    public Roadmap handle(UpdateRoadmapCommand command) {
        // Obtener el roadmap existente
        Roadmap roadmap = roadmapRepository.findById(command.roadmapId()).orElseThrow();

        // Actualizar el roadmap con los nuevos datos
        roadmap.setTitle(command.title());
        roadmap.setDescription(command.description());

        return roadmapRepository.save(roadmap);
    }

    @Override
    public void handle(EndRoadmapCommand command) {
        // Obtener el roadmap existente
        Roadmap roadmap = roadmapRepository.findById(command.roadmapId()).orElseThrow();

        // Marcar el roadmap como finalizado
        // roadmap.setFinished(true); ???

        roadmapRepository.save(roadmap);
    }

    @Override
    public Roadmap getRoadmapById(String roadmapId) {
        return roadmapRepository.findById(roadmapId).orElse(null);
    }

    @Override
    public List<Roadmap> getAllRoadmapsForUser(String userId) {
        return roadmapRepository.findAllByOwnerId(userId);
    }
}
