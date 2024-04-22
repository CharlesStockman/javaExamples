package com.example.injection.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("ES")
@Service("i18NService")
public class SpanishGreeting implements GreetingService {
    @Override
    public String sayHello() {
        return "Spanish -- Hla Mundo";
    }
}
