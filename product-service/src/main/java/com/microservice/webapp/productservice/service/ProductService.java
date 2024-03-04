package com.microservice.webapp.productservice.service;

import com.microservice.webapp.productservice.dto.ProductDTO;
import com.microservice.webapp.productservice.entity.Product;
import com.microservice.webapp.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    public void create(ProductDTO productDTO){
        String generatedId = UUID.randomUUID().toString();
        Product product = Product.builder()
                .id(generatedId)
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .build();
        // Hoặc là
//        Product product1 = new ModelMapper().map(productDTO,Product.class);
        product.setId(UUID.randomUUID().toString());
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }
    public List<ProductDTO> getAll(){
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::maptoProductDTO).collect(Collectors.toList());
        // Cách khác
        // return products.stream().map(product ->convertoDTO(product)).collect(Collectors.toList());
    }
    public ProductDTO convertoDTO(Product product){
        return new ModelMapper().map(product,ProductDTO.class);
    }
    public ProductDTO maptoProductDTO(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
