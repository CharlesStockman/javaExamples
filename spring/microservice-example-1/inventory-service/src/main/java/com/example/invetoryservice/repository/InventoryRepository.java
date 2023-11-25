package com.example.invetoryservice.repository;

import com.example.invetoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    public Optional<Object> findBySkuCode(String skuCode);

    Boolean existsBySkuCode(String skuCode);
}
