package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.interfaces.rest.acl;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.commands.SignUpCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.services.UserCommandService;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.entities.Role;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.aggregates.Profile;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.commands.CreateProfileCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.commands.UpdateProfileCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.queries.GetAllProfilesQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.queries.GetProfilesByIdQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.services.ProfileCommandService;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.services.ProfileQueryService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ProfilesContextFacade {

    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;
    private final UserCommandService userCommandService;

    public ProfilesContextFacade(ProfileCommandService profileCommandService,
                                 ProfileQueryService profileQueryService,
                                 UserCommandService userCommandService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
        this.userCommandService = userCommandService;
    }

    public String createProfile(String city, String state, String country, String zipCode
            , String phoneNumber, String email, String firstName, String lastName,
                              Date dateOfBirth, String profileType, String password) {
        // Crear el perfil
        var createProfileCommand = new CreateProfileCommand(city, state, country, zipCode
                , phoneNumber, email, firstName, lastName, dateOfBirth, profileType);
        var profileResult = profileQueryService.handle(createProfileCommand);
        if (profileResult.isEmpty()) return "0";

        // Crear el usuario asociado
        var signUpCommand = new SignUpCommand(email, password, List.of(Role.getDefaultRole()));
        var userResult = userCommandService.handle(signUpCommand);
        if (userResult.isEmpty()) return "0";

        return profileResult.get().getId();
    }

    public Optional<Profile> updateProfile(String profileId, String firstName, String lastName,
                                           String email, String phoneNumber, Date dateOfBirth,
                                           String city, String state, String country, String zipCode, String profileType) {
        UpdateProfileCommand command = new UpdateProfileCommand(firstName, lastName, email, phoneNumber,
                zipCode, city, state, country, dateOfBirth, profileType);
        return profileCommandService.handle(command);

    }

    public List<Profile> fetchAllProfiles() {
        return profileCommandService.handle(new GetAllProfilesQuery());
    }

    public Optional<Profile> fetchProfileById(String profileId) {
        return profileCommandService.handle(new GetProfilesByIdQuery(profileId));
    }

    public Optional<Profile> fetchProfileByEmail(String email) {
        return profileCommandService.handle(new GetProfileByEmailQuery(email));
    }
}
