package com.example.demo.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello() {
        return helloService.findHelloStringByNothing();
    }


    // Here we're defining a new endpoint that take a path variable
    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return helloService.getUserHelloString(name);
    }
}
