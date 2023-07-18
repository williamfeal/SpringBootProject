package com.myspringproject.orderservice.service;

import com.myspringproject.orderservice.client.ProductServiceClient;
import com.myspringproject.orderservice.dto.ProductDto;
import com.myspringproject.orderservice.entity.Order;
import com.myspringproject.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @InjectMocks
    OrderService orderService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    ProductServiceClient productServiceClient;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setQuantity(5);
        order.setProductId(1L);

        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setQuantity(10);

        when(productServiceClient.getProduct(order.getProductId())).thenReturn(productDto);
        when(productServiceClient.decreaseProductQuantity(order.getProductId(), order.getQuantity())).thenReturn(productDto);
        when(orderRepository.save(order)).thenReturn(order);

        Order savedOrder = orderService.saveOrder(order);

        assertEquals(1L, savedOrder.getId());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testGetAllOrders() {
        Order order1 = new Order();
        order1.setId(1L);
        order1.setQuantity(5);
        order1.setProductId(1L);

        Order order2 = new Order();
        order2.setId(2L);
        order2.setQuantity(10);
        order2.setProductId(2L);

        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        List<Order> orders = orderService.getAllOrders();

        assertEquals(2, orders.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void testGetOrderById() {
        Order order = new Order();
        order.setId(1L);
        order.setQuantity(5);
        order.setProductId(1L);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order foundOrder = orderService.getOrderById(1L);

        assertEquals(1L, foundOrder.getId());
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteOrder() {
        orderService.deleteOrder(1L);

        verify(orderRepository, times(1)).deleteById(1L);
    }
}

