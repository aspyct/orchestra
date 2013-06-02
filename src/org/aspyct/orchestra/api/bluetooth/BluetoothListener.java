package org.aspyct.orchestra.api.bluetooth;

public interface BluetoothListener {
	void bluetoothDeviceArrives(BluetoothDevice device);
	void bluetoothDeviceLeaves(BluetoothDevice device);
}
