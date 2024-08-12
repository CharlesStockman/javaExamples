package com.example.inventoryservice.repository;

import com.example.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    public Optional<Object> findBySkuCode(String skuCode);

    Boolean existsBySkuCode(String skuCode);

    Boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity );

    List<Inventory> findBySkuCodeIn(List<String> skuCodes);
}
