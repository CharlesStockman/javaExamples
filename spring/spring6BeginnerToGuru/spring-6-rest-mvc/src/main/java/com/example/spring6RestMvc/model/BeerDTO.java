package com.example.spring6RestMvc.model;

import com.example.spring6RestMvc.util.MetaDataFactory;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
public class BeerDTO {
    private UUID id;
    private String beerName;
    private BeerStyle beerStyle;
    private String upc;
    private Integer quantityOnHand;
    private BigDecimal price;
    private MetaDataFactory.MetaData metaData;

}
