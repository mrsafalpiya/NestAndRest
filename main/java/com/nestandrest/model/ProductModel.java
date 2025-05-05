package com.nestandrest.model;

public class ProductModel {
	private int productId;
	private String name;
	private String shortDescription;
	private String longDescription;
	private double price;
	private double discountedPrice;
	private int categoryId;
	private String category;
	private int stockQty;

	public ProductModel(int productId, String name, String shortDescription, String longDescription, double price,
			double discountedPrice, int categoryId, int stockQty) {
		super();
		this.productId = productId;
		this.name = name;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.price = price;
		this.discountedPrice = discountedPrice;
		this.categoryId = categoryId;
		this.stockQty = stockQty;
	}

	public ProductModel(int productId, String name, String shortDescription, String longDescription, double price,
			double discountedPrice, int categoryId, String category) {
		super();
		this.productId = productId;
		this.name = name;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.price = price;
		this.discountedPrice = discountedPrice;
		this.categoryId = categoryId;
		this.category = category;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getStockQty() {
		return stockQty;
	}

	public void setStockQty(int stockQty) {
		this.stockQty = stockQty;
	}
}
