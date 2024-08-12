package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.InventoryResponse;
import com.example.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    // Url created by inventory to get whether they are in stock or not.
    // http://localhost:8082/api/inventory?sku-code=iphone13&skuCode=iphone13_red
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Boolean isInStock(@RequestParam("skuCode") String skuCode, @RequestParam("quantity")Integer quantity) {
        InventoryResponse result = inventoryService.isInStock(skuCode, quantity);
        System.out.println("Result for skuCode -- " + skuCode + "with a quantity of " + quantity + "has been found " + result);
        return result.isInStock();
    }

}
