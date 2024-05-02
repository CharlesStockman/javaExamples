package com.example.spring6RestMvc.service.serviceImplementaitons;

import com.example.spring6RestMvc.model.BeerDTO;
import com.example.spring6RestMvc.model.BeerStyle;
import com.example.spring6RestMvc.service.BeerService;
import com.example.spring6RestMvc.util.MetaDataFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    private final Map<UUID, BeerDTO> beerMap;

    public BeerServiceImpl() {
        BeerDTO beer1 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("123456")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .metaData(MetaDataFactory.createMetaData())
                .build();

        BeerDTO beer2 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .beerName("Crank")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("223456")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .metaData(MetaDataFactory.createMetaData())
                .build();

        BeerDTO beer3 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .beerName("Sunshine City")
                .beerStyle(BeerStyle.IPA)
                .upc("123456")
                .price(new BigDecimal("13.99"))
                .quantityOnHand(144)
                .metaData(MetaDataFactory.createMetaData())
                .build();

        beerMap = new HashMap<>();
        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
        beerMap.put(beer3.getId(), beer3);
    }

    @Override
    public List<BeerDTO> listBeers() {
        ArrayList<BeerDTO> beers = new ArrayList<>(beerMap.values());
        log.debug("listBeers : listing {}", beers.size());
        return beers;
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {
        BeerDTO beer = beerMap.get(id);
        //log.debug("getBeerByID for id:{}, beer:{}", id, beer.getBeerName());
        return Optional.of(beer);
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {
        BeerDTO savedBeer = BeerDTO.builder()
                .id(UUID.randomUUID())
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .upc(beer.getUpc())
                .quantityOnHand(beer.getQuantityOnHand())
                .price(beer.getPrice())
                .build();

        beerMap.put(savedBeer.getId(), savedBeer);

        return savedBeer;
    }

    @Override
    public void updateBeerById(UUID beerId, BeerDTO beer) {
        BeerDTO existing = beerMap.get(beerId);
        existing.setBeerName(beer.getBeerName());
        existing.setPrice(beer.getPrice());
        existing.setUpc(existing.getUpc());
        existing.setQuantityOnHand(beer.getQuantityOnHand());
        MetaDataFactory.updateMetaData(existing.getMetaData());
    }

    @Override
    public BeerDTO deleteById(UUID beerId) {
        return beerMap.remove(beerId);
    }

    @Override
    public void patchById(UUID beerId, BeerDTO beerData) {
        BeerDTO beer = beerMap.get(beerId);

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

        MetaDataFactory.updateMetaData(beerData.getMetaData());
    }
}
