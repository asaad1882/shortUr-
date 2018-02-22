package net.visionvalley.iot.smac.atemanagement.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Device implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7572337883150133500L;
	private String macAddress;
	private String deviceTagNo;
	private String status;
	private String deviceId;
	private int actualNumberOfInputs;
	private String deviceClass;
	private String via;
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public String getDeviceTagNo() {
		return deviceTagNo;
	}
	public String getStatus() {
		return status;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public void setDeviceTagNo(String deviceTagNo) {
		this.deviceTagNo = deviceTagNo;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getActualNumberOfInputs() {
		return actualNumberOfInputs;
	}
	public void setActualNumberOfInputs(int actualNumberOfInputs) {
		this.actualNumberOfInputs = actualNumberOfInputs;
	}
	public String getDeviceClass() {
		return deviceClass;
	}
	public void setDeviceClass(String deviceClass) {
		this.deviceClass = deviceClass;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	
}
