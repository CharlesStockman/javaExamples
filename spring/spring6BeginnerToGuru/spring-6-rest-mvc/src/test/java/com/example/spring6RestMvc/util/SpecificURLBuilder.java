package com.example.spring6RestMvc.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(SpecificURLBuilder.TestConfig.class)
@TestPropertySource( properties = {
    "app.url.protocol=http",
    "app.url.ip=127.0.0.1",
    "app.url.port=80",
    "app.url.context-/api/v1/beer"
})
public class SpecificURLBuilder {

    // Retrieves the classes needed to test
    @Configuration
    @ComponentScan("com.example.spring6RestMvc.util")
    static class TestConfig {};

    @Autowired
    private SpecificURLBuilder urlBuilder;

    @Test
    public void doesClasReceiveAllValuesFromProperties() {
        Assertions.assertTrue(urlBuilder.toString().compareTo("protocol = https, ip = 127.0.0.1, port = 80, context = /api/v1/beer") == 0);
    }



}
