package com.microservice.inventoryservice.controller;

import com.microservice.inventoryservice.dto.InventoryDTO;
import com.microservice.inventoryservice.entity.Inventory;
import com.microservice.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryDTO> checkIsinStock(@RequestParam List<String> skuCode){
        return inventoryService.checkisInStock(skuCode);
    }
}
