package com.example.spring6RestMvc.mappers;

import com.example.spring6RestMvc.entities.Beer;
import com.example.spring6RestMvc.model.BeerDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BeerMapper {
    Beer beerDTOToBear(BeerDTO beerDTO);

    BeerDTO beerToBeerDTO(Beer beer);

    List<Beer> beersListDTOToBeers(List<BeerDTO> beerDTOList);

    List<BeerDTO> beersListBeersToDTO(List<Beer> beers);

}
