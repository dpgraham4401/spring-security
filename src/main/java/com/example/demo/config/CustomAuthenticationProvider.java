package com.example.demo.config;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    /**
     * the AuthenticationProvider's authenticate method handles our logic for authenticating usings and
     * returns an Authentication object.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        // This if-else essentially replaces the responsibility of UserDetailsService and the PasswordEncoder
        // We would never do this, but for learning purposes.
        if ("john".equals(username) && "12345".equals(password)) {
            return new UsernamePasswordAuthenticationToken(
                    username, password, List.of());
        } else {
            throw new AuthenticationCredentialsNotFoundException("Error,");
        }
    }

    /**
     * what does this method do?
     */
    @Override
    public boolean supports(Class<?> authenticationType) {
        return UsernamePasswordAuthenticationToken
                .class
                .isAssignableFrom(authenticationType);
    }
}
