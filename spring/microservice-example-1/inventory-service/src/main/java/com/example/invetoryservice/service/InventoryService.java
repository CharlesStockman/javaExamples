package com.example.invetoryservice.service;

import com.example.invetoryservice.dto.InventoryResponse;
import com.example.invetoryservice.model.Inventory;
import com.example.invetoryservice.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock (List<String> skuCodes) {

         // Create a response that contains the skuCode and whether the item is in Stock.
         List<InventoryResponse> inventoryList = inventoryRepository.findBySkuCodeIn(skuCodes).stream()
                         .map(inventory ->
                                 InventoryResponse.builder().
                                         skuCode(inventory.getSkuCode()).
                                         isInStock(inventory.getQuantity() > 0 ).
                                         build()).
                                    toList();

         if ( log.isTraceEnabled()) {
             log.trace("Charles Stockamn: Products Ids and whether the quantity is great than 0");
             for ( InventoryResponse inventoryResponse : inventoryList )
                    log.trace(String.format("Charles Stockman: skuCode = %s and in stock = %b", inventoryResponse.getSkuCode(), inventoryResponse.isInStock() ));
         }

         return inventoryList;
    }


}
