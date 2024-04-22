package com.example.injection;

import com.example.injection.controller.I18NController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("ES")
@SpringBootTest
class ProfileSpanishTests {
    public I18NController i18NController = null;

    @Autowired
    ProfileSpanishTests(I18NController controller) {
        this.i18NController = controller;
    }

    @Test
    public void testGetGreeting() {
        Assertions.assertSame(i18NController.sayHello(), "Spanish -- Hla Mundo");
    }

    @Test
    void contextLoads() {
    }



}
