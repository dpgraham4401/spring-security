package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity // This is needed to enable method-level security
public class AuthConfig {


    private final AuthenticationProvider authenticationProvider;

    public AuthConfig(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        // Nothing new there in this commit -> we're hooking up our custom authentication provider
        http.authenticationProvider(authenticationProvider);

        // Back to implementing HTTP Basic authentication for this Authorization Chapter examples
        http.httpBasic(Customizer.withDefaults());

        // This reads as: "Any request must be authenticated with a user that has the 'WRITE' authority"
        // Note: Authorities are case-sensitive
        http.authorizeHttpRequests(c -> c.anyRequest().hasAnyAuthority("WRITE"));

        return http.build();
    }
}
