package org.example.crud;
import  org.example.db_connection;
import  org.example.entity.Order;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderCrud {
    private Connection connection;

    public OrderCrud(Connection connection) {
        this.connection = connection;
    }

    public void createOrder(Order order) throws SQLException {
        String query = "INSERT INTO Orders (Order_id, Customer_id, Order_date, Order_price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, order.getOrderId());
            pstmt.setInt(2, order.getCustomerId());
            pstmt.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            pstmt.setBigDecimal(4, order.getOrderPrice());
            pstmt.executeUpdate();
        }
    }

    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders";
        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                int orderId = resultSet.getInt("Order_id");
                int customerId = resultSet.getInt("Customer_id");
                Date orderDate = resultSet.getDate("Order_date");
                BigDecimal orderPrice = resultSet.getBigDecimal("Order_price");

                orders.add(new Order(orderId, customerId, orderDate, orderPrice));
            }
        }
        return orders;
    }

    public void updateOrder(Order order) throws SQLException {
        String query = "UPDATE Orders SET Customer_id = ?, Order_date = ?, Order_price = ? WHERE Order_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, order.getCustomerId());
            pstmt.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            pstmt.setBigDecimal(3, order.getOrderPrice());
            pstmt.setInt(4, order.getOrderId());
            pstmt.executeUpdate();
        }
    }

    public void deleteOrder(int orderId) throws SQLException {
        String query = "DELETE FROM Orders WHERE Order_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, orderId);
            pstmt.executeUpdate();
        }
    }
}