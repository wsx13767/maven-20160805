package model;

import java.math.BigDecimal;

public class SaleTotal {
	private String name;
	private BigDecimal price;
	private long count;
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public BigDecimal getPrice(){
		return this.price;
	}
	
	public void setPrice(BigDecimal price){
		this.price = price;
	}
	
	public long getCount(){
		return this.count;
	}
	
	public void setCount(long count){
		this.count = count;
	}
	
}
