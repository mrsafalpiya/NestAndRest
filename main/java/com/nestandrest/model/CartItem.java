package com.nestandrest.model;

public class CartItem {
    private int id;
    private String productName; 
    private int productId;
    private int quantity;
    private double price;

    // getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
