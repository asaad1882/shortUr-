package net.visionvalley.iot.smac.atemanagement.dto;

import java.io.Serializable;
import java.util.Date;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDTO implements Serializable{
	@Id
	private String messageType;
	@Id
	private String ateTagNo;
	//private int errorCode;
	//private int ateMode;
	//private int alarmStatus;
	private Date timestamp;
	//private String extraInfo;
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
	
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	

}
