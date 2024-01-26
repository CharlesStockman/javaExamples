package com.example.invetoryservice.controller;

import com.example.invetoryservice.dto.InventoryResponse;
import com.example.invetoryservice.service.InventoryService;
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
    public List<InventoryResponse> isInStock(@RequestParam("sku-code") List<String> skuCodes) {
        List<InventoryResponse> result = inventoryService.isInStock(skuCodes);

        if ( log.isTraceEnabled() ) {
            if ( result == null || result.size() > 0 )
                log.trace(String.format("Charles Stockman: Returned list of InventoryResponse where first response is ",
                        result.get(0).getSkuCode(), result.get(0).isInStock()));
            else
                log.trace("Charles Stockman: No Inventory for at least one products in oder " + skuCodes);
        }
        return result;
    }

}
