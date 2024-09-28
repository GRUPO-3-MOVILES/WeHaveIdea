package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.interfaces;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.application.internal.commandservices.RoadmapServiceImpl;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates.Roadmap;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.commands.CreateRoadmapCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.commands.UpdateRoadmapCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.services.RoadmapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roadmaps")
public class RoadmapController {

    private final RoadmapService roadmapService;

    @Autowired
    public RoadmapController(RoadmapServiceImpl roadmapService) {
        this.roadmapService = roadmapService;
    }

    // Crear un nuevo roadmap
    @PostMapping("/create")
    public ResponseEntity<Roadmap> createRoadmap(@RequestBody CreateRoadmapCommand command) {
        Roadmap roadmap = roadmapService.handle(command);
        return ResponseEntity.ok(roadmap);
    }

    // Actualizar un roadmap existente
    @PutMapping("/update")
    public ResponseEntity<Roadmap> updateRoadmap(@RequestBody UpdateRoadmapCommand command) {
        Roadmap roadmap = roadmapService.handle(command);
        return ResponseEntity.ok(roadmap);
    }

    // Obtener un roadmap por ID
    @GetMapping("/{roadmapId}")
    public ResponseEntity<Roadmap> getRoadmapById(@PathVariable String roadmapId) {
        Roadmap roadmap = roadmapService.getRoadmapById(roadmapId);
        return ResponseEntity.ok(roadmap);
    }

    // Obtener todos los roadmaps para un usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Roadmap>> getAllRoadmapsForUser(@PathVariable String userId) {
        List<Roadmap> roadmaps = roadmapService.getAllRoadmapsForUser(userId);
        return ResponseEntity.ok(roadmaps);
    }

    // Actualizar un roadmap con nodos y conexiones generados por IA (Gemini)
    @PutMapping("/{roadmapId}/update-ai")
    public ResponseEntity<Roadmap> updateRoadmapWithAI(@PathVariable String roadmapId, @RequestBody String prompt) {
        Roadmap roadmap = roadmapService.updateRoadmapWithAIContent(roadmapId, prompt);
        return ResponseEntity.ok(roadmap);
    }
}
