package org.aspyct.orchestra.api.power;

public interface PowerManager {
	void switchOn();
	void switchOff();
	
	void addPowerListener(PowerListener listener);
	void removePowerListener(PowerListener listener);
	
	boolean isPowerOn();
}
