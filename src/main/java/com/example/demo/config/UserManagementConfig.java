package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
public class UserManagementConfig {

    private final StaticCalloutFilter filter;

    UserManagementConfig(StaticCalloutFilter filter) {
        this.filter = filter;
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        var userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("select username, password, enabled from foo.users where username = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select a.user_id, a.authority from foo.authorities a " +
                "inner join foo.users u on a.user_id = u.id where u.username = ?");
        return userDetailsManager;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http.addFilterAt(filter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(c -> c.anyRequest().permitAll());


        return http.build();
    }


}
