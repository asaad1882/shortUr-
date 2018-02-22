package net.visionvalley.iot.smac.atemanagement.dto;

import java.io.Serializable;
import java.util.List;

public class ProvisionMessage implements Serializable{
	private String macAddress;
	private long deviceId;
	public long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
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
	
	public int getNumberOfInput() {
		return numberOfInput;
	}
	public void setNumberOfInput(int numberOfInput) {
		this.numberOfInput = numberOfInput;
	}
	private String ateTagNo;
	private List<AlarmMessageDTO> alarmMessageDTOs;
	
	private int numberOfInput;
	public List<AlarmMessageDTO> getAlarmMessageDTOs() {
		return alarmMessageDTOs;
	}
	public void setAlarmMessageDTOs(List<AlarmMessageDTO> alarmMessageDTOs) {
		this.alarmMessageDTOs = alarmMessageDTOs;
	}
}
