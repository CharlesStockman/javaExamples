package com.example.spring6RestMvc.repositories;

import com.example.spring6RestMvc.entities.Beer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                .beerName("beer")
                .build());

        Assertions.assertNotNull(savedBeer);
        Assertions.assertNotNull(savedBeer.getId());
    }


}