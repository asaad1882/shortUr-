package net.visionvalley.iot.smac.atemanagement.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties
public class AckMessage implements Serializable {
	
	private String ateTagNo;
	private int transactionId;
	private String transactionStatus;
	public String getAteTagNo() {
		return ateTagNo;
	}
	public void setAteTagNo(String ateTagNo) {
		this.ateTagNo = ateTagNo;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	@Override
	public String toString() {
		return "AckMessage [ateTagNo=" + ateTagNo + ", transactionId=" + transactionId + ", transactionStatus="
				+ transactionStatus + "]";
	}
	

}
