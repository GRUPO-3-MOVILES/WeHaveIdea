package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.application.internal.repositoriesimpl;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.aggregates.Employer;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.infrastructure.persistence.sdmbd.repositories.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployerRepositoryImpl implements EmployerRepository {

    MongoTemplate mongoTemplate;

    @Autowired
    public EmployerRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void saveEmployer(Employer employer) {
        mongoTemplate.save(employer);
    }

    @Override
    public Optional<Employer> findById(String employerId) {
        return Optional.ofNullable(mongoTemplate.findById(employerId, Employer.class));
    }

    @Override
    public List<Employer> findAll() {
        return mongoTemplate.findAll(Employer.class);
    }
}
