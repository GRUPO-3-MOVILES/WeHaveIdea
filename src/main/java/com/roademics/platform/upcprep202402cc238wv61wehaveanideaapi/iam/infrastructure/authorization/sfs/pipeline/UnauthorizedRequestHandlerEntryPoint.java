package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.authorization.sfs.pipeline;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Unauthorized Request Handler.
 * <p>
 * This class is responsible for handling unauthorized requests.
 * It is used by the Spring Security framework to handle unauthorized requests.
 * It implements the AuthenticationEntryPoint interface.
 * </p>
 * @see AuthenticationEntryPoint
 */

@Component
public class UnauthorizedRequestHandlerEntryPoint implements AuthenticationEntryPoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(UnauthorizedRequestHandlerEntryPoint.class);

    /**
     * This method is called by the Spring Security framework when an unauthorized request is detected.
     *
     * @param request       The request that caused the exception
     * @param response      The response that will be sent to the client
     * @param authException The exception that caused the unauthorized request
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        LOGGER.error("Unauthorized request from IP: {}, URI: {}, Error: {}",
                request.getRemoteAddr(), request.getRequestURI(), authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized request detected");
    }
}
