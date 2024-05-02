package com.example.spring6RestMvc.model;

import jakarta.persistence.Version;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CustomerDTO {
    private UUID id;
    private Integer version;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private String customerName;
}
