package org.aspyct.orchestra.api.bluetooth;

public interface BluetoothListener {
	void deviceArrives(BluetoothDevice device);
	void deviceLeaves(BluetoothDevice device);
}
