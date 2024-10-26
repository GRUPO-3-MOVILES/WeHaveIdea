package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.services;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.aggregates.Employer;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.CreateEmployerCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.HirePathFinderCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.UpdateEmployerCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.GetAllEmployersQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.GetEmployerByProfileIdQuery;

import java.util.List;
import java.util.Optional;

public interface EmployerService {

    // Query
    List<Employer> handle(GetAllEmployersQuery query);
    Optional<Employer> handle(GetEmployerByProfileIdQuery query);

    // Command
    Employer handle(CreateEmployerCommand command);
    Optional<Employer> handle(UpdateEmployerCommand command);
    boolean handle(HirePathFinderCommand command);
}
