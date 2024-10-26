package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.entities;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "pathfinder_performance_metric_sets")
public class PathFinderPerformanceMetricSet extends AuditableAbstractAggregateRoot<PathFinderPerformanceMetricSet> {

    String profileId;
    Integer totalCompletedRoadmaps;
    List<String> RoadmapsCompleted;
    Integer totalCompletedModules;
    List<String> ModulesComplete;

    public PathFinderPerformanceMetricSet() {
        this.profileId = "";
        this.totalCompletedRoadmaps = 0;
        this.totalCompletedModules = 0;
        this.RoadmapsCompleted = List.of();
        this.ModulesComplete = List.of();
    }

    public void incrementTotalCompletedRoadmaps() {
        this.totalCompletedRoadmaps++;
    }

    public void incrementTotalCompletedModules() {
        this.totalCompletedModules++;
    }
}
