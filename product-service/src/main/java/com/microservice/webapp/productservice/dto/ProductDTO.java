package com.microservice.webapp.productservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {

    private String id;
    private String name;
    private String description;
    private Double price;
}
