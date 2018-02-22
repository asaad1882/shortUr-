package net.visionvalley.iot.smac.atemanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AlarmMessageDTO extends MessageDTO{
	private int errorCode;
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public int getAteMode() {
		return ateMode;
	}
	public void setAteMode(int ateMode) {
		this.ateMode = ateMode;
	}
	public int getAlarmStatus() {
		return alarmStatus;
	}
	public void setAlarmStatus(int alarmStatus) {
		this.alarmStatus = alarmStatus;
	}
	public String getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}
	private int ateMode;
	private int alarmStatus;
	private String extraInfo;
}
