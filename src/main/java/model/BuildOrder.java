package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the BUILD_ORDER database table.
 * 
 */
@Entity
@Table(name="BUILD_ORDER")
@NamedQuery(name="BuildOrder.findAll", query="SELECT b FROM BuildOrder b")
public class BuildOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="ORDER_NUMBER")
	private String orderNumber;

	@Temporal(TemporalType.DATE)
	private Date deadline;

	@Column(name="ITEM_INFORMATION")
	private String itemInformation;

	@Lob
	private String picture;

	private String remark;
	
	//new column
	private Date buildTime;
	
	private String buildState;
	//---------
	
	//new
	private String buildTimeString;
	private String deadlineString;
	//

	//bi-directional many-to-one association to UserData
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private UserData userData;

	//bi-directional many-to-one association to ItemInformation
	@OneToMany(mappedBy="buildOrder")
	private List<ItemInformation> itemInformations;

	//bi-directional many-to-one association to JoinOrder
	@OneToMany(mappedBy="buildOrder")
	private List<JoinOrder> joinOrders;

	public BuildOrder() {
	}

	public String getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	public String getDeadlineString() {
		return this.deadlineString;
	}

	public void setDeadlineString(String deadlineString) {
		this.deadlineString = deadlineString;
	}

	public String getItemInformation() {
		return this.itemInformation;
	}

	public void setItemInformation(String itemInformation) {
		this.itemInformation = itemInformation;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	//new column
	public Date getBuildTime() {
		return this.buildTime;
	}
	
	public void setBuildTime(Date buildTime) {
		this.buildTime = buildTime;
	}
	
	public String getBuildTimeString() {
		return this.buildTimeString;
	}
	
	public void setBuildTimeString(String buildTimeString) {
		this.buildTimeString = buildTimeString;
	}
	
	public String getBuildState() {
		return this.buildState;
	}
	
	public void setBuildState(String buildState) {
		this.buildState = buildState;
	}
	//-----------

	public UserData getUserData() {
		return this.userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	public List<ItemInformation> getItemInformations() {
		return this.itemInformations;
	}

	public void setItemInformations(List<ItemInformation> itemInformations) {
		this.itemInformations = itemInformations;
	}

	public ItemInformation addItemInformation(ItemInformation itemInformation) {
		getItemInformations().add(itemInformation);
		itemInformation.setBuildOrder(this);

		return itemInformation;
	}

	public ItemInformation removeItemInformation(ItemInformation itemInformation) {
		getItemInformations().remove(itemInformation);
		itemInformation.setBuildOrder(null);

		return itemInformation;
	}

	public List<JoinOrder> getJoinOrders() {
		return this.joinOrders;
	}

	public void setJoinOrders(List<JoinOrder> joinOrders) {
		this.joinOrders = joinOrders;
	}

	public JoinOrder addJoinOrder(JoinOrder joinOrder) {
		getJoinOrders().add(joinOrder);
		joinOrder.setBuildOrder(this);

		return joinOrder;
	}

	public JoinOrder removeJoinOrder(JoinOrder joinOrder) {
		getJoinOrders().remove(joinOrder);
		joinOrder.setBuildOrder(null);

		return joinOrder;
	}

}