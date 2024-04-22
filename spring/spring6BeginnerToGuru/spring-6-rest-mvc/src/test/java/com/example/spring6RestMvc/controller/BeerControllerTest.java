package com.example.spring6RestMvc.controller;

import com.example.spring6RestMvc.model.Beer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerControllerTest {

    @Autowired
    BeerController beerController;

    @Test
    void testGetBeerById() {
        Beer beer = beerController.getBeerById(UUID.randomUUID());
        System.out.println(beer.toString());
    }

}