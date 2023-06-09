package com.programming.techie.product_service.repository;

import com.programming.techie.product_service.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product")
public interface ProductRepository extends MongoRepository<Product, String> {
}
