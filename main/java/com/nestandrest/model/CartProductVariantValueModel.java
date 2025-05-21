package com.nestandrest.model;

/**
 * Represents the model for a cart product variant value.
 * 
 * Contains information about the cart, product, variant, and quantity.
 * 
 * @author 23047584 Bhumika Karki
 */
public class CartProductVariantValueModel {
	private int cartProductVariantValueId;
	private int cartId;
	private int productId;
	private int productVariantId;
	private int productVariantValueId;
	private int qty;

	/**
	 * Constructor to initialize all fields of the CartProductVariantValueModel.
	 * 
	 * @param cartProductVariantValueId the ID of the cart product variant value
	 * @param cartId                    the ID of the cart
	 * @param productId                 the ID of the product
	 * @param productVariantId          the ID of the product variant
	 * @param productVariantValueId     the ID of the product variant value
	 * @param qty                       the quantity of the product
	 */
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

	/**
	 * Constructor to initialize fields of the CartProductVariantValueModel except
	 * the ID.
	 * 
	 * @param cartId                the ID of the cart
	 * @param productId             the ID of the product
	 * @param productVariantId      the ID of the product variant
	 * @param productVariantValueId the ID of the product variant value
	 * @param qty                   the quantity of the product
	 */
	public CartProductVariantValueModel(int cartId, int productId, int productVariantId, int productVariantValueId,
			int qty) {
		super();
		this.cartId = cartId;
		this.productId = productId;
		this.productVariantId = productVariantId;
		this.productVariantValueId = productVariantValueId;
		this.qty = qty;
	}

	/**
	 * Gets the ID of the cart product variant value.
	 * 
	 * @return the cart product variant value ID
	 */
	public int getCartProductVariantValueId() {
		return cartProductVariantValueId;
	}

	/**
	 * Sets the ID of the cart product variant value.
	 * 
	 * @param cartProductVariantValueId the cart product variant value ID to set
	 */
	public void setCartProductVariantValueId(int cartProductVariantValueId) {
		this.cartProductVariantValueId = cartProductVariantValueId;
	}

	/**
	 * Gets the ID of the cart.
	 * 
	 * @return the cart ID
	 */
	public int getCartId() {
		return cartId;
	}

	/**
	 * Sets the ID of the cart.
	 * 
	 * @param cartId the cart ID to set
	 */
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	/**
	 * Gets the ID of the product.
	 * 
	 * @return the product ID
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * Sets the ID of the product.
	 * 
	 * @param productId the product ID to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
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
	 * Gets the quantity of the product.
	 * 
	 * @return the quantity
	 */
	public int getQty() {
		return qty;
	}

	/**
	 * Sets the quantity of the product.
	 * 
	 * @param qty the quantity to set
	 */
	public void setQty(int qty) {
		this.qty = qty;
	}
}
