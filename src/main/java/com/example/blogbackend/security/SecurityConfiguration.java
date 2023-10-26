package com.example.blogbackend.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@CrossOrigin(origins = "*")
public class SecurityConfiguration {
    private JwtAuthEntryPoint authEntryPoint;
    private UserDetailsLoader userDetailsLoader;

    public SecurityConfiguration(JwtAuthEntryPoint authEntryPoint, UserDetailsLoader userDetailsLoader) {
        this.authEntryPoint = authEntryPoint;
        this.userDetailsLoader = userDetailsLoader;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Creating an instance for CSRF token handler
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");
        http
                // Prevents Spring from creating and storing authentication info in the HttpSession.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Applies the requestHandler we created above to require
                // CSRF tokens except for /sign-up and /login
                .csrf(csrf -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/sign-up", "/login").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                // Applies out AuthEntryPoint class to send us an error message when
                // receiving a 401 error.
                .exceptionHandling(eH -> eH.authenticationEntryPoint(authEntryPoint))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                // Set auth permissions for HTTP request endpoints
                .authorizeHttpRequests(requests -> {
                    // Anyone can visit /sign-up, /login, and /docs
                    requests.requestMatchers("/sign-up", "/login", "/docs").permitAll();
                    // Only authenticated users can visit the following endpoints.
                    requests.requestMatchers("/api/users/me", "/posts").authenticated();
                });
        http.httpBasic(Customizer.withDefaults());
        // Apply the JWTAuthenticationFilter to intercept each request to check
        // for valid JWT tokens.
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }
}
