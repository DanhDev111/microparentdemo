package com.microservice.oderservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skuCode;
    private Double price;
    private Integer quantity;

//    @ManyToOne
//    private Order order;
}
