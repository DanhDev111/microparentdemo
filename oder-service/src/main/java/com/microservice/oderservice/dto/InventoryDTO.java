package com.microservice.oderservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryDTO {

    private String skuCode;
    boolean isInstock;
}
