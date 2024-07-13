package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfig {


    private final AuthenticationProvider authenticationProvider;

    public AuthConfig(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        // Here we're using a form for login instead of the (native) HTTP Basic we had before
        // We also, on authentication success, redirect the user to the "/home" URL
        http.formLogin(c -> c.defaultSuccessUrl("/home", true));

        // Nothing new there in this commit -> all requests must be authenticated
        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());

        // Nothing new there in this commit -> we're hooking up our custom authentication provider
        http.authenticationProvider(authenticationProvider);

        return http.build();
    }
}
