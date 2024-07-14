package com.example.demo.hello;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * PermissionEvaluator have two methods that we need to implement, both called hasPermission:
 * hasPermission(Authentication authentication, Object targetDomainObject, Object permission)
 * hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission)
 * <p>
 * Both methods receive the Authentication object which represents the current user
 * The first receives the target object itself, so we have already retrieved the object
 * The second receives the target object's ID and type, so we can retrieve the needed object
 * <p>
 * These have different use cases: for example @PostAuthorize vs @PreAuthorize annotations
 */
@Component
public class GreetingPermissionEvaluator implements PermissionEvaluator {

    private final GreetingRepository greetingRepository;

    public GreetingPermissionEvaluator(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        Greeting greeting = (Greeting) targetDomainObject;
        String username = authentication.getPrincipal().toString();
        Greeting greetingByUsername = greetingRepository.findByUsername(username);

        boolean admin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));
        return admin || greeting.equals(greetingByUsername);
    }

    /**
     * We're not really checking against the targetId and targetType here (b/c) I'm feeling lazy and my data
     * model is kinda messed up. But you get the idea.
     */
    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        Greeting greeting = greetingRepository.findByUsername(authentication.getName());
        System.out.println("Checking permission for greeting with ID: " + targetId + " and greeting: " + greeting.getMessage());
        System.out.println("User has authorities: " + authentication.getAuthorities());
        return authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN") || a.getAuthority().equals("READ"));
    }
}
