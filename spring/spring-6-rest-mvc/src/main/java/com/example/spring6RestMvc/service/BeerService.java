package com.example.spring6RestMvc.service;

import com.example.spring6RestMvc.model.Beer;

import java.util.UUID;

public interface BeerService {

    Beer getBeerById(UUID uuid);
}
