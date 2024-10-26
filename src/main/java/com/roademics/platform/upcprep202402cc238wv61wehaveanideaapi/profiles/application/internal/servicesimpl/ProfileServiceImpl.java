package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.application.internal.servicesimpl;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.aggregates.Profile;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.commands.CreateProfileCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.commands.UpdateProfileCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.queries.GetAllProfilesQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.queries.GetProfilesByIdQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.model.queries.GetProfilesByNameQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.domain.services.ProfileService;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.profiles.infrastructure.persistence.sdmdb.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    MongoTemplate mongoTemplate;
    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(MongoTemplate mongoTemplate, ProfileRepository profileRepository) {
        this.mongoTemplate = mongoTemplate;
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(GetProfileByEmailQuery query) {
        return profileRepository.findByEmail(query.emailAddress());
    }

    @Override
    public Optional<Profile> handle(GetProfilesByNameQuery query) {
        return profileRepository.findByFullName(query.name());
    }

    @Override
    public Optional<Profile> handle(GetProfilesByIdQuery query) {
        return profileRepository.findById(query.profileId());
    }

    @Override
    public List<Profile> handle(GetAllProfilesQuery query) {
        return profileRepository.findAll();
    }

    @Override
    public Optional<Profile> handle(UpdateProfileCommand command) {
        // Buscar el perfil por ID
        Profile existingProfile = profileRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));

        // Actualizar el perfil con los nuevos datos
        existingProfile.updateProfile(command);

        // Guardar el perfil actualizado en la base de datos
        profileRepository.saveProfile(existingProfile);

        return Optional.of(existingProfile);
    }

    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {

        // Verificar si ya existe un perfil con el mismo email, phone o name
        if (profileRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("A profile with this email already exists.");
        }
        if (profileRepository.existsByPhone(command.phoneNumber())) {
            throw new IllegalArgumentException("A profile with this phone number already exists.");
        }
        if (profileRepository.existsByName(command.firstName() + " " + command.lastName())) {
            throw new IllegalArgumentException("A profile with this name already exists.");
        }

        // Crear un nuevo perfil
        Profile newProfile = new Profile(command);

        // Guardar el nuevo perfil en la base de datos
        profileRepository.saveProfile(newProfile);
        return Optional.of(newProfile);
    }
}