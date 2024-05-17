package com.example.spring6RestMvc.service.serviceImplementaitons;

import com.example.spring6RestMvc.entities.Beer;
import com.example.spring6RestMvc.mappers.BeerMapper;
import com.example.spring6RestMvc.model.BeerDTO;
import com.example.spring6RestMvc.repositories.BeerRepository;
import com.example.spring6RestMvc.service.BeerService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Primary
@AllArgsConstructor
public class BeerServiceJPA implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public List<Beer> listBeers() {
        return beerRepository.findAll();
    }

    @Override
    public Optional<Beer> getBeerById(UUID uuid) {
        return Optional.ofNullable(beerRepository.findById(uuid).orElse(null));
    }

    @Override
    public Beer saveNewBeer(BeerDTO beerDTO) {
        return beerRepository.save(beerMapper.beerDTOToBear(beerDTO));

    }

    @Override
    public Optional<Beer> updateBeerById(UUID beerId, Beer beer) {

        // Can not do any updates outside the lambda function
        AtomicReference<Optional<Beer>> atomicReference = new AtomicReference<>();

        beerRepository.findById(beerId).ifPresentOrElse(
                foundBeer -> {
                    foundBeer.setBeerName(beer.getBeerName());
                    foundBeer.setBeerStyle(beer.getBeerStyle());
                    foundBeer.setUpc(beer.getUpc());
                    foundBeer.setPrice(beer.getPrice());
                    atomicReference.set(Optional.of(beerRepository.save(foundBeer)));
                },
                () -> { atomicReference.set(Optional.empty());
                }
        );

        return atomicReference.get();
    }

    @Override
    public Boolean deleteById(UUID beerId) {
        Boolean found = false;
        if ( beerRepository.existsById(beerId)) {
            found = true;
            beerRepository.deleteById(beerId);
        }
        
        return found;
    }

    @Override
    public Optional<Beer> patchById(UUID beerId, Beer beerData) {

        AtomicReference<Optional<Beer>> atomicBeer = new AtomicReference<>();

        beerRepository.findById(beerId).ifPresentOrElse(
                foundBeer -> {

                    if ( foundBeer.getBeerName() != null ) foundBeer.setBeerName(beerData.getBeerName());
                    if ( foundBeer.getBeerStyle() != null ) foundBeer.setBeerStyle(beerData.getBeerStyle());
                    if ( foundBeer.getPrice() != null ) foundBeer.setPrice(beerData.getPrice());
                    if ( foundBeer.getQuantityOnHand() != null ) foundBeer.setQuantityOnHand(beerData.getQuantityOnHand());
                    if ( foundBeer.getUpc() != null ) foundBeer.setUpc(beerData.getUpc());
                    atomicBeer.set(Optional.of(beerData));
                },
                () -> {
                    atomicBeer.set(Optional.empty());
                });

        return atomicBeer.get();
    }
}
