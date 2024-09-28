package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.interfaces;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.aggregates.Profile;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.commands.CreateProfileCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.commands.UpdateProfileCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.interfaces.rest.acl.ProfilesContextFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfilesContextFacade profilesContextFacade;

    @Autowired
    public ProfileController(ProfilesContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    @PostMapping
    public ResponseEntity<String> createProfile(@RequestBody CreateProfileCommand command, @RequestParam String password) {
        String profileId = profilesContextFacade.createProfile(command.city(), command.state(), command.country(), command.zipCode()
                , command.phoneNumber(), command.email(), command.firstName(), command.lastName(), command.dateOfBirth(), command.profileType(), password);
        if (Objects.equals(profileId, "0")) {
            return ResponseEntity.badRequest().body(profileId);
        }
        return ResponseEntity.ok(profileId);
    }

    @GetMapping
    public List<Profile> getAllProfiles() {
        return profilesContextFacade.fetchAllProfiles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable String id) {
        Optional<Profile> profile = profilesContextFacade.fetchProfileById(id);
        return profile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email")
    public ResponseEntity<Profile> getProfileByEmail(@RequestParam String email) {
        Optional<Profile> profile = profilesContextFacade.fetchProfileByEmail(email);
        return profile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable String id, @RequestBody UpdateProfileCommand command) {
        Optional<Profile> updatedProfile = profilesContextFacade.updateProfile(id, command.firstName(),
                command.lastName(), command.email(), command.phoneNumber(), command.dateOfBirth(),
                command.city(), command.state(), command.country(), command.zipCode(), command.profileType());
        return updatedProfile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
