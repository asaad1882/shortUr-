package net.visionvalley.iot.smac.atemanagement.dto;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties
public class ResponseDTO implements Serializable{
	
	private boolean OperationSucceeded;
	private int ErrorCode;
	private String ErrorDescription;
	
	public ResponseDTO() {
	
	}
	public boolean isOperationSucceeded() {
		return OperationSucceeded;
	}
	public void setOperationSucceeded(boolean operationSucceeded) {
		OperationSucceeded = operationSucceeded;
	}
	public int getErrorCode() {
		return ErrorCode;
	}
	public void setErrorCode(int errorCode) {
		ErrorCode = errorCode;
	}
	public String getErrorDescription() {
		return ErrorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		ErrorDescription = errorDescription;
	}
	public ResponseDTO(boolean operationSucceeded, int errorCode, String errorDescription) {
		super();
		OperationSucceeded = operationSucceeded;
		ErrorCode = errorCode;
		ErrorDescription = errorDescription;
	}
	@Override
	public String toString() {
		return "ResponseDTO [OperationSucceeded=" + OperationSucceeded + ", ErrorCode=" + ErrorCode
				+ ", ErrorDescription=" + ErrorDescription + "]";
	}

}
