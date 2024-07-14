package com.example.demo.hello;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    private final GreetingRepository greetingRepository;

    public HelloService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    // This method is secured with the @PreAuthorize annotation
    // It checks the condition before the method is executed
    // We can use any SpEL expression in the annotation such as hasAnyAuthority, hasRole, hasAnyRole
    @PreAuthorize("hasAnyAuthority('WRITE')")
    public String findHelloStringByNothing() {
        return "Hello Service Method!\n";
    }

    @PostAuthorize("#name == authentication.name")
    public String getUserHelloString(String name) {
        return "Hello " + name + "!\n";
    }

    @PreAuthorize("hasPermission(#name, 'foo', 'READ')")
    public Greeting getGreeting(String name) {
        return greetingRepository.findByUsername(name);
    }
}
