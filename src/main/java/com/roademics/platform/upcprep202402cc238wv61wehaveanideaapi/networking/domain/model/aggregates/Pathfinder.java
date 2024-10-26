package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.aggregates;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.CreatePathfindersCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.UpdatePathFinderCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.entities.PathFinderPerformanceMetricSet;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.valueobjects.ProfileId;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "pathfinders")
public class Pathfinder extends AuditableAbstractAggregateRoot<Pathfinder> {

   private ProfileId profileId;
   private PathFinderPerformanceMetricSet performanceMetricSet;
   private List<String> skills;
   private Integer experienceInYears;
   private String currentJobTitle;
   private boolean availability;
   private List<String> languages;

   public Pathfinder(){
        this.performanceMetricSet = new PathFinderPerformanceMetricSet();
   }

    public Pathfinder(CreatePathfindersCommand command){
      this();
      this.profileId = new ProfileId(command.profileId());
      this.skills = command.skills();
      this.experienceInYears = command.experienceInYears();
      this.currentJobTitle = command.currentJobTitle();
      this.availability = command.availability();
      this.languages = command.languages();

   }

   public void UpdatePathFinder(UpdatePathFinderCommand command){
      this.profileId = new ProfileId(command.pathFinderId());
      this.skills = command.skills();
      this.experienceInYears = command.experienceInYears();
      this.currentJobTitle = command.currentJobTitle();
      this.availability = command.availability();
      this.languages = command.languages();
   }

   public void updatePerformanceOnRoadmapCompleted(){
      this.performanceMetricSet.incrementTotalCompletedRoadmaps();
   }

    public void updatePerformanceOnModuleCompleted(){
        this.performanceMetricSet.incrementTotalCompletedModules();
    }

    public Integer getTotalCompletedRoadmaps() {
        return performanceMetricSet.getTotalCompletedRoadmaps();
   }

    public Integer getTotalCompletedModules() {
        return performanceMetricSet.getTotalCompletedModules();
    }
}
