package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.application.internal.queryservices;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.aggregates.Profile;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.commands.CreateProfileCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.services.ProfileQueryService;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.infrastructure.persistence.sdmbd.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {

    @Autowired
    MongoTemplate mongoTemplate;

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileQueryServiceImpl(MongoTemplate mongoTemplate, ProfileRepository profileRepository) {
        this.mongoTemplate = mongoTemplate;
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {
        // Crear un nuevo perfil basado en el comando recibido
        Profile profile = new Profile(command);
        // Usar MongoTemplate para guardar el perfil
        mongoTemplate.save(profile);

        // Retornar el perfil creado
        return Optional.of(profile);
    }
}
