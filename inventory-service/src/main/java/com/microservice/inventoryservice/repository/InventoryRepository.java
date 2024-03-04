package com.microservice.inventoryservice.repository;

import com.microservice.inventoryservice.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Integer> {
    Optional<Inventory> findBySkuCode(String skucode);

    List<Inventory> findBySkuCodeIn(List<String> skuCode);
    // Check list

}
