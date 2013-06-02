package org.aspyct.orchestra.api.power;

public interface PowerManager {
	void switchOn();
	void switchOff();
	void togglePower(boolean toggle);
	
	void addPowerListener(PowerListener listener);
}
