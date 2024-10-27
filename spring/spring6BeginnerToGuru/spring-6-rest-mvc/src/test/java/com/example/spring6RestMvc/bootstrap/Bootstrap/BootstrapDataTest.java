package com.example.spring6RestMvc.bootstrap.Bootstrap;

import com.example.spring6RestMvc.repositories.BeerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class BootstrapDataTest {

    @Autowired
    BeerRepository beerRepository = null;

    BootstrapData bootstrapData;

    @BeforeEach
    void setUp() {
        bootstrapData = new BootstrapData(beerRepository);
    }

    @Test
    void TestRun() throws Exception {
        bootstrapData.run((String) null);
        Assertions.assertEquals(3, beerRepository.count());
    }
}
