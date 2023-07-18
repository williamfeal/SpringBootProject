package com.myspringproject.productservice.service;

import com.myspringproject.productservice.entity.Product;
import com.myspringproject.productservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setQuantity(10);

        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.saveProduct(product);

        assertEquals("Test Product", savedProduct.getName());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Test Product 1");
        product1.setQuantity(10);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Test Product 2");
        product2.setQuantity(20);

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProducts();

        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testGetProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setQuantity(10);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product foundProduct = productService.getProductById(1L);

        assertEquals("Test Product", foundProduct.getName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteProduct() {
        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDecreaseProductQuantity() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setQuantity(10);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        Product updatedProduct = productService.decreaseProductQuantity(1L, 5);

        assertEquals(5, updatedProduct.getQuantity());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testDecreaseProductQuantity_NotEnoughStock() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setQuantity(10);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        assertThrows(RuntimeException.class, () -> productService.decreaseProductQuantity(1L, 15));

        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testDecreaseProductQuantity_ProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.decreaseProductQuantity(1L, 5));

        verify(productRepository, times(1)).findById(1L);
    }
}
