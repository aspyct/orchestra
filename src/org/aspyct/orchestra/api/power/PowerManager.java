package org.aspyct.orchestra.api.power;

public interface PowerManager {
	void powerOn();
	void powerOff();
	
	void addPowerListener(PowerListener listener);
	void removePowerListener(PowerListener listener);
	
	boolean isPowerOn();
}
