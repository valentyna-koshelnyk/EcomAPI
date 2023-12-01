package org.example.orders;

import java.sql.Date;

public class Order {
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(String customerDetails) {
        this.customerDetails = customerDetails;
    }

    int productId;
    int quantity;
    String customerDetails;
    Date date;

    public Order() {
    }

    public Order(int id, int productId, int quantity, String customerDetails, Date date) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.customerDetails = customerDetails;
        this.date = date;
    }
}