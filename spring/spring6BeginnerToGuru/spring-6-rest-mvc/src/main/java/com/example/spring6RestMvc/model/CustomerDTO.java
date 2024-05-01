package com.example.spring6RestMvc.model;

import com.example.spring6RestMvc.util.MetaDataFactory;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDTO {
    private String customerName;
    private MetaDataFactory.MetaData metaData;
}
