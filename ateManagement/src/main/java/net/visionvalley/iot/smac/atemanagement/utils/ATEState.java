package net.visionvalley.iot.smac.atemanagement.utils;

public enum ATEState {
	PROVISIONED(9),
	FAILEDACTIVATION(10),
	PAIRED(8),	
	INSTALLED(7),
	FAILEDINSTALLATION(6),
	PREACRIVATED(5),
	ACTIVE(4),
	FAILURE(3),
	DEACTIVATED(1),UNPAIRED(2);
	private final int value;

	ATEState(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }

}
