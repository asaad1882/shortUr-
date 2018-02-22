package net.visionvalley.iotservices.smac.queuesconfiguarions;

public interface Queue {
	public final static String SMAC_RECEIVED = "/smacAPI";
	public final static String SMAC_RECEIVED_AUTHORIZED_PATTERN  = "/smacAPI/**";
	public final static String SMAC_SENT = "/usr/smacAPI/alarms";
	public final static String SMAC_SENT_AUTHORIZED_PATTERN = "/smacAPI/**";
	public final static String SMAC_SENT_V2_AUTHORIZED_PATTERN = "/usr/smacAPI/**";
	public final static String SMAC_SENT_V2 = "/smacAPI/alarms";
	

}
