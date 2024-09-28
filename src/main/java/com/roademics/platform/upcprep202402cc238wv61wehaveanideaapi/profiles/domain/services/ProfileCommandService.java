package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.services;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.aggregates.Profile;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.commands.UpdateProfileCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.queries.GetAllProfilesQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.queries.GetProfilesByIdQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.queries.GetProfilesByNameQuery;

import java.util.List;
import java.util.Optional;

public interface ProfileCommandService {
    Optional<Profile> handle(GetProfileByEmailQuery query);

    Optional<Profile> handle(GetProfilesByNameQuery query);

    Optional<Profile> handle(GetProfilesByIdQuery query);

    List<Profile> handle(GetAllProfilesQuery query);

    Optional<Profile> handle(UpdateProfileCommand command);
}
