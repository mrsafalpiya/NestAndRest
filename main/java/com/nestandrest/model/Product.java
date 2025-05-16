package com.nestandrest.model;

/**
 * Represents a detailed product entity with full admin-side attributes.
 * Used for managing product data such as category, price, availability,
 * display details, and publication status.
 * 
 * @author Bhumika Karki
 */
public class Product {

	private int productId;
	private String name;
	private String image;
	private String category;
	private double price;
	private int stock;

	private String subDescription;
	private String fullDescription;
	private String color;
	private String size;
	private double discountedPrice;
	private int quantity;
	private boolean inStock;
	private boolean published;

    /**
     * Constructor used for listing or display purpose with limited fields.
     *
     * @param name      product name
     * @param image     image filename or URL
     * @param category  product category
     * @param price     base price of the product
     */
    public Product(String name, String image, String category, double price) {
        this.name = name;
        this.image = image;
        this.category = category;
        this.price = price;
    }

    /**
     * Default constructor for frameworks or manual field setting.
     */
    public Product() {
    }

    // Getters and Setters
    
    /** @return the product ID */
    public int getProductId() {
        return productId;
    }

	public void setProductId(int productId) {
		this.productId = productId;
	}

    /** @return product name */
    public String getName() {
        return name;
    }

	public void setName(String name) {
		this.name = name;
	}

    /** @return image filename or path */
    public String getImage() {
        return image;
    }

	public void setImage(String image) {
		this.image = image;
	}

    /** @return product category */
    public String getCategory() {
        return category;
    }

	public void setCategory(String category) {
		this.category = category;
	}

    /** @return base price */
    public double getPrice() {
        return price;
    }

	public void setPrice(double price) {
		this.price = price;
	}

    /** @return stock level */
    public int getStock() {
        return stock;
    }

	public void setStock(int stock) {
		this.stock = stock;
	}

    /** @return short description (used on cards or previews) */
    public String getSubDescription() {
        return subDescription;
    }

	public void setSubDescription(String subDescription) {
		this.subDescription = subDescription;
	}

    /** @return full detailed description */
    public String getFullDescription() {
        return fullDescription;
    }

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

    /** @return product color option */
    public String getColor() {
        return color;
    }

	public void setColor(String color) {
		this.color = color;
	}

    /** @return product size option */
    public String getSize() {
        return size;
    }

	public void setSize(String size) {
		this.size = size;
	}

    /** @return discounted price if available */
    public double getDiscountedPrice() {
        return discountedPrice;
    }

	public void setDiscountedPrice(double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

    /** @return quantity added in cart or selection */
    public int getQuantity() {
        return quantity;
    }

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

    /** @return true if the product is available in stock */
    public boolean isInStock() {
        return inStock;
    }

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

    /** @return true if the product is visible/published */
    public boolean isPublished() {
        return published;
    }

	public void setPublished(boolean published) {
		this.published = published;
	}
}
