package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.interfaces;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.aggregates.Profile;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.commands.CreateProfileCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.commands.UpdateProfileCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.queries.GetAllProfilesQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.queries.GetProfilesByIdQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/api/create")
    public ResponseEntity<String> createProfile(@RequestBody CreateProfileCommand command) {
        Optional<Profile> profile = profileService.handle(command);
        return profile.map(p -> ResponseEntity.ok(p.getId())).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/fecthallprofile")
    public ResponseEntity<List<Profile>> getAllProfiles() {
        var getAllProfilesQuery = new GetAllProfilesQuery();
        var profiles = profileService.handle(getAllProfilesQuery);
        return  profiles.stream().toList().isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(profiles);
    }

    @GetMapping("/getprofile{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable String id) {
        var getProfilesByIdQuery = new GetProfilesByIdQuery(id);
        Optional<Profile> profile = profileService.handle(getProfilesByIdQuery);
        return profile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/fetchbyprofile/{email}")
    public ResponseEntity<Profile> getProfileByEmail(@RequestParam String email) {
        var getProfileByEmailQuery = new GetProfileByEmailQuery(email);
        Optional<Profile> profile = profileService.handle(getProfileByEmailQuery);
        return profile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/updateprofile/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable String id, @RequestBody UpdateProfileCommand command) {
        var updateProfileCommand = new UpdateProfileCommand(id, command.city(), command.state(), command.country(), command.zipCode(), command.phoneNumber(), command.email(), command.firstName(), command.lastName(), command.dateOfBirth(), command.biography(),command.profileType());
        Optional<Profile> updatedProfile = profileService.handle(updateProfileCommand);
        return updatedProfile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
