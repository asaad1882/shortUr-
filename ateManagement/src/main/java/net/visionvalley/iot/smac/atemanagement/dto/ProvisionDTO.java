package net.visionvalley.iot.smac.atemanagement.dto;

import java.io.Serializable;
import java.util.List;



public class ProvisionDTO implements Serializable{
	private String ateTagNo;
	public int getNumberOfInput() {
		return numberOfInput;
	}
	public void setNumberOfInput(int numberOfInput) {
		this.numberOfInput = numberOfInput;
	}
	
	private List<AlarmMessageDTO> alarmMessageDTOs;
	
	private int numberOfInput;
	public List<AlarmMessageDTO> getAlarmMessageDTOs() {
		return alarmMessageDTOs;
	}
	public void setAlarmMessageDTOs(List<AlarmMessageDTO> alarmMessageDTOs) {
		this.alarmMessageDTOs = alarmMessageDTOs;
	}
	public String getAteTagNo() {
		return ateTagNo;
	}
	public void setAteTagNo(String ateTagNo) {
		this.ateTagNo = ateTagNo;
	}

}
