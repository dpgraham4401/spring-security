package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebAuthorizationConfig {

    private final CustomAuthenticationProvider authenticationProvider;

    public WebAuthorizationConfig(CustomAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    /**
     * configure endpoint authorization. The .anyRequest().permitAll() allows all request without credentials
     */
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        // Calling this instructs the app to accept HTTP Basic as an auth method
        http.httpBasic(Customizer.withDefaults());

        // Here we register our custom authentication provider
        http.authenticationProvider(authenticationProvider);

        // This configures authorization rules at the endpoint level. We can target specific endpoints with this method.
        http.authorizeHttpRequests(
                c -> c.anyRequest().authenticated()
        );


        // Instead of creating Beans of the UserDetailsService, we could also
        // programmatically configure that an object that fulfils that interface in this method and attach to our app
        // using the http.userDetailsService() method

        return http.build();
    }
}
