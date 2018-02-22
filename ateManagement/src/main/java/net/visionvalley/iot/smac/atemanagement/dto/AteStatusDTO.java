package net.visionvalley.iot.smac.atemanagement.dto;

import java.io.Serializable;

public class AteStatusDTO implements Serializable{
	
	private String ateTagNo;
	private int status;
	private String reason;
	private long transactionNum;
	public long getTransactionNum() {
		return transactionNum;
	}
	public void setTransactionNum(long transactionNum) {
		this.transactionNum = transactionNum;
	}
	public String getAteTagNo() {
		return ateTagNo;
	}
	public void setAteTagNo(String ateTagNo) {
		this.ateTagNo = ateTagNo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Override
	public String toString() {
		return "AteStatusDTO [ateTagNo=" + ateTagNo + ", status=" + status + ", reason=" + reason + ", transactionNum="
				+ transactionNum + "]";
	}

}
