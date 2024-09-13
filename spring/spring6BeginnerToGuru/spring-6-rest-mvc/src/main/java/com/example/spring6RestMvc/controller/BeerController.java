package com.example.spring6RestMvc.controller;

import com.example.spring6RestMvc.exception.NotFoundException;
import com.example.spring6RestMvc.model.BeerDTO;
import com.example.spring6RestMvc.service.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerService beerService;

    @GetMapping("{beerId}")
    public BeerDTO getBeerById(@PathVariable("beerId") UUID beerId) {
            return beerService.getBeerById(beerId).orElseThrow(NotFoundException::new);
    }

    @RequestMapping( method = RequestMethod.GET)
    public List<BeerDTO> listBeers() {
        return beerService.listBeers();
    }

    @PostMapping
    public ResponseEntity<BeerDTO> handleAPost(@Validated @RequestBody BeerDTO beer) {
        BeerDTO savedBeer = beerService.saveNewBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("location", "/api/v1/beer/" + savedBeer.getId().toString());
        return  new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("{beerId}")
    public ResponseEntity<BeerDTO> updateById(@Validated @PathVariable("beerId") UUID beerId, @RequestBody BeerDTO beer ) {
        Optional<BeerDTO> returnedBeer = beerService.updateBeerById(beerId, beer);
        if ( returnedBeer.isEmpty()) throw new NotFoundException();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{beerId}")
    public ResponseEntity deleteById(@Validated @PathVariable("beerId") UUID beerId) {
        boolean result = beerService.deleteById(beerId);
        if ( !result ) throw new NotFoundException();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("{beerId}")
    public ResponseEntity<BeerDTO> patchById(@Validated @PathVariable("beerId") UUID beerId, @RequestBody BeerDTO beerData ) {
        Optional<BeerDTO> beerDTO = beerService.patchById(beerId, beerData);
        if ( beerDTO.isEmpty() ) throw new NotFoundException();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
