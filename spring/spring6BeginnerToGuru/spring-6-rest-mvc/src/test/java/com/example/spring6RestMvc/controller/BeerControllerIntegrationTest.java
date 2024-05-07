package com.example.spring6RestMvc.controller;

import com.example.spring6RestMvc.entities.Beer;
import com.example.spring6RestMvc.exception.NotFoundException;
import com.example.spring6RestMvc.model.BeerDTO;
import com.example.spring6RestMvc.repositories.BeerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerControllerIntegrationTest {

    @Autowired
    BeerController beerController;

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testListBeers() {
        List<BeerDTO> dtos = beerController.listBeers();
        Assertions.assertThat(dtos.size()).isEqualTo(3);
    }

    @Transactional
    @Test
    void TestEmptyList() {
        beerRepository.deleteAll();
        List<BeerDTO> dtos = beerController.listBeers();
        Assertions.assertThat(dtos.size()).isEqualTo(0);
    }

    @Test
    void TestGetById() {
        Beer beer = beerRepository.findAll().getFirst();
        BeerDTO dto = beerController.getBeerById(beer.getId());
        Assertions.assertThat(dto.getBeerName()).isEqualTo(beer.getBeerName());
    }

    @Test
    void TestGetByIdNotFound() {
        assertThrows(NotFoundException.class, () -> beerController.getBeerById(UUID.randomUUID()));
    }
}