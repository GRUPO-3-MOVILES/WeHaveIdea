package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.infrastructure.persistence.sdmbd.repositories;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.aggregates.Employer;

import java.util.List;
import java.util.Optional;

public interface EmployerRepository {
    void saveEmployer(Employer employer);
    Optional<Employer> findById(String employerId);
    List<Employer> findAll();
}
