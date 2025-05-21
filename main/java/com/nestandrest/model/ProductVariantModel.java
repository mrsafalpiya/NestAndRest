package com.nestandrest.model;

import java.util.List;

/**
 * Represents the model for a product variant.
 * 
 * This class contains fields and methods to manage product variant details such
 * as the variant ID, name, and associated variant values.
 * 
 * @author 23047584 Bhumika Karki
 */
public class ProductVariantModel {
	// Unique identifier for the product variant
	private int productVariantId;

	// Name of the product variant
	private String variantName;

	// List of values associated with the product variant
	private List<ProductVariantValueModel> variantValues;

	/**
	 * Constructor to initialize all fields of the ProductVariantModel.
	 * 
	 * @param productVariantId the ID of the product variant
	 * @param variantName      the name of the product variant
	 * @param variantValues    the list of values associated with the product
	 *                         variant
	 */
	public ProductVariantModel(int productVariantId, String variantName, List<ProductVariantValueModel> variantValues) {
		super();
		this.productVariantId = productVariantId;
		this.variantName = variantName;
		this.variantValues = variantValues;
	}

	/**
	 * Gets the ID of the product variant.
	 * 
	 * @return the product variant ID
	 */
	public int getProductVariantId() {
		return productVariantId;
	}

	/**
	 * Sets the ID of the product variant.
	 * 
	 * @param productVariantId the product variant ID to set
	 */
	public void setProductVariantId(int productVariantId) {
		this.productVariantId = productVariantId;
	}

	/**
	 * Gets the name of the product variant.
	 * 
	 * @return the product variant name
	 */
	public String getVariantName() {
		return variantName;
	}

	/**
	 * Sets the name of the product variant.
	 * 
	 * @param variantName the product variant name to set
	 */
	public void setVariantName(String variantName) {
		this.variantName = variantName;
	}

	/**
	 * Gets the list of values associated with the product variant.
	 * 
	 * @return the list of product variant values
	 */
	public List<ProductVariantValueModel> getVariantValues() {
		return variantValues;
	}

	/**
	 * Sets the list of values associated with the product variant.
	 * 
	 * @param variantValues the list of product variant values to set
	 */
	public void setVariantValues(List<ProductVariantValueModel> variantValues) {
		this.variantValues = variantValues;
	}
}
