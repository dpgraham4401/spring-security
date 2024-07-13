package com.example.demo.hello;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    // This method is secured with the @PreAuthorize annotation
    // It checks the condition before the method is executed
    // We can use any SpEL expression in the annotation such as hasAnyAuthority, hasRole, hasAnyRole
    @PreAuthorize("hasAnyAuthority('WRITE')")
    public String findHelloStringByNothing() {
        return "Hello Service Method!\n";
    }
}
