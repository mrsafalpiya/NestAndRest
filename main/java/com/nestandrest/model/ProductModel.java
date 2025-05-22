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
	 * Empty constructor.
	 */
	public ProductModel() {
	}

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
	 * @param stockQty         stock quantity available
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
	 * Gets the product ID.
	 *
	 * @return the product ID
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * Sets the product ID.
	 *
	 * @param productId the product ID to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * Gets the product name.
	 *
	 * @return the product name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the product name.
	 *
	 * @param name the product name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the short description.
	 *
	 * @return the short description
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * Sets the short description.
	 *
	 * @param shortDescription the short description to set
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	/**
	 * Gets the long description.
	 *
	 * @return the long description
	 */
	public String getLongDescription() {
		return longDescription;
	}

	/**
	 * Sets the long description.
	 *
	 * @param longDescription the long description to set
	 */
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	/**
	 * Gets the original price.
	 *
	 * @return the original price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the original price.
	 *
	 * @param price the original price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets the discounted price.
	 *
	 * @return the discounted price
	 */
	public double getDiscountedPrice() {
		return discountedPrice;
	}

	/**
	 * Sets the discounted price.
	 *
	 * @param discountedPrice the discounted price to set
	 */
	public void setDiscountedPrice(double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	/**
	 * Gets the sale price. If discounted price is set, returns discounted price; otherwise returns original price.
	 *
	 * @return the sale price
	 */
	public double getSalePrice() {
		if (this.discountedPrice != 0.0) {
			return discountedPrice;
		}
		return price;
	}

	/**
	 * Gets the category ID.
	 *
	 * @return the category ID
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * Sets the category ID.
	 *
	 * @param categoryId the category ID to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * Gets the category name.
	 *
	 * @return the category name
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the category name.
	 *
	 * @param category the category name to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Gets the available stock quantity.
	 *
	 * @return the available stock quantity
	 */
	public int getStockQty() {
		return stockQty;
	}

	/**
	 * Sets the available stock quantity.
	 *
	 * @param stockQty the stock quantity to set
	 */
	public void setStockQty(int stockQty) {
		this.stockQty = stockQty;
	}

	/**
	 * Gets the product variants.
	 *
	 * @return the variants map
	 */
	public Map<String, String> getVariants() {
		return variants;
	}

	/**
	 * Sets the product variants.
	 *
	 * @param variants the variants map to set
	 */
	public void setVariants(Map<String, String> variants) {
		this.variants = variants;
	}

	/**
	 * Gets the cart quantity.
	 *
	 * @return the cart quantity
	 */
	public int getCartQty() {
		return cartQty;
	}

	/**
	 * Sets the cart quantity.
	 *
	 * @param cartQty the cart quantity to set
	 */
	public void setCartQty(int cartQty) {
		this.cartQty = cartQty;
	}
}
