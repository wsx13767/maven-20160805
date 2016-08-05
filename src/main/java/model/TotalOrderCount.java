package model;

import java.io.Serializable;

/**
 * The persistent class for the CASH_LOG database table.
 * 
 */
public class TotalOrderCount implements Serializable {
	private static final long serialVersionUID = 1L;
	private int orderCount;

	public int getOrderCount() {
		return this.orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
}