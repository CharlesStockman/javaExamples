package com.example.spring6RestMvc.controller;

import com.example.spring6RestMvc.entities.Beer;
import com.example.spring6RestMvc.exception.NotFoundException;
import com.example.spring6RestMvc.mappers.BeerMapper;
import com.example.spring6RestMvc.model.BeerDTO;
import com.example.spring6RestMvc.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/beer")
@AllArgsConstructor
public class BeerController {
    private final BeerService beerService;
    private final BeerMapper beerMapper;

    @GetMapping("{beerId}")
    public BeerDTO getBeerById(@PathVariable("beerId") UUID beerId) {
            return beerMapper.beerToBeerDTO(beerService.getBeerById(beerId).orElseThrow(NotFoundException::new));
    }

    @RequestMapping( method = RequestMethod.GET)
    public List<BeerDTO> listBeers() {
        System.out.println("*** Currently in getBeerById ******** ");
        return beerMapper.beersListBeersToDTO(beerService.listBeers());
    }

    @PostMapping
    public ResponseEntity<BeerDTO> handleAPost(@Validated @RequestBody BeerDTO beer) {
        Beer savedBeer = beerService.saveNewBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("location", "/api/v1/beer/" + savedBeer.getId().toString());
        return  new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("{beerId}")
    public ResponseEntity<BeerDTO> updateById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer ) {
        Optional<Beer> returnedBeer = beerService.updateBeerById(beerId, beer);
        if ( returnedBeer.isEmpty())
            throw new NotFoundException();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{beerId}")
    public ResponseEntity deleteById(@PathVariable("beerId") UUID beerId) {
        boolean result = beerService.deleteById(beerId);
        if ( !result ) throw new NotFoundException();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("{beerId}")
    public ResponseEntity<BeerDTO> patchById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beerData ) {
        Optional<Beer> beer = beerService.patchById(beerId, beerData);
        if ( beer.isEmpty() ) throw new NotFoundException();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
