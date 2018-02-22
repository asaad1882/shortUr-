package net.visionvalley.iotservices.smac.dto;

import java.io.Serializable;
import java.util.Date;

public class MessageDTO implements Serializable{
	private String messageType;
	private String ateTagNo;
	private int errorCode;
	private int ateMode;
	private int alarmStatus;
	private Date timestamp;
	private String extraInfo;
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getAteTagNo() {
		return ateTagNo;
	}
	public void setAteTagNo(String ateTagNo) {
		this.ateTagNo = ateTagNo;
	}
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
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}
	@Override
	public String toString() {
		return "MessageDTO [messageType=" + messageType + ", ateTagNo=" + ateTagNo + ", errorCode=" + errorCode
				+ ", ateMode=" + ateMode + ", alarmStatus=" + alarmStatus + ", timestamp=" + timestamp + ", extraInfo="
				+ extraInfo + "]";
	}

}
