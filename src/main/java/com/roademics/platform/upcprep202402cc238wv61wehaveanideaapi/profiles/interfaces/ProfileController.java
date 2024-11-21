package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.interfaces;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.queries.GetAllProfilesQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.queries.GetProfilesByIdQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.services.ProfileService;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.interfaces.rest.resources.CreateProfileResource;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.interfaces.rest.resources.ProfileResource;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.interfaces.rest.resources.UpdateProfileResource;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.interfaces.rest.transform.UpdateProfileCommandFromResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource resource) {
        var command = CreateProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var profile = profileService.handle(command);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }

    @GetMapping("/fecthallprofile")
    public ResponseEntity<List<ProfileResource>> getAllProfiles() {
        var getAllProfilesQuery = new GetAllProfilesQuery();
        var profiles = profileService.handle(getAllProfilesQuery);
        if (profiles.isEmpty()) return ResponseEntity.notFound().build();
        var profileResources = profiles.stream()
                .map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(profileResources);
    }

    @GetMapping("/getprofile{id}")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable String id) {
        var getProfilesByIdQuery = new GetProfilesByIdQuery(id);
        var profile = profileService.handle(getProfilesByIdQuery);
        if (profile.isEmpty()) return ResponseEntity.notFound().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    @GetMapping("/fetchbyprofile/{email}")
    public ResponseEntity<ProfileResource> getProfileByEmail(@RequestParam String email) {
        var getProfileByEmailQuery = new GetProfileByEmailQuery(email);
        var profile = profileService.handle(getProfileByEmailQuery);
        if (profile.isEmpty()) return ResponseEntity.notFound().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    @PutMapping("/updateprofile/{id}")
    public ResponseEntity<ProfileResource> updateProfile(@PathVariable String id, @RequestBody UpdateProfileResource resource) {
        var command = UpdateProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var updatedProfile = profileService.handle(command);
        if (updatedProfile.isEmpty()) return ResponseEntity.notFound().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(updatedProfile.get());
        return ResponseEntity.ok(profileResource);
    }
}