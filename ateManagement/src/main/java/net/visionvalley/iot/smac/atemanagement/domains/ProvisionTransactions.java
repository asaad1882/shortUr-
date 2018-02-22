package net.visionvalley.iot.smac.atemanagement.domains;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="DM_TAB_ALARMS")
public class ProvisionTransactions {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "DM_TAB_ALARMS_generator")
	@TableGenerator(name="DM_TAB_ALARMS_generator", table="id_generator", schema="history")
	@Column(name = "id", updatable = false, nullable = false)
	 private long id;
	@Column(name="INSERT_DATE",insertable = false, updatable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date insertDate;
	@Column(name="ATE_TAG_NO")
	private String ateTagNo;
	@Column(name="ALARM_PAYLOAD")	
	private String alarmPayload;
	@Column(name="UPDATE_DATE",insertable = false, updatable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date updateDate;
	private int state;
	@Column(name="transaction_id")
	private long transactionId;
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public String getAteTagNo() {
		return ateTagNo;
	}
	public void setAteTagNo(String ateTagNo) {
		this.ateTagNo = ateTagNo;
	}
	public String getAlarmPayload() {
		return alarmPayload;
	}
	public void setAlarmPayload(String alarmPayload) {
		this.alarmPayload = alarmPayload;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

}
