package org.aspyct.orchestra.api.bluetooth;

import java.util.Date;

public class BluetoothDevice implements Comparable<BluetoothDevice> {
	private String macAddr;
	private String name;
	private Date lastSeen;

	public BluetoothDevice() {
	}
	
	public BluetoothDevice(String macAddr) {
		super();
		this.macAddr = macAddr;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLastSeen() {
		return (Date) lastSeen.clone();
	}

	public void setLastSeen(Date lastSeen) {
		this.lastSeen = (Date) lastSeen.clone();
	}
}
