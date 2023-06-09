package com.programmingtech.inventoryservice.service;

import com.programmingtech.inventoryservice.dto.InventoryResponse;
import com.programmingtech.inventoryservice.model.Inventory;
import com.programmingtech.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly=true)
    public  List<InventoryResponse> isInStock(List<String> skuCode) {

        ArrayList<Boolean> returnList = new ArrayList<>();

        List<InventoryResponse> results = inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                        .skuCode( inventory.getSkuCode())
                        .isInStock(inventory.getQuantity() > 0 )
                        .build()).toList();
        return results;



    }
}
