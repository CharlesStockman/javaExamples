package com.example.spring6RestMvc.model;

import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @NotBlank
    private String customerName;
}
