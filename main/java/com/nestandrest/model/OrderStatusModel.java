package com.nestandrest.model;

/**
 * Represents the model for an order status.
 * 
 * This class contains fields and methods to manage order status details such as
 * the status ID and name.
 * 
 * @author 23048460 Safal Piya
 */
public class OrderStatusModel {
	// Unique identifier for the order status
	private int orderStatusId;

	// Name of the order status
	private String name;

	/**
	 * Constructor to initialize all fields of the OrderStatusModel.
	 * 
	 * @param orderStatusId the ID of the order status
	 * @param name          the name of the order status
	 */
	public OrderStatusModel(int orderStatusId, String name) {
		super();
		this.orderStatusId = orderStatusId;
		this.name = name;
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
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the order status.
	 * 
	 * @param name the order status name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
