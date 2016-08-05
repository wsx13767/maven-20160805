package model;

import java.math.BigDecimal;
import java.sql.Date;

public class PaySearch{
	private int userId;
	private Date time;
	private BigDecimal moneyAdd;
	private BigDecimal money;
	private String itemName;
	private BigDecimal count;
	
	public int getUserId(){
		return this.userId;
	}
	public void setUserId(int userId){
		this.userId = userId;
	}
	
	public Date getTime(){
		return this.time;
	}
	public void setTime(Date time){
		this.time = time;
	}
	
	public BigDecimal getMoneyAdd(){
		return this.moneyAdd;
	}
	public void setMoneyAdd(BigDecimal moneyAdd){
		this.moneyAdd = moneyAdd;
	}
	
	public BigDecimal getMoney(){
		return this.money;
	}
	public void setMoney(BigDecimal money){
		this.money = money;
	}
	
	public String getItemName(){
		return this.itemName;
	}
	public void setItemName(String itemname){
		this.itemName = itemname;
	}
	
	public BigDecimal getCount(){
		return this.count;
	}
	public void setCount(BigDecimal count){
		this.count = count;
	}
	
}
