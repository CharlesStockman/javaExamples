package com.example.spring6RestMvc.service;

import com.example.spring6RestMvc.entities.Beer;
import com.example.spring6RestMvc.model.BeerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    List<Beer> listBeers();

    Optional<Beer> getBeerById(UUID uuid);

    Beer saveNewBeer(BeerDTO beer);

    Optional<Beer> updateBeerById(UUID beerId, Beer beer);

    Boolean deleteById(UUID beerId);

    Optional<Beer> patchById(UUID beerId, Beer beerData);
}
