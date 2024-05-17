package com.example.spring6RestMvc.service.serviceImplementaitons;

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
    public List<BeerDTO> listBeers() {
        return beerRepository.findAll().stream().map(beerMapper::beerToBeerDTO).toList();
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID uuid) {
        return Optional.ofNullable(beerMapper.beerToBeerDTO(beerRepository.findById(uuid).orElse(null)));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beerDTO) {
        return beerMapper.beerToBeerDTO(beerRepository.save(beerMapper.beerDTOToBear(beerDTO)));

    }

    @Override
    public Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beerDTO) {

        // Can not do any updates outside the lambda function
        AtomicReference<Optional<BeerDTO>> atomicReference = new AtomicReference<>();

        beerRepository.findById(beerId).ifPresentOrElse(
                foundBeer -> {
                    foundBeer.setBeerName(beerDTO.getBeerName());
                    foundBeer.setBeerStyle(beerDTO.getBeerStyle());
                    foundBeer.setUpc(beerDTO.getUpc());
                    foundBeer.setPrice(beerDTO.getPrice());
                    atomicReference.set(Optional.of(beerMapper.beerToBeerDTO(beerRepository.save(foundBeer))));
                },
                () -> atomicReference.set(Optional.empty())
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
    public Optional<BeerDTO> patchById(UUID beerId, BeerDTO beerData) {

        AtomicReference<Optional<BeerDTO>> atomicBeer = new AtomicReference<>();

        beerRepository.findById(beerId).ifPresentOrElse(
                foundBeer -> {

                    if ( foundBeer.getBeerName() != null ) foundBeer.setBeerName(beerData.getBeerName());
                    if ( foundBeer.getBeerStyle() != null ) foundBeer.setBeerStyle(beerData.getBeerStyle());
                    if ( foundBeer.getPrice() != null ) foundBeer.setPrice(beerData.getPrice());
                    if ( foundBeer.getQuantityOnHand() != null ) foundBeer.setQuantityOnHand(beerData.getQuantityOnHand());
                    if ( foundBeer.getUpc() != null ) foundBeer.setUpc(beerData.getUpc());
                    atomicBeer.set(Optional.of(beerData));
                },
                () -> atomicBeer.set(Optional.empty()));

        return atomicBeer.get();
    }
}
