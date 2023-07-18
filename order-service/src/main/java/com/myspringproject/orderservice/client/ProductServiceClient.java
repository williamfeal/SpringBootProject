package com.myspringproject.orderservice.client;

import com.myspringproject.orderservice.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "product-service")
public interface ProductServiceClient {

    @GetMapping("/products/{id}")
    ProductDto getProduct(@PathVariable("id") Long id);

    @PutMapping("/products/{id}/decreaseQuantity/{quantity}")
    ProductDto decreaseProductQuantity(@PathVariable("id") Long id, @PathVariable("quantity") Integer quantity);
}
