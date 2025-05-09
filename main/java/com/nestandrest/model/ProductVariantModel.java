package com.nestandrest.model;

import java.util.List;

public class ProductVariantModel {
	private int productVariantId;
	private String variantName;
	private List<ProductVariantValueModel> variantValues;

	public ProductVariantModel(int productVariantId, String variantName, List<ProductVariantValueModel> variantValues) {
		super();
		this.productVariantId = productVariantId;
		this.variantName = variantName;
		this.variantValues = variantValues;
	}

	public int getProductVariantId() {
		return productVariantId;
	}

	public void setProductVariantId(int productVariantId) {
		this.productVariantId = productVariantId;
	}

	public String getVariantName() {
		return variantName;
	}

	public void setVariantName(String variantName) {
		this.variantName = variantName;
	}

	public List<ProductVariantValueModel> getVariantValues() {
		return variantValues;
	}

	public void setVariantValues(List<ProductVariantValueModel> variantValues) {
		this.variantValues = variantValues;
	}
}
