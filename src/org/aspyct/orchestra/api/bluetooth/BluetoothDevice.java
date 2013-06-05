package org.aspyct.orchestra.api.bluetooth;

public class BluetoothDevice implements Comparable<BluetoothDevice> {
	private String macAddr;

	public String getMacAddr() {
		return macAddr;
	}

	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}

	@Override
	public int compareTo(BluetoothDevice other) {
		return other.macAddr.compareTo(macAddr);
	}
}
