package com.example.spring6RestMvc.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Customer {

    private UUID id;
    private String customerName;
    private Integer version;
    LocalDateTime createDate;
    LocalDateTime lastModifiedDate;


}
