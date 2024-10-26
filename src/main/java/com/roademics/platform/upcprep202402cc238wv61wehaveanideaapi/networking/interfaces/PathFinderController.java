package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.interfaces;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.aggregates.Pathfinder;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.CreatePathfindersCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.UpdatePathFinderCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.UpdatePathfindersMetricOnModuleCompletedCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.UpdatePathfindersMetricOnRoadmapCompletedCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.GetAllPathfindersQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.GetPathFinderTotalCompletedModulesQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.GetPathFinderTotalCompletedRoadmapsQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.GetPathfinderByProfileIdQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.services.PathFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/path-finder")
public class PathFinderController {

    private final PathFinderService pathFinderService;

    @Autowired
    public PathFinderController(PathFinderService pathFinderService) {
        this.pathFinderService = pathFinderService;
    }

    // Crear un nuevo pathfinder
    @PostMapping("/create")
    public ResponseEntity<Pathfinder> createPathFinder(@RequestBody CreatePathfindersCommand command) {
        Pathfinder pathFinder = pathFinderService.handle(command);
        return ResponseEntity.ok(pathFinder);
    }

    // Actualizar un pathfinder existente
    @PutMapping("/update")
    public ResponseEntity<Pathfinder> updatePathFinder(@RequestBody UpdatePathFinderCommand command) {
        Optional<Pathfinder> pathFinder = pathFinderService.handle(command);
        return pathFinder.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar los roadmpas completados por un pathfinder
    @PutMapping("/update-roadmaps-completed")
    public ResponseEntity<Pathfinder> updatePathFinderRoadmapsCompleted(@RequestBody UpdatePathfindersMetricOnRoadmapCompletedCommand command) {
        Optional<Pathfinder> pathFinder = pathFinderService.handle(command);
        return pathFinder.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar los módulos completados por un pathfinder
    @PutMapping("/update-modules-completed")
    public ResponseEntity<Pathfinder> updatePathFinderModulesCompleted(@RequestBody UpdatePathfindersMetricOnModuleCompletedCommand command) {
        Optional<Pathfinder> pathFinder = pathFinderService.handle(command);
        return pathFinder.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener un pathfinder por ID
    @GetMapping("/{pathFinderId}")
    public ResponseEntity<Pathfinder> getPathFinderById(@PathVariable String pathFinderId) {
        GetPathfinderByProfileIdQuery query = new GetPathfinderByProfileIdQuery(pathFinderId);
        Optional<Pathfinder> pathFinder = pathFinderService.handle(query);
        return pathFinder.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Pathfinder>> getAllPathFinder() {
        GetAllPathfindersQuery query = new GetAllPathfindersQuery();
        Iterable<Pathfinder> pathfinders = pathFinderService.handle(query);
        return ResponseEntity.ok(pathfinders);
    }

    // Obtener el total de roadmaps completados por un pathfinder
    @GetMapping("/total-completed-roadmaps/{pathFinderId}")
    public ResponseEntity<Integer> getTotalCompletedRoadmaps(@PathVariable String pathFinderId) {
        GetPathFinderTotalCompletedRoadmapsQuery query = new GetPathFinderTotalCompletedRoadmapsQuery(pathFinderId);
        Integer total = pathFinderService.handle(query);
        return ResponseEntity.ok(total);
    }

    // Obtener el total de módulos completados por un pathfinder
    @GetMapping("/total-completed-modules/{pathFinderId}")
    public ResponseEntity<Integer> getTotalCompletedModules(@PathVariable String pathFinderId) {
        GetPathFinderTotalCompletedModulesQuery query = new GetPathFinderTotalCompletedModulesQuery(pathFinderId);
        Integer total = pathFinderService.handle(query);
        return ResponseEntity.ok(total);
    }
}
