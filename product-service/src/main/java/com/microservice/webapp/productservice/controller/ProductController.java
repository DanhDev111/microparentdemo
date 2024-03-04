package com.microservice.webapp.productservice.controller;

import com.microservice.webapp.productservice.dto.ProductDTO;
import com.microservice.webapp.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public void createProduct(@RequestBody @Valid ProductDTO productDTO){
        productService.create(productDTO);
    }

    @PostMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getAll(){
       return productService.getAll();
    }
}
