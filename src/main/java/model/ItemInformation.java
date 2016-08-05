package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the ITEM_INFORMATION database table.
 * 
 */
@Entity
@Table(name="ITEM_INFORMATION")
@NamedQuery(name="ItemInformation.findAll", query="SELECT i FROM ItemInformation i")
public class ItemInformation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="ITEM_NUMBER")
	private String itemNumber;

	private String ice;

	@Column(name="\"MAX\"")
	private BigDecimal max;

	@Column(name="\"MIN\"")
	private BigDecimal min;

	@Column(name="\"MONEY\"")
	private BigDecimal money;

	private String name;

	@Column(name="\"SIZE\"")
	private String size;

	private String suger;
	
	
	//new column
	private BigDecimal bigPrice;
	
	private BigDecimal midPrice;
	
	private BigDecimal smallPrice;
	//---------

	//bi-directional many-to-one association to BuildOrder
	@ManyToOne
	@JoinColumn(name="ORDER_NUMBER")
	private BuildOrder buildOrder;

	//bi-directional many-to-one association to UserData
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private UserData userData;

	//bi-directional many-to-one association to JoinOrder
	@OneToMany(mappedBy="itemInformation")
	private List<JoinOrder> joinOrders;

	public ItemInformation() {
	}

	public String getItemNumber() {
		return this.itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getIce() {
		return this.ice;
	}

	public void setIce(String ice) {
		this.ice = ice;
	}

	public BigDecimal getMax() {
		return this.max;
	}

	public void setMax(BigDecimal max) {
		this.max = max;
	}

	public BigDecimal getMin() {
		return this.min;
	}

	public void setMin(BigDecimal min) {
		this.min = min;
	}

	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	//new column
	public BigDecimal getBigPrice() {
		return this.bigPrice;
	}
	
	public void setBigPrice(BigDecimal bigPrice) {
		this.bigPrice = bigPrice;
	}
	
	public BigDecimal getMidPrice() {
		return this.midPrice;
	}
	
	public void setMidPrice(BigDecimal midPrice) {
		this.midPrice = midPrice;
	}
	
	public BigDecimal getSmallPrice() {
		return this.smallPrice;
	}
	
	public void setSmallPrice(BigDecimal smallPrice) {
		this.smallPrice = smallPrice;
	}
	//---------

	public BuildOrder getBuildOrder() {
		return this.buildOrder;
	}

	public void setBuildOrder(BuildOrder buildOrder) {
		this.buildOrder = buildOrder;
	}

	public UserData getUserData() {
		return this.userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	public List<JoinOrder> getJoinOrders() {
		return this.joinOrders;
	}

	public void setJoinOrders(List<JoinOrder> joinOrders) {
		this.joinOrders = joinOrders;
	}

	public JoinOrder addJoinOrder(JoinOrder joinOrder) {
		getJoinOrders().add(joinOrder);
		joinOrder.setItemInformation(this);

		return joinOrder;
	}

	public JoinOrder removeJoinOrder(JoinOrder joinOrder) {
		getJoinOrders().remove(joinOrder);
		joinOrder.setItemInformation(null);

		return joinOrder;
	}

}