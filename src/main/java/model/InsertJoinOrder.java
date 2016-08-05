package model;

import java.io.Serializable;



public class InsertJoinOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	private String jItem;

	private String jItemQuan;

	private String jPrice;

	private String jTotalPrice;

	private String jIndex;
	
	private String jItemNumber;
	
	private String jSize;
	
	private String jSuger;
	
	private String jIce;
	
	public InsertJoinOrder() {
	}

	public String getJItem() {
		return this.jItem;
	}

	public void setJItem(String jItem) {
		this.jItem = jItem;
	}

	public String getJItemQuan() {
		return this.jItemQuan;
	}

	public void setJItemQuan(String jItemQuan) {
		this.jItemQuan = jItemQuan;
	}

	public String getJPrice() {
		return this.jPrice;
	}

	public void setJPrice(String jPrice) {
		this.jPrice =jPrice;
	}
	
	public String getJTotalPrice() {
		return this.jTotalPrice;
	}

	public void setJTotalPrice(String jTotalPrice) {
		this.jTotalPrice =jTotalPrice;
	}
	public String getJIndex() {
		return this.jIndex;
	}

	public void setJIndex(String jIndex) {
		this.jIndex =jIndex;
	}
	public String getItemNumber() {
		return this.jItemNumber;
	}

	public void setItemNumber(String jItemNumber) {
		this.jItemNumber =jItemNumber;
	}
	public String getJSize() {
		return this.jSize;
	}

	public void setJSize(String jSize) {
		this.jSize =jSize;
	}
	public String getJSuger() {
		return this.jSuger;
	}

	public void setJSuger(String jSuger) {
		this.jSuger =jSuger;
	}
	public String getJIce() {
		return this.jIce;
	}

	public void setJIce(String jIce) {
		this.jIce =jIce;
	}	
}