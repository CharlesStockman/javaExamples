package com.example.spring6RestMvc.service;

import com.example.spring6RestMvc.model.Beer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    List<Beer> listBeers();

    Optional<Beer> getBeerById(UUID uuid);

    Beer saveNewBeer(Beer beer);

    void updateBeerById(UUID beerId, Beer beer);

    Beer deleteById(UUID beerId);

    void patchById(UUID beerId, Beer beerData);
}
