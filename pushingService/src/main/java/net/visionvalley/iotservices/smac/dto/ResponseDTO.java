package net.visionvalley.iotservices.smac.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties
public class ResponseDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2873614974221627415L;
	private boolean OperationSucceeded;
	private int ErrorCode;
	private String ErrorDescription;
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

}
