package com.myspringproject.orderservice.service;

import com.myspringproject.orderservice.client.ProductServiceClient;
import com.myspringproject.orderservice.dto.ProductDto;
import com.myspringproject.orderservice.entity.Order;
import com.myspringproject.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductServiceClient productServiceClient;

    public Order saveOrder(Order order) {
        // Antes de guardar la orden, obtenemos información del producto
        ProductDto productDto = productServiceClient.getProduct(order.getProductId());
        // Aquí puedes usar la información del producto como lo necesites, por ejemplo
        // puedes validar que el producto exista antes de crear la orden.
        // Si no existe el producto, puedes lanzar una excepción.

        // Ahora también puedes verificar si hay suficiente cantidad del producto en stock
        if (productDto.getQuantity() < order.getQuantity()) {
            throw new RuntimeException("Not enough product in stock");
        }

        // Si hay suficiente producto en stock, disminuimos la cantidad del producto
        productServiceClient.decreaseProductQuantity(order.getProductId(), order.getQuantity());

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
