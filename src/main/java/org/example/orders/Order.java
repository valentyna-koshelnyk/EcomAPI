package main.java.dev.ecom.orders;

import main.java.dev.ecom.products.Product;

public class Order {
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
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

    Product productId;
    int quantity;
    String customerDetails;
    public Order(int id, Product productId, int quantity, String customerDetails) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.customerDetails = customerDetails;
    }
}
