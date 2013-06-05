package org.aspyct.orchestra.api.bluetooth;

import java.util.List;

public interface BluetoothMonitor {
	void watchDevice(BluetoothDevice device);
	void stopWatching(BluetoothDevice device);
	
	/**
	 * Only lists devices that are being watched
	 */
	List<BluetoothDevice> listDevices();
	boolean isAnyDevicePresent();
	boolean isPresent(BluetoothDevice device);
	
	void addBluetoothListener(BluetoothListener listener);
	void removeBluetoothListener(BluetoothListener listener);

	void decreaseBluetoothScanRate();
	void increaseBluetoothScanRate();
}
