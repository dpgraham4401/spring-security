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

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return helloService.getUserHelloString(name);
    }

    // A new endpoint that returns a (new) object of type Greeting
    @GetMapping("/hello/{name}/greeting")
    public Greeting greeting(@PathVariable String name) {
        return helloService.getGreeting(name);
    }
}
