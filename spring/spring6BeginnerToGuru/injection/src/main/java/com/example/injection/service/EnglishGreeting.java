package com.example.injection.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"EN", "default"})
@Service("i18NService")
public class EnglishGreeting implements GreetingService {
    @Override
    public String sayHello() {
        return "English Hello World";
    }
}
