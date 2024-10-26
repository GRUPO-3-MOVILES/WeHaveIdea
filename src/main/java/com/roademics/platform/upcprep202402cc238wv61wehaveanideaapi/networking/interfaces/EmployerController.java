package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.interfaces;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.networking.domain.services.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employer")
public class EmployerController {

    private final EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }
}
