package com.myspringproject.orderservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long productId;
    private int quantity;
    private double total;  // Nuevo campo
    private double discount;  // Nuevo campo

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {  // Nuevo getter
        return total;
    }

    public void setTotal(double total) {  // Nuevo setter
        this.total = total;
    }

    public double getDiscount() {  // Nuevo getter
        return discount;
    }

    public void setDiscount(double discount) {  // Nuevo setter
        this.discount = discount;
    }
}
