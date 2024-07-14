package com.example.demo.config;

import com.example.demo.hello.GreetingPermissionEvaluator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity // This is needed to enable method-level security
public class AuthConfig {

    private final AuthenticationProvider authenticationProvider;

    private final GreetingPermissionEvaluator evaluator;

    public AuthConfig(AuthenticationProvider authenticationProvider, GreetingPermissionEvaluator greetingPermissionEvaluator) {
        this.authenticationProvider = authenticationProvider;
        this.evaluator = greetingPermissionEvaluator;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        // Nothing new there in this commit -> we're hooking up our custom authentication provider
        http.authenticationProvider(authenticationProvider);

        // Back to implementing HTTP Basic authentication for this Authorization Chapter examples
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }


    @Bean
    public MethodSecurityExpressionHandler createExpressionHandler() {
        var expressionHandler =
                new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(evaluator);

        return expressionHandler;
    }

}
