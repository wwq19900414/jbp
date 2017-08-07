package com.wuwenqi.java.valueObject;

import java.io.Serializable;

public class Order implements Serializable {
	private static final long serialVersionUID = -3088042522344230409L;
	private int orderId;
	private String clientName;
	private int number;
	private String productName;

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductName() {
		return productName;
	}
}
