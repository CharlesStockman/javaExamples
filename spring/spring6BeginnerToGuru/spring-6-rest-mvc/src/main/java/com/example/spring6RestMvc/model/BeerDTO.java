package com.example.spring6RestMvc.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class BeerDTO implements Serializable {

    private UUID id;
    private Integer version;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @NotNull
    @NotBlank
    @Size(max=30)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private String beerName;

    @NotNull
    private BeerStyle beerStyle;


    @NotNull
    @NotBlank
    private String upc;

    private Integer quantityOnHand;

    @NotNull
    private BigDecimal price;

}
