package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.aggregates;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.commands.CreateProfileCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.commands.UpdateProfileCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.entities.PersonalInformation;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.valueobjects.EmailAddress;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.valueobjects.ProfileType;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "profiles")
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    private PersonalInformation personalInformation;
    private EmailAddress email;
    private ProfileType profileType;
    private String biography;
    //private Connections connections;

    public Profile(CreateProfileCommand command){
        this.personalInformation = new PersonalInformation(command.firstName(), command.lastName(), command.city(), command.state(), command.country(), command.zipCode(), command.phoneNumber(), command.dateOfBirth());
        this.email = new EmailAddress(command.email());
        this.profileType = ProfileType.valueOf(command.profileType());
        this.biography = command.biography();
    }


    public void updateProfile(UpdateProfileCommand command){
        this.personalInformation = new PersonalInformation(command.firstName(), command.lastName(), command.city(), command.state(), command.country(), command.zipCode(), command.phoneNumber(), command.dateOfBirth());

        this.email = new EmailAddress(command.email());
        this.profileType = ProfileType.valueOf(command.profileType());
        this.biography = command.biography();
    }
}
