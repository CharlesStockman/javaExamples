package com.example.spring6RestMvc.repositories;

import com.example.spring6RestMvc.entities.Beer;
import com.example.spring6RestMvc.model.BeerStyle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                .beerName("beer").price(new BigDecimal("50.0")).upc("test1").beerStyle(BeerStyle.LAGER)
                .build());

        // Without this function, the test will pass even though there are constraints issues.
        // Problem: It running so quickly, but the session is ending to quickly
        // Solution: Use beerRepository.flush() to immediately write to disk
        beerRepository.flush();

        Assertions.assertNotNull(savedBeer);
        Assertions.assertNotNull(savedBeer.getId());
    }


}