package com.example.spring6RestMvc.service.serviceImplementaitons;

import com.example.spring6RestMvc.entities.Beer;
import com.example.spring6RestMvc.model.BeerDTO;
import com.example.spring6RestMvc.model.BeerStyle;
import com.example.spring6RestMvc.service.BeerService;
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
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("123456")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .id(UUID.randomUUID())
                .version(1)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        Beer beer2 = Beer.builder()
                .id(UUID.randomUUID())
                .beerName("Crank")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("223456")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .id(UUID.randomUUID())
                .version(1)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        Beer beer3 = Beer.builder()
                .id(UUID.randomUUID())
                .beerName("Sunshine City")
                .beerStyle(BeerStyle.IPA)
                .upc("123456")
                .price(new BigDecimal("13.99"))
                .quantityOnHand(144)
                .id(UUID.randomUUID())
                .version(1)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
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
    public Optional<Beer> getBeerById(UUID id) {
        Beer beer = beerMap.get(id);
        return Optional.of(beer);
    }

    @Override
    public Beer saveNewBeer(BeerDTO beer) {
        Beer savedBeer = Beer.builder()
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .upc(beer.getUpc())
                .quantityOnHand(beer.getQuantityOnHand())
                .price(beer.getPrice())
                .id(UUID.randomUUID())
                .version(1)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        beerMap.put(savedBeer.getId(), savedBeer);

        return savedBeer;
    }

    @Override
    public Optional<Beer> updateBeerById(UUID beerId, Beer beer) {
        Beer existing = beerMap.get(beerId);
        existing.setBeerName(beer.getBeerName());
        existing.setPrice(beer.getPrice());
        existing.setUpc(existing.getUpc());
        existing.setQuantityOnHand(beer.getQuantityOnHand());

        return Optional.of(existing);
    }

    @Override
    public Boolean deleteById(UUID beerId) {
        beerMap.remove(beerId);
        return true;
    }

    @Override
    public Optional<Beer> patchById(UUID beerId, Beer beerData) {
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
        return Optional.of(beer);
    }
}
