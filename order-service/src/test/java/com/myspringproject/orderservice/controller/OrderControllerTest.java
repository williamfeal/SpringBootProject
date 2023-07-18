package com.myspringproject.orderservice.controller;

import com.myspringproject.orderservice.entity.Order;
import com.myspringproject.orderservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setQuantity(5);
        order.setProductId(1L);

        when(orderService.saveOrder(order)).thenReturn(order);

        ResponseEntity<Order> response = orderController.createOrder(order);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(order, response.getBody());

        verify(orderService, times(1)).saveOrder(order);
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

        when(orderService.getAllOrders()).thenReturn(Arrays.asList(order1, order2));

        ResponseEntity<List<Order>> response = orderController.getAllOrders();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());

        verify(orderService, times(1)).getAllOrders();
    }

    @Test
    public void testGetOrderById() {
        Order order = new Order();
        order.setId(1L);
        order.setQuantity(5);
        order.setProductId(1L);

        when(orderService.getOrderById(1L)).thenReturn(order);

        ResponseEntity<Order> response = orderController.getOrderById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(order, response.getBody());

        verify(orderService, times(1)).getOrderById(1L);
    }

    @Test
    public void testDeleteOrder() {
        doNothing().when(orderService).deleteOrder(1L);

        ResponseEntity<Void> response = orderController.deleteOrder(1L);

        assertEquals(204, response.getStatusCodeValue());

        verify(orderService, times(1)).deleteOrder(1L);
    }
}

