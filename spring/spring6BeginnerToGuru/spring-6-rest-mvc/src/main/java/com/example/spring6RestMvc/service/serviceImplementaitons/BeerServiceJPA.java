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
    public void updateBeerById(UUID beerId, BeerDTO beerDTO) {
        beerRepository.findById(beerId).ifPresent( foundBeer -> {
            foundBeer.setBeerName(beerDTO.getBeerName());
            foundBeer.setBeerStyle(beerDTO.getBeerStyle());
            foundBeer.setUpc(beerDTO.getUpc());
            foundBeer.setPrice(beerDTO.getPrice());
            beerRepository.save(foundBeer);
        });
    }

    @Override
    public BeerDTO deleteById(UUID beerId) {
        return null;
    }

    @Override
    public void patchById(UUID beerId, BeerDTO beerData) {

    }
}
