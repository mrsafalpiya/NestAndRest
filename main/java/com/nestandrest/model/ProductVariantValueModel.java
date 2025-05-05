package com.nestandrest.model;

public class ProductVariantValueModel {
	private int productVariantValueId;
	private int productVariantId;
	private String variantValue;

	public ProductVariantValueModel(int productVariantValueId, int productVariantId, String variantValue) {
		super();
		this.productVariantValueId = productVariantValueId;
		this.productVariantId = productVariantId;
		this.variantValue = variantValue;
	}

	public int getProductVariantValueId() {
		return productVariantValueId;
	}

	public void setProductVariantValueId(int productVariantValueId) {
		this.productVariantValueId = productVariantValueId;
	}

	public int getProductVariantId() {
		return productVariantId;
	}

	public void setProductVariantId(int productVariantId) {
		this.productVariantId = productVariantId;
	}

	public String getVariantValue() {
		return variantValue;
	}

	public void setVariantValue(String variantValue) {
		this.variantValue = variantValue;
	}
}
