package com.example.spring6RestMvc.service;

import com.example.spring6RestMvc.model.Beer;
import com.example.spring6RestMvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    private final Map<UUID, Beer> beerMap;

    public BeerServiceImpl() {
        Beer beer1 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("123456")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer2 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Crank")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("223456")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer3 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Sunshine City")
                .beerStyle(BeerStyle.IPA)
                .upc("123456")
                .price(new BigDecimal("13.99"))
                .quantityOnHand(144)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        beerMap = new HashMap<>();
        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
        beerMap.put(beer3.getId(), beer3);
    }

    @Override
    public List<Beer> listBeers() {
        ArrayList<Beer> beers = new ArrayList<>(beerMap.values());
        log.debug("listBeers : listing {}", beers.size());
        return beers;
    }

    @Override
    public Beer getBeerById(UUID id) {
        Beer beer = beerMap.get(id);
        //log.debug("getBeerByID for id:{}, beer:{}", id, beer.getBeerName());
        return beer;
    }

    @Override
    public Beer saveNewBeer(Beer beer) {
        Beer savedBeer = Beer.builder()
                .id(UUID.randomUUID())
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .upc(beer.getUpc())
                .quantityOnHand(beer.getQuantityOnHand())
                .price(beer.getPrice())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .version(1)
                .build();

        beerMap.put(savedBeer.getId(), savedBeer);

        return savedBeer;
    }

    @Override
    public void updateBeerById(UUID beerId, Beer beer) {
        Beer existing = beerMap.get(beerId);
        existing.setBeerName(beer.getBeerName());
        existing.setPrice(beer.getPrice());
        existing.setUpc(existing.getUpc());
        existing.setQuantityOnHand(beer.getQuantityOnHand());

        existing.setVersion(existing.getVersion() + 1);
        existing.setUpdateDate(LocalDateTime.now());
    }

    @Override
    public Beer deleteById(UUID beerId) {
        return beerMap.remove(beerId);
    }

    @Override
    public void patchById(UUID beerId, Beer beerData) {
        Beer beer = beerMap.get(beerId);

        if (StringUtils.hasText(beerData.getBeerName())) {
            beer.setBeerName(beerData.getBeerName());
        }

        if (beerData.getBeerStyle() != null ) {
            beer.setBeerStyle(beerData.getBeerStyle());
        }

        if ( beerData.getPrice() != null) {
            beer.setBeerStyle(beerData.getBeerStyle());
        }

        if ( beerData.getQuantityOnHand() != null ) {
            beer.setQuantityOnHand(beerData.getQuantityOnHand());
        }

        if ( StringUtils.hasText(beerData.getUpc())) {
            beer.setUpc(beerData.getUpc());
        }

        beer.setVersion(beer.getVersion() + 1);
        beer.setUpdateDate(LocalDateTime.now());
    }
}
