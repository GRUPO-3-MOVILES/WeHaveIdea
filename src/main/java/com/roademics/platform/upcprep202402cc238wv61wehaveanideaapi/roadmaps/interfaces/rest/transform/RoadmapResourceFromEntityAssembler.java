package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.interfaces.rest.transform;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.domain.model.aggregates.Roadmap;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.roadmaps.interfaces.rest.resources.RoadmapResource;

public class RoadmapResourceFromEntityAssembler {
    public static RoadmapResource toResourceFromEntity(Roadmap entity){
        return new RoadmapResource(entity.getId(),
                entity.getOwnerId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getAiInteractionId());
    }
}
