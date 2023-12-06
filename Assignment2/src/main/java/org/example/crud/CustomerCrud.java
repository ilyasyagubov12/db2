package org.example.crud;
import org.example.db_connection;
import org.example.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CustomerCrud {
    private Connection connection;

    public CustomerCrud(Connection connection) {
        this.connection = connection;
    }

    public void createCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO Customers (Customer_id, Customer_name, Customer_mail, Customer_phone) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, customer.getCustomerId());
            pstmt.setString(2, customer.getCustomerName());
            pstmt.setString(3, customer.getCustomerMail());
            pstmt.setString(4, customer.getCustomerPhone());
            pstmt.executeUpdate();
        }
    }

    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM Customers";
        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = ((Statement) stmt).executeQuery(query)) {
            while (resultSet.next()) {
                int customerId = resultSet.getInt("Customer_id");
                String customerName = resultSet.getString("Customer_name");
                String customerMail = resultSet.getString("Customer_mail");
                String customerPhone = resultSet.getString("Customer_phone");

                customers.add(new Customer(customerId, customerName, customerMail, customerPhone));
            }
        }
        return customers;
    }

    public void updateCustomer(Customer customer) throws SQLException {
        String query = "UPDATE Customers SET Customer_name = ?, Customer_mail = ?, Customer_phone = ? WHERE Customer_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, customer.getCustomerName());
            pstmt.setString(2, customer.getCustomerMail());
            pstmt.setString(3, customer.getCustomerPhone());
            pstmt.setInt(4, customer.getCustomerId());
            pstmt.executeUpdate();
        }
    }

    public void deleteCustomer(int customerId) throws SQLException {
        String query = "DELETE FROM Customers WHERE Customer_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, customerId);
            pstmt.executeUpdate();
        }
    }
}