package net.visionvalley.iot.smac.cache.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Device implements Serializable{
	private String macAddress;
	private String deviceTagNo;
	private String status;
	private String deviceId;
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
	
}
