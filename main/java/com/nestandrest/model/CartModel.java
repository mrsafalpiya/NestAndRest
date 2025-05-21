package com.nestandrest.model;

/**
 * Model class representing a shopping cart.
 *
 * Contains information about the cart ID and the associated user ID.
 *
 * @author 23047584 Bhumika Karki
 */
public class CartModel {
	private int cartId;
	private int userId;

	/**
	 * Constructs a CartModel with the specified cart ID and user ID.
	 *
	 * @param cartId The unique identifier for the cart.
	 * @param userId The unique identifier for the user associated with the cart.
	 */
	public CartModel(int cartId, int userId) {
		super();
		this.cartId = cartId;
		this.userId = userId;
	}

	/**
	 * Gets the cart ID.
	 *
	 * @return The unique identifier for the cart.
	 */
	public int getCartId() {
		return cartId;
	}

	/**
	 * Sets the cart ID.
	 *
	 * @param cartId The unique identifier for the cart.
	 */
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	/**
	 * Gets the user ID associated with the cart.
	 *
	 * @return The unique identifier for the user.
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Sets the user ID associated with the cart.
	 *
	 * @param userId The unique identifier for the user.
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
