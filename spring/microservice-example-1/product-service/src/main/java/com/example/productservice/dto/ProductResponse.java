package com.example.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// Question : Why create a separate response even though it is the same as the Product :
// Answer Good Practice not to expose the model entities since there might be fields that
// are necessary for the business model, but should not be exposed to the outside world.
public class ProductResponse {
    private String id;

    private String name;
    private String description;
    private BigDecimal price;
}
