package com.myspringproject.orderservice.controller;

import com.myspringproject.orderservice.client.ProductServiceClient;
import com.myspringproject.orderservice.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductServiceClient productServiceClient;

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productServiceClient.getProduct(id);
    }
}

