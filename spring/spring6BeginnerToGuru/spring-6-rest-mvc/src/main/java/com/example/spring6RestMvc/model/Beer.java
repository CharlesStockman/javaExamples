package com.example.spring6RestMvc.model;

import com.example.spring6RestMvc.util.MetaDataFactory;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class Beer {
    private String beerName;
    private BeerStyle beerStyle;
    private String upc;
    private Integer quantityOnHand;
    private BigDecimal price;
    private MetaDataFactory.MetaData metaData;

    public UUID getId() {
        return metaData.getId();
    }
}
