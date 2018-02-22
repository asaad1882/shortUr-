package net.visionvalley.iot.smac.atemanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HeartBeatDTO extends MessageDTO {
	private int errorCode;
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public int getAteState() {
		return ateState;
	}
	public void setAteState(int ateState) {
		this.ateState = ateState;
	}
	private int ateState;
	

}
