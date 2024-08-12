package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.InventoryResponse;
import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public InventoryResponse isInStock (String skuCode, Integer quantity) {

         // Find an inventory for a given skuCode where quality is > 0
         // Did not use findFirst
         InventoryResponse inventoryResponse =
                 Stream.of(skuCode)
                         .map( item ->
                             InventoryResponse.builder()
                                     .skuCode(item)
                                     .isInStock(
                                             inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(
                                                     item, quantity))
                                     .build()).toList().getFirst();



         if ( log.isTraceEnabled()) {
            log.trace(String.format("Charles Stockman: skuCode = %s and in stock = %b", inventoryResponse.getSkuCode(), inventoryResponse.isInStock() ));
         }

         return inventoryResponse;
    }


}
