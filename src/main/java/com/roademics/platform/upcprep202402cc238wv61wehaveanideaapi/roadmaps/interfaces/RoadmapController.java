package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.interfaces;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.services.RoadmapService;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.interfaces.rest.resources.CreateRoadmapResource;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.interfaces.rest.resources.RoadmapResource;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.interfaces.rest.resources.UpdateRoadmapResource;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.interfaces.rest.transform.CreateRoadmapCommandFromResourceAssembler;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.interfaces.rest.transform.RoadmapResourceFromEntityAssembler;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.interfaces.rest.transform.UpdateRoadmapCommandFromResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roadmaps")
public class RoadmapController {

    private final RoadmapService roadmapService;

    @Autowired
    public RoadmapController(RoadmapService roadmapService) {
        this.roadmapService = roadmapService;
    }

    @PostMapping("/create")
    public ResponseEntity<RoadmapResource> createRoadmap(@RequestBody CreateRoadmapResource resource) {
        var command = CreateRoadmapCommandFromResourceAssembler.toCommandFromResource(resource);
        var roadmap = roadmapService.handle(command);
        if (roadmap == null) return ResponseEntity.badRequest().build();
        var roadmapResource = RoadmapResourceFromEntityAssembler.toResourceFromEntity(roadmap);
        return new ResponseEntity<>(roadmapResource, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<RoadmapResource> updateRoadmap(@RequestBody UpdateRoadmapResource resource) {
        var command = UpdateRoadmapCommandFromResourceAssembler.toCommandFromResource(resource);
        var roadmap = roadmapService.handle(command);
        if (roadmap.isEmpty()) return ResponseEntity.notFound().build();
        var roadmapResource = RoadmapResourceFromEntityAssembler.toResourceFromEntity(roadmap.get());
        return ResponseEntity.ok(roadmapResource);
    }

    @GetMapping("/{roadmapId}")
    public ResponseEntity<RoadmapResource> getRoadmapById(@PathVariable String roadmapId) {
        var roadmap = roadmapService.getRoadmapById(roadmapId);
        if (roadmap.isEmpty()) return ResponseEntity.notFound().build();
        var roadmapResource = RoadmapResourceFromEntityAssembler.toResourceFromEntity(roadmap.get());
        return ResponseEntity.ok(roadmapResource);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RoadmapResource>> getAllRoadmapsForUser(@PathVariable String userId) {
        var roadmaps = roadmapService.getAllRoadmapsForUser(userId);
        if (roadmaps.isEmpty()) return ResponseEntity.notFound().build();
        var roadmapResources = roadmaps.stream()
                .map(RoadmapResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(roadmapResources);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<RoadmapResource> getRoadmapByTitle(@PathVariable String title) {
        var roadmap = roadmapService.getRoadmapByTitle(title);
        if (roadmap.isEmpty()) return ResponseEntity.notFound().build();
        var roadmapResource = RoadmapResourceFromEntityAssembler.toResourceFromEntity(roadmap.get());
        return ResponseEntity.ok(roadmapResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoadmapById(@PathVariable String id) {
        roadmapService.deleteRoadmap(id);
        return ResponseEntity.noContent().build();
    }
}