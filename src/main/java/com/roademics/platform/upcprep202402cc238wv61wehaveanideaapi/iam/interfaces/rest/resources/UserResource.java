package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.interfaces.rest.resources;

import java.util.List;

public record UserResource(Long id, String username, List<String> roles) {
}
