package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.shared.application.internal.outboundservice;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.authorization.sfs.pipeline.BearerAuthorizationRequestFilter;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.authorization.sfs.pipeline.UnauthorizedRequestHandlerEntryPoint;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.shared.interfaces.rest.resources.GeminiInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@EnableMethodSecurity
public class AppConfig {

    private final BearerAuthorizationRequestFilter bearerAuthorizationRequestFilter;
    private final UnauthorizedRequestHandlerEntryPoint unauthorizedRequestHandlerEntryPoint;

    public AppConfig(BearerAuthorizationRequestFilter bearerAuthorizationRequestFilter,
                     UnauthorizedRequestHandlerEntryPoint unauthorizedRequestHandlerEntryPoint) {
        this.bearerAuthorizationRequestFilter = bearerAuthorizationRequestFilter;
        this.unauthorizedRequestHandlerEntryPoint = unauthorizedRequestHandlerEntryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desactivar CSRF para tokens JWT
                .csrf(AbstractHttpConfigurer::disable)
                // Configurar autorización de solicitudes
                .authorizeHttpRequests(auth -> auth
                        // Permitir sin autenticación estas rutas
                        .requestMatchers("/sign-up", "/sign-in").permitAll()
                        // Cualquier otra ruta requiere autenticación
                        .anyRequest().authenticated()
                )
                // Manejar excepciones (401 Unauthorized)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(unauthorizedRequestHandlerEntryPoint)
                )
                // Definir que la sesión es sin estado
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Agregar el filtro JWT antes del filtro de autenticación
        http.addFilterBefore(bearerAuthorizationRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean(name = "customPasswordEncoder")
    public PasswordEncoder customPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = "CustomAuthenticationProvider")
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public RestClient GeminiRestClient(@Value("${gemini.api.url}") String baseUrl, @Value("${gemini.api.key}") String apiKey) {
        // Concatenar la API Key como parte del query string en la URL
        String fullUrl = baseUrl + "/v1beta/models/gemini-1.5-flash-latest:generateContent?key=" + apiKey;

        return RestClient.builder()
                .baseUrl(fullUrl) // Usar la URL completa que incluye el API Key como parámetro
                .defaultHeader("Accept", "application/json")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    @Bean
    public GeminiInterface geminiInterface(@Qualifier("GeminiRestClient") RestClient _restClient) {
        RestClientAdapter adapter = RestClientAdapter.create(_restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(GeminiInterface.class);
    }
}
