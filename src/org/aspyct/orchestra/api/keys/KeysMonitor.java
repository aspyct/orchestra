package org.aspyct.orchestra.api.keys;

public interface KeysMonitor {
	void addKeysListener(KeysListener listener);
	void removeKeysListener(KeysListener listener);
	boolean areKeysPresent();
}
