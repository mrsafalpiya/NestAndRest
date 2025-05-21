package com.nestandrest.model;

public class OrderStatusModel {
	private int orderStatusId;
	private String name;

	public OrderStatusModel(int orderStatusId, String name) {
		super();
		this.orderStatusId = orderStatusId;
		this.name = name;
	}

	public int getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(int orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
