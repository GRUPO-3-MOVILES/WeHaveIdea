package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.aggregates;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.commands.CreateProfileCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.commands.UpdateProfileCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.entities.PersonalInformation;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.valueobjects.EmailAddress;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.valueobjects.ProfileType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "profiles")
public class Profile {

    @Id
    private String id;
    private PersonalInformation personalInformation;
    private EmailAddress email;
    private ProfileType profileType;
    private String biography;
    //private Connections connections;

    public Profile(CreateProfileCommand command){
        this.personalInformation = new PersonalInformation(command.firstName(), command.lastName(), command.city(), command.state(), command.country(), command.zipCode(), command.phoneNumber(), command.dateOfBirth());

        this.email = new EmailAddress(command.email());
        this.profileType = ProfileType.valueOf(command.profileType());
        this.biography = "";
    }

    public void updateName(String firstName, String lastName){
        this.personalInformation.setPersonName(firstName, lastName);
    }

    public void updateEmail(String email){
        this.email = new EmailAddress(email);
    }

    public void updateBiography(String biography){
        this.biography = biography;
    }

    public String getFullName(){
        return personalInformation.getFullName();
    }

    public void updateProfileType(ProfileType profileType){
        this.profileType = profileType;
    }

    public void updateProfile(UpdateProfileCommand command){
        this.personalInformation = new PersonalInformation(command.firstName(), command.lastName(), command.city(), command.state(), command.country(), command.zipCode(), command.phoneNumber(), command.dateOfBirth());

        this.email = new EmailAddress(command.email());
        this.profileType = ProfileType.valueOf(command.profileType());
        this.biography = "";
    }
}
