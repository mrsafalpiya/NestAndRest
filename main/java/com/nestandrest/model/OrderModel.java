package com.nestandrest.model;

import java.sql.Timestamp;
import java.util.List;

/**
 * Represents the model for an order.
 * 
 * This class contains fields and methods to manage order details such as order
 * ID, order date, status, associated cart, address, total price, and user
 * information.
 * 
 * @author 23048460 Safal Piya
 */
public class OrderModel {
	// Unique identifier for the order
	private int orderId;

	// Timestamp representing the date and time of the order
	private Timestamp orderDate;

	// Identifier for the order status
	private int orderStatusId;

	// Name of the order status
	private String orderStatus;

	// Identifier for the associated cart
	private int cartId;

	// List of products in the cart
	private List<ProductModel> cartProducts;

	// Identifier for the address associated with the order
	private int addressId;

	// Address details for the order
	private UserAddressModel address;

	// Total price of the order
	private double totalPrice;

	// User who placed the order
	private UserModel user;

	/**
	 * Constructor to initialize all fields of the OrderModel.
	 * 
	 * @param orderId       the ID of the order
	 * @param orderDate     the date and time of the order
	 * @param orderStatusId the ID of the order status
	 * @param orderStatus   the name of the order status
	 * @param cartId        the ID of the associated cart
	 * @param addressId     the ID of the associated address
	 * @param address       the address details
	 * @param totalPrice    the total price of the order
	 */
	public OrderModel(int orderId, Timestamp orderDate, int orderStatusId, String orderStatus, int cartId,
			int addressId, UserAddressModel address, double totalPrice) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderStatusId = orderStatusId;
		this.orderStatus = orderStatus;
		this.cartId = cartId;
		this.addressId = addressId;
		this.address = address;
		this.totalPrice = totalPrice;
	}

	/**
	 * Gets the ID of the order.
	 * 
	 * @return the order ID
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * Sets the ID of the order.
	 * 
	 * @param orderId the order ID to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**
	 * Gets the date and time of the order.
	 * 
	 * @return the order date and time
	 */
	public Timestamp getOrderDate() {
		return orderDate;
	}

	/**
	 * Sets the date and time of the order.
	 * 
	 * @param orderDate the order date and time to set
	 */
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * Gets the ID of the order status.
	 * 
	 * @return the order status ID
	 */
	public int getOrderStatusId() {
		return orderStatusId;
	}

	/**
	 * Sets the ID of the order status.
	 * 
	 * @param orderStatusId the order status ID to set
	 */
	public void setOrderStatusId(int orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	/**
	 * Gets the name of the order status.
	 * 
	 * @return the order status name
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * Sets the name of the order status.
	 * 
	 * @param orderStatus the order status name to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * Gets the ID of the associated cart.
	 * 
	 * @return the cart ID
	 */
	public int getCartId() {
		return cartId;
	}

	/**
	 * Sets the ID of the associated cart.
	 * 
	 * @param cartId the cart ID to set
	 */
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	/**
	 * Gets the ID of the associated address.
	 * 
	 * @return the address ID
	 */
	public int getAddressId() {
		return addressId;
	}

	/**
	 * Sets the ID of the associated address.
	 * 
	 * @param addressId the address ID to set
	 */
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	/**
	 * Gets the address details for the order.
	 * 
	 * @return the address details
	 */
	public UserAddressModel getAddress() {
		return address;
	}

	/**
	 * Sets the address details for the order.
	 * 
	 * @param address the address details to set
	 */
	public void setAddress(UserAddressModel address) {
		this.address = address;
	}

	/**
	 * Gets the total price of the order.
	 * 
	 * @return the total price
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * Sets the total price of the order.
	 * 
	 * @param totalPrice the total price to set
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * Gets the list of products in the cart.
	 * 
	 * @return the list of cart products
	 */
	public List<ProductModel> getCartProducts() {
		return cartProducts;
	}

	/**
	 * Sets the list of products in the cart.
	 * 
	 * @param cartProducts the list of cart products to set
	 */
	public void setCartProducts(List<ProductModel> cartProducts) {
		this.cartProducts = cartProducts;
	}

	/**
	 * Gets the user who placed the order.
	 * 
	 * @return the user details
	 */
	public UserModel getUser() {
		return user;
	}

	/**
	 * Sets the user who placed the order.
	 * 
	 * @param user the user details to set
	 */
	public void setUser(UserModel user) {
		this.user = user;
	}
}
