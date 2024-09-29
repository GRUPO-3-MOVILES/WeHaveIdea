package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.application.internal.eventhandlers;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.application.outboundservices.tokens.TokenService;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.queries.SeedRolesCommand;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.services.RoleCommandService;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Component
public class ApplicationReadyEventHandler {

    private final TokenService tokenService;
    private final RoleCommandService roleCommandService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventHandler.class);


    public ApplicationReadyEventHandler(TokenService tokenService, RoleCommandService roleCommandService) {
        this.tokenService = tokenService;
        this.roleCommandService = roleCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        tokenService.generateToken("admin");
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Starting application " + applicationName + ", verify if roles seeding is needed");
        var seedRolesCommand = new SeedRolesCommand();
        roleCommandService.handle(seedRolesCommand);
        LOGGER.info("Roles seeding verification finished for {}", applicationName);
    }
}