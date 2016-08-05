package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the USER_DATA database table.
 * 
 */
@Entity
@Table(name="USER_DATA")
@NamedQuery(name="UserData.findAll", query="SELECT u FROM UserData u")
public class UserData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="USER_ID")
	private long userId;

	private String account;

	private String email;

	@Column(name="\"MONEY\"")
	private BigDecimal money;

	private String name;

	private String password;

	private String phone;

	//bi-directional many-to-one association to BuildOrder
	@OneToMany(mappedBy="userData")
	private List<BuildOrder> buildOrders;

	//bi-directional many-to-one association to CashLog
	@OneToMany(mappedBy="userData")
	private List<CashLog> cashLogs;

	//bi-directional many-to-one association to ItemInformation
	@OneToMany(mappedBy="userData")
	private List<ItemInformation> itemInformations;

	//bi-directional many-to-one association to JoinOrder
	@OneToMany(mappedBy="userData")
	private List<JoinOrder> joinOrders;

	public UserData() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<BuildOrder> getBuildOrders() {
		return this.buildOrders;
	}

	public void setBuildOrders(List<BuildOrder> buildOrders) {
		this.buildOrders = buildOrders;
	}

	public BuildOrder addBuildOrder(BuildOrder buildOrder) {
		getBuildOrders().add(buildOrder);
		buildOrder.setUserData(this);

		return buildOrder;
	}

	public BuildOrder removeBuildOrder(BuildOrder buildOrder) {
		getBuildOrders().remove(buildOrder);
		buildOrder.setUserData(null);

		return buildOrder;
	}

	public List<CashLog> getCashLogs() {
		return this.cashLogs;
	}

	public void setCashLogs(List<CashLog> cashLogs) {
		this.cashLogs = cashLogs;
	}

	public CashLog addCashLog(CashLog cashLog) {
		getCashLogs().add(cashLog);
		cashLog.setUserData(this);

		return cashLog;
	}

	public CashLog removeCashLog(CashLog cashLog) {
		getCashLogs().remove(cashLog);
		cashLog.setUserData(null);

		return cashLog;
	}

	public List<ItemInformation> getItemInformations() {
		return this.itemInformations;
	}

	public void setItemInformations(List<ItemInformation> itemInformations) {
		this.itemInformations = itemInformations;
	}

	public ItemInformation addItemInformation(ItemInformation itemInformation) {
		getItemInformations().add(itemInformation);
		itemInformation.setUserData(this);

		return itemInformation;
	}

	public ItemInformation removeItemInformation(ItemInformation itemInformation) {
		getItemInformations().remove(itemInformation);
		itemInformation.setUserData(null);

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
		joinOrder.setUserData(this);

		return joinOrder;
	}

	public JoinOrder removeJoinOrder(JoinOrder joinOrder) {
		getJoinOrders().remove(joinOrder);
		joinOrder.setUserData(null);

		return joinOrder;
	}

}