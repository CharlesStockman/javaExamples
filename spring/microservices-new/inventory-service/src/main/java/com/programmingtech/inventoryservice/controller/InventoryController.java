package com.programmingtech.inventoryservice.controller;

import com.programmingtech.inventoryservice.dto.InventoryResponse;
import com.programmingtech.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    /**
     * For the @RequestParam the parameters must match the JSON Keys.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        System.out.println("***** Entered with " + skuCode.toString());
        List<InventoryResponse> result = inventoryService.isInStock(skuCode);
        return result;
    }
}
