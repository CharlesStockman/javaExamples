package com.example.injection;

import com.example.injection.controller.InjectorController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InjectorControllerTest {

    InjectorController injectorController = null;

    @Autowired
    InjectorControllerTest(InjectorController injectorController) {
        this.injectorController = injectorController;
    }

    @Test
    public void testPrimaryInjection() {
        Assertions.assertSame("Hello world using Primary Injection", injectorController.getPrimaryString());
    }

    @Test
    public void testQualifierInjection() {
        Assertions.assertSame( "Hello from routine being used with Qualifier", injectorController.getSecondaryString());
    }
}
