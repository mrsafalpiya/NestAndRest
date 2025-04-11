package com.nestandrest.model;

public class Product {
    private String name;
    private String image;
    private String category;
    private int price;

    public Product(String name, String image, String category, int price) {
        this.name = name;
        this.image = image;
        this.category = category;
        this.price = price;
    }

    public String getName() { return name; }
    public String getImage() { return image; }
    public String getCategory() { return category; }
    public int getPrice() { return price; }
}
