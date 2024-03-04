package com.microservice.webapp.productservice.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Documented;
import java.util.UUID;


@Entity
@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private String description;
    private Double price;
}
