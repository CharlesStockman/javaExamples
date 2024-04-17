package com.example.injection.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceSecondary implements GreetingService {

    public final String sayHello() {
        return "Hello from routine being used with Qualifier";
    }
}
