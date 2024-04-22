package com.example.spring6RestMvc.service;

import com.example.spring6RestMvc.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    List<Beer> listBeers();

    Beer getBeerById(UUID uuid);

    Beer saveNewBeer(Beer beer);

    void updateBeerById(UUID beerId, Beer beer);

    Beer deleteById(UUID beerId);

    void patchById(UUID beerId, Beer beerData);
}
