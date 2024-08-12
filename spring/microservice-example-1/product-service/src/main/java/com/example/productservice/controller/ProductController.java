package com.example.productservice.controller;

import com.example.productservice.service.ProductService;
import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        log.trace("For Product Controller inserting {}", productRequest.name());
        ProductResponse product = productService.createProduct(productRequest);
        log.trace("Insertion of product was successful -- product name = {} with id = {}", product.getName(), product.getId());
        return product;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        List<ProductResponse> productsResponses = productService.getAllProducts();

        log.trace("Retrieving the products");
        log.trace("\t Retrieved :{}", productsResponses.size());


        return productsResponses;
    }

}
