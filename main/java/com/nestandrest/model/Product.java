package com.nestandrest.model;

public class Product {
    private String name;
    private String image;
    private String category;
    private int price;
    private int stock;
    
    private String Aname, subDescription, fullDescription, Acategory, color, size, Aimage;
    private double Aprice, discountedPrice;
    private int quantity;
    private boolean inStock, published;


    public Product(String name, String image, String category, int price) {
        this.name = name;
        this.image = image;
        this.category = category;
        this.price = price;
    }
    
    public Product()
    {
    	
    }

    public String getName() { return name; }
    public String getImage() { return image; }
    public String getCategory() { return category; }
    public int getPrice() { return price; }
    public int getStock() { return stock; }

public void setStock(int stock)
{
	this.stock = stock;
}


//=== Setters ===
public void setName(String Aname) { this.Aname = name; }
public void setSubDescription(String subDescription) { this.subDescription = subDescription; }
public void setFullDescription(String fullDescription) { this.fullDescription = fullDescription; }
public void setCategory(String Acategory) { this.Acategory = category; }
public void setColor(String color) { this.color = color; }
public void setSize(String size) { this.size = size; }
public void setImage(String Aimage) { this.Aimage = image; }
public void setAPrice(double Aprice) { this.price = price; }
public void setDiscountedPrice(double discountedPrice) { this.discountedPrice = discountedPrice; }
public void setQuantity(int quantity) { this.quantity = quantity; }
public void setInStock(boolean inStock) { this.inStock = inStock; }
public void setPublished(boolean published) { this.published = published; }

// === Getters (Optional, but useful later) ===
public String getAName() { return name; }
public String getSubDescription() { return subDescription; }
public String getFullDescription() { return fullDescription; }
public String getACategory() { return category; }
public String getColor() { return color; }
public String getSize() { return size; }
public String getAImage() { return image; }
public double getAPrice() { return price; }
public double getDiscountedPrice() { return discountedPrice; }
public int getQuantity() { return quantity; }
public boolean isInStock() { return inStock; }
public boolean isPublished() { return published; }
}

