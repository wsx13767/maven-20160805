package model;

import java.io.Serializable;

/**
 * The persistent class for the CASH_LOG database table.
 * 
 */
public class PptItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private int orderNumber;
	private String itemInformation;
	private String picture;

	public int getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getItemInformation() {
		return this.itemInformation;
	}

	public void setItemInformation(String itemInformation) {
		this.itemInformation = itemInformation;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getPicture() {
		return this.picture;
	}
}