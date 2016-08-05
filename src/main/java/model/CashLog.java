package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CASH_LOG database table.
 * 
 */
@Entity
@Table(name="CASH_LOG")
@NamedQuery(name="CashLog.findAll", query="SELECT c FROM CashLog c")
public class CashLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="LOG_NUMBER")
	private String logNumber;

	@Column(name="\"MONEY\"")
	private BigDecimal money;

	@Temporal(TemporalType.DATE)
	@Column(name="\"TIME\"")
	private Date time;

	//bi-directional many-to-one association to UserData
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private UserData userData;

	public CashLog() {
	}

	public String getLogNumber() {
		return this.logNumber;
	}

	public void setLogNumber(String logNumber) {
		this.logNumber = logNumber;
	}

	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public UserData getUserData() {
		return this.userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

}