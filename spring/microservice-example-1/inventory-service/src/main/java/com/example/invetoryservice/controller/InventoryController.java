package com.example.invetoryservice.controller;

import com.example.invetoryservice.service.InventoryService;
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

    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean isInStock(@PathVariable("sku-code") String skuCode) {
        Boolean result = inventoryService.isInStock(skuCode);
        log.trace(String.format("For the Inventory Service Controller isInStock(%s) -- %b", skuCode, result ));
        return result;
    }

}
