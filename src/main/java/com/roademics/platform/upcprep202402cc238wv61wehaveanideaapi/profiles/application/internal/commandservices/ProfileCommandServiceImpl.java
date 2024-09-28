package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.application.internal.commandservices;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.aggregates.Profile;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.commands.UpdateProfileCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.queries.GetAllProfilesQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.queries.GetProfilesByIdQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.queries.GetProfilesByNameQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.services.ProfileCommandService;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.infrastructure.persistence.sdmbd.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {

    @Autowired
    MongoTemplate mongoTemplate;

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileCommandServiceImpl(MongoTemplate mongoTemplate, ProfileRepository profileRepository) {
        this.mongoTemplate = mongoTemplate;
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(GetProfileByEmailQuery query) {
        // Usar MongoTemplate para buscar un perfil por email
        Query mongoQuery = new Query(Criteria.where("email").is(query.emailAddress().toString()));
        Profile profile = mongoTemplate.findOne(mongoQuery, Profile.class);
        return Optional.ofNullable(profile);
    }

    @Override
    public Optional<Profile> handle(GetProfilesByNameQuery query) {
        // Usar MongoTemplate para buscar un perfil por nombre
        Query mongoQuery = new Query(Criteria.where("name").regex(query.name(), "i"));
        Profile profile = mongoTemplate.findOne(mongoQuery, Profile.class);
        return Optional.ofNullable(profile);
    }

    @Override
    public Optional<Profile> handle(GetProfilesByIdQuery query) {
        // Usar MongoTemplate para buscar un perfil por ID
        Profile profile = mongoTemplate.findById(query.profileId(), Profile.class);
        return Optional.ofNullable(profile);
    }

    @Override
    public List<Profile> handle(GetAllProfilesQuery query) {
        // Usar MongoTemplate para obtener todos los perfiles
        return mongoTemplate.findAll(Profile.class);
    }

    @Override
    public Optional<Profile> handle(UpdateProfileCommand command) {
        Optional<Profile> existingProfile = profileRepository.findByEmail(command.email());

        if (existingProfile.isEmpty()) {
            return Optional.empty();  // El perfil no existe
        }

        Profile profile = existingProfile.get();
        profile.updateProfile(command);

        // Guardar los cambios en el perfil
        mongoTemplate.save(profile);

        return Optional.of(profile);
    }
}
