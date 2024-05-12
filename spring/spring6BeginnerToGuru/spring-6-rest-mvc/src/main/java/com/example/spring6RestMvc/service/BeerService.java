package com.example.spring6RestMvc.service;

import com.example.spring6RestMvc.model.BeerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    List<BeerDTO> listBeers();

    Optional<BeerDTO> getBeerById(UUID uuid);

    BeerDTO saveNewBeer(BeerDTO beer);

    Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer);

    BeerDTO deleteById(UUID beerId);

    void patchById(UUID beerId, BeerDTO beerData);
}
