package model;

import java.io.Serializable;

/**
 * The persistent class for the CASH_LOG database table.
 * 
 */
public class IndexItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private int orderNumber;
	private String itemInformation;
	private String remark;
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
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark() {
		return this.remark;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getPicture() {
		return this.picture;
	}
}