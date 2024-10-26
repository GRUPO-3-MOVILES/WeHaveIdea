package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.application.internal.servicesimpl;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.aggregates.Employer;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.aggregates.Pathfinder;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.CreateEmployerCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.HirePathFinderCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.UpdateEmployerCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.GetAllEmployersQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.GetEmployerByProfileIdQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.services.EmployerService;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.infrastructure.persistence.sdmbd.repositories.EmployerRepository;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.infrastructure.persistence.sdmbd.repositories.PathfinderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerServiceImpl implements EmployerService {

    private final EmployerRepository employerRepository;
    private final PathfinderRepository pathfinderRepository;

    @Autowired
    public EmployerServiceImpl(EmployerRepository employerRepository, PathfinderRepository pathfinderRepository) {
        this.pathfinderRepository = pathfinderRepository;
        this.employerRepository = employerRepository;
    }

    @Override
    public Employer handle(CreateEmployerCommand command) {
        Employer employer = new Employer(command);
        employerRepository.saveEmployer(employer);
        return employer;
    }

    @Override
    public Optional<Employer> handle(UpdateEmployerCommand command) {
        Optional<Employer> employer = employerRepository.findById(command.employerId());
        if (employer.isPresent()) {
            employer.get().UpdateEmployer(command);
            employerRepository.saveEmployer(employer.get());
            return employer;
        }
        return Optional.empty();
    }

    @Override
    public List<Employer> handle(GetAllEmployersQuery query) {
        return employerRepository.findAll();
    }

    @Override
    public Optional<Employer> handle(GetEmployerByProfileIdQuery query) {
        return employerRepository.findById(query.profileId());
    }

    @Override
    public boolean handle(HirePathFinderCommand command) {
        Optional<Pathfinder> pathfinder = pathfinderRepository.findById(command.employerId());
        Employer employer = employerRepository.findById(command.employerId()).orElseThrow(() -> new RuntimeException("Employer not found"));
        if (pathfinder.isPresent()) {
            try{
                if (employer.getHiredPathfinders().contains(pathfinder.get())){
                    throw new RuntimeException("Pathfinder already hired");
                } else {
                    employer.hirePathFinder(pathfinder.get());
                }
            } catch (Exception e){
                throw new RuntimeException("PathFinder doesn't exist");
            }
            return true;
        }
        return false;
    }
}
