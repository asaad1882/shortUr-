package net.visionvalley.iot.smac.atemanagement.dto;

import java.io.Serializable;

public class ATEPairingDTO implements Serializable{
	
	private String macAddress;
	private String ateTagNo;
	private String emirate;
	private String areaCode;
	private long transactionNum;
	public long getTransactionNum() {
		return transactionNum;
	}
	public void setTransactionNum(long transactionNum) {
		this.transactionNum = transactionNum;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public String getAteTagNo() {
		return ateTagNo;
	}
	public void setAteTagNo(String ateTagNo) {
		this.ateTagNo = ateTagNo;
	}
	public String getEmirate() {
		return emirate;
	}
	public void setEmirate(String emirate) {
		this.emirate = emirate;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	@Override
	public String toString() {
		return "ATEPairingDTO [macAddress=" + macAddress + ", ateTagNo=" + ateTagNo + ", emirate=" + emirate
				+ ", areaCode=" + areaCode + ", transactionNum=" + transactionNum + "]";
	}
	

}
