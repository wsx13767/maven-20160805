package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the JOIN_ORDER database table.
 * 
 */
@Entity
@Table(name="JOIN_ORDER")
@NamedQuery(name="JoinOrder.findAll", query="SELECT j FROM JoinOrder j")
public class JoinOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="JOIN_LOG")
	private String joinLog;

	@Column(name="\"COUNT\"")
	private BigDecimal count;

	@Column(name="\"GET\"")
	private BigDecimal get;

	@Temporal(TemporalType.DATE)
	@Column(name="JOIN_TIME")
	private Date joinTime;
	
	private String joinTimeString;

	@Column(name="\"MONEY\"")
	private BigDecimal money;

	//bi-directional many-to-one association to BuildOrder
	@ManyToOne
	@JoinColumn(name="ORDER_NUMBER")
	private BuildOrder buildOrder;

	//bi-directional many-to-one association to ItemInformation
	@ManyToOne
	@JoinColumn(name="ITEM_NUMBER")
	private ItemInformation itemInformation;

	//bi-directional many-to-one association to UserData
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private UserData userData;
	
	@Column(name="\"SIZE\"")
	private String size;

	private String suger;
	
	private String ice;

	public JoinOrder() {
	}

	public String getJoinLog() {
		return this.joinLog;
	}

	public void setJoinLog(String joinLog) {
		this.joinLog = joinLog;
	}

	public BigDecimal getCount() {
		return this.count;
	}

	public void setCount(BigDecimal count) {
		this.count = count;
	}

	public BigDecimal getGet() {
		return this.get;
	}

	public void setGet(BigDecimal get) {
		this.get = get;
	}

	public Date getJoinTime() {
		return this.joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
	
	public String getJoinTimeString(){
		return this.joinTimeString;
	}
	
	public void setJoinTimeString(String joinTimeString){
		this.joinTimeString = joinTimeString;
	}

	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	public String getSuger() {
		return this.suger;
	}

	public void setSuger(String suger) {
		this.suger = suger;
	}
	
	public String getIce() {
		return this.ice;
	}

	public void setIce(String ice) {
		this.ice = ice;
	}

	public BuildOrder getBuildOrder() {
		return this.buildOrder;
	}

	public void setBuildOrder(BuildOrder buildOrder) {
		this.buildOrder = buildOrder;
	}

	public ItemInformation getItemInformation() {
		return this.itemInformation;
	}

	public void setItemInformation(ItemInformation itemInformation) {
		this.itemInformation = itemInformation;
	}

	public UserData getUserData() {
		return this.userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

}