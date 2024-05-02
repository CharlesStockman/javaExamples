package com.example.spring6RestMvc.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class BeerDTO {

    private UUID id;
    private Integer version;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private String beerName;
    private BeerStyle beerStyle;
    private String upc;
    private Integer quantityOnHand;
    private BigDecimal price;

}
