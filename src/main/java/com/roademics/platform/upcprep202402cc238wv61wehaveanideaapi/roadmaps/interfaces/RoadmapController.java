package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.interfaces;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates.Roadmap;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.commands.CreateRoadmapCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.commands.UpdateRoadmapCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.services.RoadmapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roadmaps")
public class RoadmapController {

    private final RoadmapService roadmapService;

    @Autowired
    public RoadmapController(RoadmapService roadmapService) {
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
        Optional<Roadmap> roadmap = roadmapService.handle(command);
        return roadmap.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener un roadmap por ID
    @GetMapping("/{roadmapId}")
    public ResponseEntity<Roadmap> getRoadmapById(@PathVariable String roadmapId) {
        Optional<Roadmap> roadmap = roadmapService.getRoadmapById(roadmapId);
        return roadmap.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener todos los roadmaps para un usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Roadmap>> getAllRoadmapsForUser(@PathVariable String userId) {
        List<Roadmap> roadmaps = roadmapService.getAllRoadmapsForUser(userId);
        return ResponseEntity.ok(roadmaps);
    }

    // After
    @GetMapping("/title/{title}")
    public ResponseEntity<Roadmap> getRoadmapByTitle(@PathVariable String title) {
        Optional<Roadmap> roadmap = roadmapService.getRoadmapByTitle(title);
        return roadmap.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoadmapById(@PathVariable String id) {
        roadmapService.deleteRoadmap(id);
        return ResponseEntity.noContent().build();
    }
}