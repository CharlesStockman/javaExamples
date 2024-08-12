package com.example.inventoryservice;

import com.example.inventoryservice.model.Inventory;

import com.example.inventoryservice.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Slf4j
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
//        return args -> {
//
//            Inventory inventory = new Inventory();
//            inventory.setSkuCode("iphone_13");
//            inventory.setQuantity(100);
//
//            Inventory inventory1 = new Inventory();
//            inventory1.setSkuCode("iphone_13_red");
//            inventory1.setQuantity(0);
//
//            List<Inventory> inventories = new ArrayList<Inventory>();
//            inventories.add(inventory);
//            inventories.add(inventory1);
//
//            for ( Inventory item : inventories)
//                if (!inventoryRepository.existsBySkuCode(item.getSkuCode()))
//                    inventoryRepository.save(item);
//
//            log.trace("Charles Stockman : Initial Data was entered into the data, for now verify the values in the cells manually");
//        };
//    }
}
