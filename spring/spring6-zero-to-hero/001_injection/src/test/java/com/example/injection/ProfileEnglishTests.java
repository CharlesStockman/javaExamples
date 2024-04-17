package com.example.injection;

import com.example.injection.controller.I18NController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("EN")
@SpringBootTest
class ProfileEnglishTests {
    public I18NController i18NController = null;

    @Autowired
    ProfileEnglishTests(I18NController controller) {
        this.i18NController = controller;
    }

    @Test
    public void testGetGreeting() {
        Assertions.assertSame(i18NController.sayHello(), "English Hello World");
    }

    @Test
    void contextLoads() {
    }



}
