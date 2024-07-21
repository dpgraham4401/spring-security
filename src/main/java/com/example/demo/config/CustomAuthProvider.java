package com.example.demo.config;

import com.example.demo.user.SecurityUserDetails;
import com.example.demo.user.SecurityUserDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * The AuthenticationProvider interface is used to create a custom authentication logic.
 * It has two methods:
 * authenticate() -> The logic to authenticate the user.
 * supports() -> The logic to check if the provider supports the authentication token.
 * The AuthenticationManager delegates the authentication to the AuthenticationProvider based on whether
 * the provider supports the authentication type.
 */
@Component
public class CustomAuthProvider implements AuthenticationProvider {

    private final SecurityUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthProvider(SecurityUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * here we implement the logic to authenticate the user.
     * We either have to
     * 1) return an Authentication (interface) instance if the authentication is successful
     * 2) throw an AuthenticationException if the authentication fails
     * 3) we can return null if our provider does not support the authentication token
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        SecurityUserDetails user = userDetailsService.loadUserByUsername(username);

        if (passwordEncoder.matches(password, user.getPassword())) {
            // This is a BIG fix!!! I was passing the username, instead of user object as
            // the principal of the Authentication object. So every time I tried to access the principal's
            // attributes, I was getting a 'cannot cast String to UserDetails' exception.
            return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
        } else {
            throw new BadCredentialsException("Authentication failed");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
