package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.aggregates;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.CreateEmployerCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.UpdateEmployerCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.valueobjects.ProfileId;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "employers")
public class Employer extends AuditableAbstractAggregateRoot<Employer> {

    private String companyName;
    private String industry;
    private ProfileId profileId;
    private List<Pathfinder> hiredPathfinders;
    private List<String> preferredSkills;
    private List<String> preferredLocations;

    public Employer(){
        this.hiredPathfinders = List.of();
    }

    public Employer(CreateEmployerCommand command){
        this.companyName = command.companyName();
        this.industry = command.industry();
        this.profileId = new ProfileId(command.profileId());
        this.preferredSkills = command.preferredSkills();
        this.preferredLocations = command.preferredLocations();
    }

    public void UpdateEmployer(UpdateEmployerCommand command){
        this.companyName = command.companyName();
        this.industry = command.industry();
        this.profileId = new ProfileId(command.profileId());
        this.preferredSkills = command.preferredSkills();
        this.preferredLocations = command.preferredLocations();
    }

    public void hirePathFinder(Pathfinder pathfinder){
        this.hiredPathfinders.add(pathfinder);
    }
}
