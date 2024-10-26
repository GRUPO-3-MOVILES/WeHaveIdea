package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.interfaces;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.domain.services.UserCommandService;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.interfaces.rest.resources.AuthenticatedUserResource;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.interfaces.rest.resources.SignInResource;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.interfaces.rest.resources.SignUpResource;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.interfaces.rest.resources.UserResource;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Endpoints")
public class AuthenticationController {

    private final UserCommandService userCommandService;

    public AuthenticationController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserResource> signUp(@RequestBody SignUpResource resource) {
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(resource);
        var user = userCommandService.handle(signUpCommand);
        if (user.isEmpty()) return ResponseEntity.badRequest().build();
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody SignInResource resource) {
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(resource);
        var authenticatedUser = userCommandService.handle(signInCommand);
        if (authenticatedUser.isEmpty()) return ResponseEntity.notFound().build();
        var authenticatedUserResource = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());
        return ResponseEntity.ok(authenticatedUserResource);
    }
}
