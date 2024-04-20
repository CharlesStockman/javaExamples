package com.example.spring6RestMvc.controller;

import com.example.spring6RestMvc.model.Beer;
import com.example.spring6RestMvc.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerService beerService;

    @GetMapping("{beerId}")
    public Beer getBeerById(@PathVariable("beerId") UUID beerId) {
        log.debug("Get Beer by Id: {id} in controller");
        return beerService.getBeerById(beerId);
    }

    @RequestMapping( method = RequestMethod.GET)
    public List<Beer> listBeers() {
        return beerService.listBeers();
    }

    @PostMapping
    public ResponseEntity<Beer> handleAPost(Beer beer) {
        Beer saveBeer = beerService.saveNewBeer(beer);
        return  new ResponseEntity<Beer>(HttpStatus.CREATED);
    }


}
