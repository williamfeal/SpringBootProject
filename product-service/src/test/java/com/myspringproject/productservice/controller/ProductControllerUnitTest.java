package com.myspringproject.productservice.controller;

import com.myspringproject.productservice.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import com.myspringproject.productservice.entity.Product;
import com.myspringproject.productservice.service.ProductService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerUnitTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {
        productService = mock(ProductService.class);
        productController = new ProductController(productService);
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(100.0);
        product.setInventory(10);
        product.setQuantity(5);

        when(productService.saveProduct(product)).thenReturn(product);

        ResponseEntity<Product> response = productController.createProduct(product);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(product, response.getBody());

        verify(productService, times(1)).saveProduct(product);
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product();
        product1.setName("Test Product 1");
        product1.setPrice(100.0);
        product1.setInventory(10);
        product1.setQuantity(5);

        Product product2 = new Product();
        product2.setName("Test Product 2");
        product2.setPrice(200.0);
        product2.setInventory(20);
        product2.setQuantity(10);

        when(productService.getAllProducts()).thenReturn(Arrays.asList(product1, product2));

        ResponseEntity<List<Product>> response = productController.getAllProducts();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());

        verify(productService, times(1)).getAllProducts();
    }

    @Test
    public void testGetProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(100.0);
        product.setInventory(10);
        product.setQuantity(5);

        when(productService.getProductById(1L)).thenReturn(product);

        ResponseEntity<Product> response = productController.getProductById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(product, response.getBody());

        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    public void testDeleteProduct() {
        doNothing().when(productService).deleteProduct(1L);

        ResponseEntity<Void> response = productController.deleteProduct(1L);

        assertEquals(200, response.getStatusCodeValue());

        verify(productService, times(1)).deleteProduct(1L);
    }
    @Test
    public void testDecreaseProductQuantity() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(100.0);
        product.setInventory(10);
        product.setQuantity(5);

        when(productService.decreaseProductQuantity(1L, 5)).thenReturn(product);

        ResponseEntity<Product> response = productController.decreaseProductQuantity(1L, 5);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(product, response.getBody());

        verify(productService, times(1)).decreaseProductQuantity(1L, 5);
    }


}