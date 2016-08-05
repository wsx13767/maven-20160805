package model;

import java.math.BigDecimal;

public class SearchFoodModel {
	private String orderNumber;
	private String remark;
	private String iteminformation;
	private String picture;
	private String name;
	private BigDecimal max;
	private BigDecimal min;
	private BigDecimal money;
	private String ice;
	private String suger;
	private String size;
	private BigDecimal bigprice;
	private BigDecimal midprice;
	private BigDecimal smallprice;
	
	
	public void setIce(String ice){
		this.ice =ice;
	}
	public String getIce(){
		return this.ice;
	}
	
	
	public void setSuger(String suger){
		this.suger =suger;
	}
	public String getSuger(){
		return this.suger;
	}
	
	public void setSize(String size){
		this.size =size;
	}
	public String getSize(){
		return this.size;
	}
	
	public void setBigprice(BigDecimal bigprice){
		this.bigprice = bigprice;
	}
	public BigDecimal getBigprice(){
		return this.bigprice;
	}
	
	public void setMidprice(BigDecimal midprice){
		this.midprice = midprice;
	}
	public BigDecimal getMidprice(){
		return this.midprice;
	}
	
	public void setSmallprice(BigDecimal smallprice){
		this.smallprice = smallprice;
	}
	public BigDecimal getSmallprice(){
		return this.smallprice;
	}
	
	
	
	
	
	
	
	
	public void setOrderNumber(String orderNumber){
		this.orderNumber = orderNumber;
	}
	public String getOrderNumber(){
		return this.orderNumber;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	public String getRemark(){
		return this.remark;
	}
	
	public void setIteminformation(String iteminformation){
		this.iteminformation = iteminformation;
	}
	public String getIteminformation(){
		return this.iteminformation;
	}
	
	public void setPicture(String picture){
		this.picture = picture;
	}
	public String getPicture(){
		return this.picture;
	}
	
	public void setMoney(BigDecimal money){
		this.money = money;
	}
	public BigDecimal getMoney(){
		return this.money;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	
	public void setMax(BigDecimal max){
		this.max = max;
	}
	public BigDecimal getMax(){
		return this.max;
	}
	
	public void setMin(BigDecimal min){
		this.min = min;
	}
	public BigDecimal getMin(){
		return this.min;
	}
	
	
	
	
	
	
	
	
}
