package net.visionvalley.iot.smac.atemanagement.dto;

import java.io.Serializable;

public class ATEInstallationDTO implements Serializable{
private String ateTagNo;
private long transactionId;
public String getAteTagNo() {
	return ateTagNo;
}
public void setAteTagNo(String ateTagNo) {
	this.ateTagNo = ateTagNo;
}
public int getNumberOfInputs() {
	return numberOfInputs;
}
public void setNumberOfInputs(int numberOfInputs) {
	this.numberOfInputs = numberOfInputs;
}
private int numberOfInputs;
public long getTransactionId() {
	return transactionId;
}
public void setTransactionId(long transactionId) {
	this.transactionId = transactionId;
}
@Override
public String toString() {
	return "ATEInstallationDTO [ateTagNo=" + ateTagNo + ", transactionId=" + transactionId + ", numberOfInputs="
			+ numberOfInputs + "]";
}

}
