package net.visionvalley.iot.smac.atemanagement.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties
public class AteDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4820950636961357670L;
	private String macAddress;
	private int buildingClass;
	private int networkProtoclIdentifier;
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
	public int getBuildingClass() {
		return buildingClass;
	}
	public void setBuildingClass(int buildingClass) {
		this.buildingClass = buildingClass;
	}
	public int getNetworkProtoclIdentifier() {
		return networkProtoclIdentifier;
	}
	public void setNetworkProtoclIdentifier(int networkProtoclIdentifier) {
		this.networkProtoclIdentifier = networkProtoclIdentifier;
	}
	@Override
	public String toString() {
		return "AteDTO [macAddress=" + macAddress + ", buildingClass=" + buildingClass + ", networkProtoclIdentifier="
				+ networkProtoclIdentifier + ", transactionNum=" + transactionNum + "]";
	}

}
