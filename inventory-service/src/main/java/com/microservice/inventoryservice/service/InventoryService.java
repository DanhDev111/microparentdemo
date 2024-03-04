package com.microservice.inventoryservice.service;

import com.microservice.inventoryservice.dto.InventoryDTO;
import com.microservice.inventoryservice.repository.InventoryRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InventoryService {
    @Autowired
    InventoryRepository inventoryRepository;
    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryDTO> checkisInStock(List<String> skuCode){
        log.info("Wait Started");
        Thread.sleep(10000);
        log.info("Wait Ended");
       return inventoryRepository.findBySkuCodeIn(skuCode)
               .stream().map(inventory -> InventoryDTO.builder()
                       .skuCode(inventory.getSkuCode())
                       .isInstock(inventory.getQuantity() > 0)
                       .build()
               ).collect(Collectors.toList());
    }

}
