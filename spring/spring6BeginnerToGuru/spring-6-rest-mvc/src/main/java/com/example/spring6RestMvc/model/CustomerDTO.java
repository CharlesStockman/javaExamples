package com.example.spring6RestMvc.model;

import com.example.spring6RestMvc.util.MetaDataFactory;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CustomerDTO {
    private UUID id;
    private String customerName;
    private MetaDataFactory.MetaData metaData;
}
