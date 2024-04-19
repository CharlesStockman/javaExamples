package com.example.spring6RestMvc.controller;

import com.example.spring6RestMvc.model.Beer;
import com.example.spring6RestMvc.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Slf4j
@Controller
@AllArgsConstructor
public class BeerController {
    private final BeerService beerService;

    public Beer getBeerById(UUID id) {
        log.debug("Get Beer by Id: {id} in controller");
        return beerService.getBeerById(id);
    }
}
