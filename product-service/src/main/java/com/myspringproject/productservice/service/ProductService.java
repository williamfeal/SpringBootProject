package com.myspringproject.productservice.service;

import com.myspringproject.productservice.entity.Product;
import com.myspringproject.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public Product decreaseProductQuantity(Long id, Integer quantity) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            int newQuantity = product.getQuantity() - quantity;
            if (newQuantity >= 0) {
                product.setQuantity(newQuantity);
                return productRepository.save(product);
            } else {
                throw new RuntimeException("Not enough product in stock");
            }
        } else {
            throw new RuntimeException("Product not found");
        }
    }
}
