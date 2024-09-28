package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.application.internal.eventhandlers;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.commands.SeedRolesCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.services.RoleCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ApplicationReadyEventHandler {
    private final RoleCommandService roleCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventHandler.class);

    public ApplicationReadyEventHandler(RoleCommandService roleCommandService) {
        this.roleCommandService = roleCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        var applicationName = event.getApplicationContext().getId();
        var timestamp = currentTimestamp();
        LOGGER.info("Starting to verify if roles seeding is needed for {} at {}", applicationName, timestamp);

        var seedRolesCommand = new SeedRolesCommand();
        roleCommandService.handle(seedRolesCommand);

        LOGGER.info("Roles seeding verification finished for {} at {}", applicationName, timestamp);
    }

    private Date currentTimestamp() {
        return new Date(); // Return current date/time
    }
}