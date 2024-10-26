package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.interfaces;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.aggregates.Employer;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.CreateEmployerCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.commands.HirePathFinderCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.GetAllEmployersQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.model.queries.GetEmployerByProfileIdQuery;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.services.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/employer")
public class EmployerController {

    private final EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    // Crear un nuevo empleador
    @PostMapping("/create")
    public ResponseEntity<Employer> createEmployer(@RequestBody CreateEmployerCommand command) {
        Employer employer = employerService.handle(command);
        return ResponseEntity.ok(employer);
    }

    // Listar todos los empleadores
    @GetMapping("/all")
    public ResponseEntity<Iterable<Employer>> getAllEmployers() {
        Iterable<Employer> employers = employerService.handle(new GetAllEmployersQuery());
        return ResponseEntity.ok(employers);
    }

    // Obtener un empleador por ID
    @GetMapping("/{profileId}")
    public ResponseEntity<Employer> getEmployerByProfileId(@PathVariable String profileId) {
        Optional<Employer> employer = employerService.handle(new GetEmployerByProfileIdQuery(profileId));
        return employer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Contratar un pathfinder
    @PostMapping("/hire")
    public ResponseEntity<Employer> hirePathFinder(@RequestBody HirePathFinderCommand command) {
        Optional<Employer> employer = employerService.handle(new GetEmployerByProfileIdQuery(command.employerId()));
        if (employer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        employerService.handle(command);
        return ResponseEntity.ok(employer.get());
    }
}
