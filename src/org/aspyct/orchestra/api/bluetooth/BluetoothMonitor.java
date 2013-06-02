package org.aspyct.orchestra.api.bluetooth;

import java.util.List;

public interface BluetoothMonitor {
	List<BluetoothDevice> listDevices();
	boolean isPresent(BluetoothDevice device);
	
	void addBluetoothListener(BluetoothListener listener, BluetoothDevice device);
	void removeBluetoothListener(BluetoothListener listener, BluetoothDevice device);
}
