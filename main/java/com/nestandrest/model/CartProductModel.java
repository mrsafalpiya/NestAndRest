package com.nestandrest.model;

public class CartProductModel {
	private int cartId;
	private int productId;

	public CartProductModel(int cartId, int productId) {
		super();
		this.cartId = cartId;
		this.productId = productId;
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
}
