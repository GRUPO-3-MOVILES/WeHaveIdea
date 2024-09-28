package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.application.internal.eventhandlers;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.application.internal.commandservices.RoleCommandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.event.ApplicationReadyEvent;

@Component
public class ApplicationReadyEventHandler {

    private final RoleCommandServiceImpl roleCommandService;

    @Autowired
    public ApplicationReadyEventHandler(RoleCommandServiceImpl roleCommandService) {
        this.roleCommandService = roleCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        roleCommandService.handle();
    }
}