package com.nestandrest.model;

public class CartProductVariantValueModel {
	private int cartProductVariantValueId;
	private int cartId;
	private int productId;
	private int productVariantId;
	private int productVariantValueId;
	private int qty;

	public CartProductVariantValueModel(int cartProductVariantValueId, int cartId, int productId, int productVariantId,
			int productVariantValueId, int qty) {
		super();
		this.cartProductVariantValueId = cartProductVariantValueId;
		this.cartId = cartId;
		this.productId = productId;
		this.productVariantId = productVariantId;
		this.productVariantValueId = productVariantValueId;
		this.qty = qty;
	}

	public CartProductVariantValueModel(int cartId, int productId, int productVariantId, int productVariantValueId,
			int qty) {
		super();
		this.cartId = cartId;
		this.productId = productId;
		this.productVariantId = productVariantId;
		this.productVariantValueId = productVariantValueId;
		this.qty = qty;
	}

	public int getCartProductVariantValueId() {
		return cartProductVariantValueId;
	}

	public void setCartProductVariantValueId(int cartProductVariantValueId) {
		this.cartProductVariantValueId = cartProductVariantValueId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductVariantId() {
		return productVariantId;
	}

	public void setProductVariantId(int productVariantId) {
		this.productVariantId = productVariantId;
	}

	public int getProductVariantValueId() {
		return productVariantValueId;
	}

	public void setProductVariantValueId(int productVariantValueId) {
		this.productVariantValueId = productVariantValueId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

}
