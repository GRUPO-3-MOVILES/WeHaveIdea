package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.model.commands;

import java.util.List;

public record SignUpCommand(String username, String password, List<Role> roles) {
}

