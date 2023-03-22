package com.programmingtech.inventoryservice.repository;

import com.programmingtech.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.function.BooleanSupplier;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Boolean> findBySkuCode(String skuCode);
}
