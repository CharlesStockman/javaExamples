package com.example.spring6RestMvc.mappers;

import com.example.spring6RestMvc.entities.Beer;
import com.example.spring6RestMvc.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    Beer beerDTOToBear(BeerDTO beerDTO);

    BeerDTO beeerToBeerDTO(Beer beer);

}
