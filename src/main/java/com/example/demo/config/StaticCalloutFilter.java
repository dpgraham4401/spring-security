package com.example.demo.config;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StaticCalloutFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//        var httpRequest = (HttpServletRequest) request;
//        var httpResponse = (HttpServletResponse) response;

        System.out.println("HI!!!");
        filterChain.doFilter(request, response);
    }
}
