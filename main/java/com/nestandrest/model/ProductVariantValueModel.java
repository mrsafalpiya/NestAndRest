package com.nestandrest.model;

/**
 * Represents the model for a product variant value.
 * 
 * This class contains fields and methods to manage product variant value
 * details such as the variant value ID, associated variant ID, and the value
 * itself.
 * 
 * @author 23047584 Bhumika Karki
 */
public class ProductVariantValueModel {
	// Unique identifier for the product variant value
	private int productVariantValueId;

	// Identifier for the associated product variant
	private int productVariantId;

	// The value of the product variant
	private String variantValue;

	/**
	 * Constructor to initialize all fields of the ProductVariantValueModel.
	 * 
	 * @param productVariantValueId the ID of the product variant value
	 * @param productVariantId      the ID of the associated product variant
	 * @param variantValue          the value of the product variant
	 */
	public ProductVariantValueModel(int productVariantValueId, int productVariantId, String variantValue) {
		super();
		this.productVariantValueId = productVariantValueId;
		this.productVariantId = productVariantId;
		this.variantValue = variantValue;
	}

	/**
	 * Gets the ID of the product variant value.
	 * 
	 * @return the product variant value ID
	 */
	public int getProductVariantValueId() {
		return productVariantValueId;
	}

	/**
	 * Sets the ID of the product variant value.
	 * 
	 * @param productVariantValueId the product variant value ID to set
	 */
	public void setProductVariantValueId(int productVariantValueId) {
		this.productVariantValueId = productVariantValueId;
	}

	/**
	 * Gets the ID of the associated product variant.
	 * 
	 * @return the product variant ID
	 */
	public int getProductVariantId() {
		return productVariantId;
	}

	/**
	 * Sets the ID of the associated product variant.
	 * 
	 * @param productVariantId the product variant ID to set
	 */
	public void setProductVariantId(int productVariantId) {
		this.productVariantId = productVariantId;
	}

	/**
	 * Gets the value of the product variant.
	 * 
	 * @return the product variant value
	 */
	public String getVariantValue() {
		return variantValue;
	}

	/**
	 * Sets the value of the product variant.
	 * 
	 * @param variantValue the product variant value to set
	 */
	public void setVariantValue(String variantValue) {
		this.variantValue = variantValue;
	}
}
