package com.example.injection.controller;

import com.example.injection.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class InjectorController {

    private final GreetingService greetingService;
    private GreetingService greetingService2 = null;

    @Autowired
    public InjectorController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Autowired
    @Qualifier("greetingServiceSecondary")
    public void setGreetingService(GreetingService greetingService2) {
        this.greetingService2 = greetingService2;
    }

    public String getSecondaryString() {
        return this.greetingService2.sayHello();
    }

    public String getPrimaryString() {
        return this.greetingService.sayHello();
    }
}
