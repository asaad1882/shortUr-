package net.visionvalley.iot.smac.atemanagement.utils;

public enum TransactionState {
	SUCCESS(2),
	FAILED(1),
	PENDING(0);
	private final int value;

	TransactionState(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }

}
