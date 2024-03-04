package com.microservice.inventoryservice.util;

import com.microservice.inventoryservice.entity.Inventory;
import com.microservice.inventoryservice.repository.InventoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
//public class DataLoader implements CommandLineRunner {
//
//    @Autowired
//    InventoryRepository inventoryRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        Inventory inventory = new Inventory();
//        inventory.setSkuCode("giay_jordan");
//        inventory.setQuantity(100);
//
//        Inventory inventory1 = new Inventory();
//        inventory1.setSkuCode("giay_jordan_red");
//        inventory1.setQuantity(10);
//
//        inventoryRepository.save(inventory);
//        inventoryRepository.save(inventory1);
//    }
//}
