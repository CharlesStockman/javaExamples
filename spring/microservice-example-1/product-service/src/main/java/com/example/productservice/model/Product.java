package com.example.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;

// Marks a class as being a domain object that is persisted to the Database
// collection -- name of collection where the data will be stored
@Document( collection = "Product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {

    @Id
    private String id;

    private String name;
    private String description;
    private BigDecimal price;
}
