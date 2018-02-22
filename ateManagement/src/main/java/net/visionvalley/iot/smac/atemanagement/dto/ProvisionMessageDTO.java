package net.visionvalley.iot.smac.atemanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProvisionMessageDTO extends MessageDTO{
	private long transactionId;
	private int numberOfInput;
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public int getNumberOfInput() {
		return numberOfInput;
	}
	public void setNumberOfInput(int numberOfInput) {
		this.numberOfInput = numberOfInput;
	}

}
