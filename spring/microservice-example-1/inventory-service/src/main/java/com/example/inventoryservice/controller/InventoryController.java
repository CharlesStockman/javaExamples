package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.InventoryResponse;
import com.example.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Boolean isInStock(@RequestParam("skuCode") String skuCode, @RequestParam("quantity")Integer quantity) {
        InventoryResponse result = inventoryService.isInStock(skuCode, quantity);
        log.info(String.format("Inventory Controller: Result for skuCode -- %s with a quantity of %d has been found %b",
                result.getSkuCode(), quantity, result.isInStock()));
        return result.isInStock();
    }

}
