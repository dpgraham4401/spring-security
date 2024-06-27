package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    private final CustomAuthenticationProvider authenticationProvider;

    public ProjectConfig(CustomAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    UserDetailsService userDetailsService() {
        var user = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    /**
     * Responsible for checking if the provided password matches the credentials of our user.
     * It doesn't need to know where the password comes from (db, secret manager, LDAP).
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
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
