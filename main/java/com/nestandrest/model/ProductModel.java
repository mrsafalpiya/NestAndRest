package com.nestandrest.model;

import java.util.Map;

/**
 * Represents the model for a product.
 * 
 * @author 23047626 Ayush Shrestha
 * @author 23047584 Bhumika Karki
 * @author 23048460 Safal Piya
 * @author 23047589 Sanniva Shakya
 */
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

	// For Cart Page
	private Map<String, String> variants;
	private int cartQty;

	/**
	 * Constructor for creating a ProductModel with only basic informations.
	 *
	 * @param productId       unique product identifier
	 * @param name            product name
	 * @param price           original product price
	 * @param discountedPrice discounted price (if any)
	 */
	public ProductModel(int productId, String name, double price, double discountedPrice) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.discountedPrice = discountedPrice;
	}

	/**
	 * Constructor for creating a ProductModel with stock quantity info (used for
	 * cart and stock management).
	 *
	 * @param productId        unique product identifier
	 * @param name             product name
	 * @param shortDescription short text for quick view
	 * @param longDescription  detailed product description
	 * @param price            original product price
	 * @param discountedPrice  discounted price (if any)
	 * @param categoryId       category identifier
	 * @param stockQty         stock quantity available
	 */
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

	/**
	 * Constructor for use cases where the category name is required (e.g., listing
	 * with category name display).
	 *
	 * @param productId        unique product identifier
	 * @param name             product name
	 * @param shortDescription short text for quick view
	 * @param longDescription  detailed product description
	 * @param price            original product price
	 * @param discountedPrice  discounted price (if any)
	 * @param categoryId       category identifier
	 * @param category         category name
	 */
	public ProductModel(int productId, String name, String shortDescription, String longDescription, double price,
			double discountedPrice, int categoryId, int stockQty, String category) {
		super();
		this.productId = productId;
		this.name = name;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.price = price;
		this.discountedPrice = discountedPrice;
		this.categoryId = categoryId;
		this.stockQty = stockQty;
		this.category = category;
	}

	// Getters and Setters

	/**
	 * @return the product ID
	 */
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * @return the product name
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the short description
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	/**
	 * @return the long description
	 */
	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	/**
	 * @return the original price
	 */
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the discounted price
	 */
	public double getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public double getSalePrice() {
		if (this.discountedPrice != 0.0) {
			return discountedPrice;
		}
		return price;
	}

	/**
	 * @return the category ID
	 */
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the category name
	 */
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the available stock quantity
	 */
	public int getStockQty() {
		return stockQty;
	}

	public void setStockQty(int stockQty) {
		this.stockQty = stockQty;
	}

	public Map<String, String> getVariants() {
		return variants;
	}

	public void setVariants(Map<String, String> variants) {
		this.variants = variants;
	}

	public int getCartQty() {
		return cartQty;
	}

	public void setCartQty(int cartQty) {
		this.cartQty = cartQty;
	}
}
