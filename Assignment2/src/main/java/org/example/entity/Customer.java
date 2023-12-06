package org.example.entity;

public class Customer {
    private int customerId;
    private String customerName;
    private String customerMail;
    private String customerPhone;

    // Constructors, getters, and setters
    // You can generate these using your IDE or manually define them

    // Constructor
    public Customer(int customerId, String customerName, String customerMail, String customerPhone) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerMail = customerMail;
        this.customerPhone = customerPhone;
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
}
