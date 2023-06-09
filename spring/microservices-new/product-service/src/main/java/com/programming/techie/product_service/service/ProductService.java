package com.programming.techie.product_service.service;

import com.programming.techie.product_service.dto.ProductRequest;
import com.programming.techie.product_service.dto.ProductResponse;
import com.programming.techie.product_service.model.Product;
import com.programming.techie.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product is saved", product.toString());
    }

    public List<ProductResponse> getAllProducts() {
        log.error("Currently made it into getAllProducts");
        log.info("Currently made it into getAllProducts");
        List<Product> products = productRepository.findAll();
        log.info("Products retrieved from the database");
        List<ProductResponse> response = products.stream().map(this::mapToProductResponse).toList();
        return response;
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder().
                id(product.getId()).
                name(product.getName()).
                description(product.getDescription()).
                price(product.getPrice()).
                build();
    }
}
