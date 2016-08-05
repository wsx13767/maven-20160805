package model;

import java.io.Serializable;

/**
 * The persistent class for the CASH_LOG database table.
 * 
 */
public class CountJoinItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private int countJoinItem;

	public int getCountJoinItem() {
		return this.countJoinItem;
	}

	public void setCountJoinItem(int countJoinItem) {
		this.countJoinItem = countJoinItem;
	}

}