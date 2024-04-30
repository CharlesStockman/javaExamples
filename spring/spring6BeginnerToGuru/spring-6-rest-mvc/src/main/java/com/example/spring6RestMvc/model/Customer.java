package com.example.spring6RestMvc.model;

import com.example.spring6RestMvc.util.MetaDataFactory;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Customer {
    private String customerName;
    private MetaDataFactory.MetaData metaData;
}
