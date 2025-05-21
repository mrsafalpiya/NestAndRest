package com.nestandrest.model;

import java.sql.Timestamp;
import java.util.List;

public class OrderModel {
	private int orderId;
	private Timestamp orderDate;
	private int orderStatusId;
	private String orderStatus;
	private int cartId;
	private List<ProductModel> cartProducts;
	private int addressId;
	private UserAddressModel address;
	private double totalPrice;
	private UserModel user;

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

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(int orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public UserAddressModel getAddress() {
		return address;
	}

	public void setAddress(UserAddressModel address) {
		this.address = address;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<ProductModel> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(List<ProductModel> cartProducts) {
		this.cartProducts = cartProducts;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}
}
