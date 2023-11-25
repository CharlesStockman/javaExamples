package com.example.invetoryservice.service;

import com.example.invetoryservice.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock (String skuCode) {

         Boolean result = inventoryRepository.findBySkuCode(skuCode).isPresent();
         log.trace(String.format("For the Inventory Service Controller isInStock(%s) -- %b", skuCode, result ));

        return result;
    }


}
