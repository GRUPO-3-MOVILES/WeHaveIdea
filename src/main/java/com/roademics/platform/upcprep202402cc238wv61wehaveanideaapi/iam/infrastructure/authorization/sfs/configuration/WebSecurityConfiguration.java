package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.authorization.sfs.configuration;

import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.authorization.sfs.pipeline.BearerAuthorizationRequestFilter;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.hashing.bcrypt.BCryptHashingService;
import com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.iam.infrastructure.tokens.jwt.BearerTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfiguration {

    private final UserDetailsService userDetailsService;
    private final BearerTokenService tokenService;
    private final BCryptHashingService hashingService;
    private final AuthenticationEntryPoint unauthorizedRequestHandler;

    // Constructor para inyectar dependencias
    public WebSecurityConfiguration(UserDetailsService userDetailsService,
                                    BearerTokenService tokenService,
                                    BCryptHashingService hashingService,
                                    AuthenticationEntryPoint unauthorizedRequestHandler) {
        this.userDetailsService = userDetailsService;
        this.tokenService = tokenService;
        this.hashingService = hashingService;
        this.unauthorizedRequestHandler = unauthorizedRequestHandler;
    }

    // Filtro de autorización JWT para validar el token en cada solicitud
    @Bean
    public BearerAuthorizationRequestFilter authorizationRequestFilter() {
        return new BearerAuthorizationRequestFilter(tokenService, userDetailsService);
    }

    // Configuración de AuthenticationManager para manejar la autenticación
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Configuración del proveedor de autenticación, incluyendo la codificación de contraseñas
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(hashingService);
        return authenticationProvider;
    }

    // Configuración de PasswordEncoder para codificar las contraseñas usando BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return hashingService;
    }

    // Configuración principal de la cadena de filtros de seguridad
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Configuración de CORS (Cross-Origin Resource Sharing)
        http.cors(configurer -> configurer.configurationSource(c -> {
            var cors = new CorsConfiguration();
            cors.setAllowedOrigins(List.of("*")); // En producción, restringe los dominios permitidos
            cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
            cors.setAllowedHeaders(List.of("*"));
            return cors;
        }));

        // Desactivar CSRF, ya que JWT se utiliza para proteger las solicitudes
        http.csrf(AbstractHttpConfigurer::disable)
        // Asegurarse de que las rutas no protegidas estén configuradas correctamente


                .authorizeHttpRequests(authorizeRequests -> authorizeRequests

                        // Permitir acceso público a las rutas de autenticación
                        .requestMatchers("/api/authentication/sign-in", "/api/authentication/sign-up", "/api/ai-interactions/start", "/api/ai-interactions/send-prompt", "/api/ai-interactions/end").permitAll()

                        // Permitir acceso público a la documentación de Swagger
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/swagger-resources/**",
                                "/webjars/**").permitAll()

                        // Todas las demás rutas requieren autenticación
                        .anyRequest().authenticated()
                )
                // Configurar un EntryPoint para manejar solicitudes no autorizadas (401)
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(unauthorizedRequestHandler)
                )
                // Configurar que no se gestione sesiones en el servidor
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        // Proveedor de autenticación que define cómo se valida al usuario
        http.authenticationProvider(authenticationProvider());

        // Añadir el filtro de autorización JWT antes del filtro de autenticación de Spring Security
        http.addFilterBefore(authorizationRequestFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
