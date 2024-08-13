package com.example.inventoryservice.repository;

import com.example.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity );

    Boolean existsBySkuCode(String skuCode);

    @Query("SELECT inventory.quantity FROM Inventory inventory WHERE inventory.skuCode = :skuCode")
    Integer findByQuantityBySkuCode(@Param("skuCode") String skuCode);





}
