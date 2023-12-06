package org.example.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate; // Using LocalDate for date representation

public class Order {
    private int orderId;
    private int customerId;
    private Date orderDate;
    private BigDecimal orderPrice;

    // Constructors, getters, and setters
    // You can generate these using your IDE or manually define them

    // Constructor
    public Order(int orderId, int customerId, Date orderDate, BigDecimal orderPrice) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }
}
