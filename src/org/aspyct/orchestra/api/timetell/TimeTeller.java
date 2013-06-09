package org.aspyct.orchestra.api.timetell;

public interface TimeTeller {
	boolean isDaytime();
	boolean isMorning();
	boolean isNight();
	
	void addTimeListener(TimeListener listener);
	void removeTimeListener(TimeListener listener);
}
