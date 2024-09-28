package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.services;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.aggregates.Profile;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.commands.CreateProfileCommand;

import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> handle(CreateProfileCommand command);
}
