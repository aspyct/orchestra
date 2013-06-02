package org.aspyct.orchestra.maestro;

import org.aspyct.orchestra.api.bluetooth.BluetoothDevice;
import org.aspyct.orchestra.api.bluetooth.BluetoothListener;
import org.aspyct.orchestra.api.bluetooth.BluetoothMonitor;
import org.aspyct.orchestra.api.keys.KeysListener;
import org.aspyct.orchestra.api.keys.KeysMonitor;
import org.aspyct.orchestra.api.music.MusicListener;
import org.aspyct.orchestra.api.music.MusicManager;
import org.aspyct.orchestra.api.power.PowerListener;
import org.aspyct.orchestra.api.power.PowerManager;
import org.aspyct.orchestra.api.timetell.TimeTeller;

public class Maestro implements BluetoothListener, PowerListener, KeysListener, MusicListener {
	private MusicManager musicManager;
	private PowerManager powerManager;
	private TimeTeller timeTeller;
	private BluetoothMonitor bluetoothMonitor;
	private KeysMonitor keysMonitor;
	
	@Override
	public void bluetoothDeviceArrives(BluetoothDevice device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bluetoothDeviceLeaves(BluetoothDevice device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keysPluggedIn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keysUnplugged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void powerSwitchedOn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void powerSwitchedOff() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void musicStartedPlaying() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void musicStoppedPlaying() {
		// TODO Auto-generated method stub		
	}

	public TimeTeller getTimeTeller() {
		return timeTeller;
	}

	public void setTimeTeller(TimeTeller timeTeller) {
		this.timeTeller = timeTeller;
	}

	public PowerManager getPowerManager() {
		return powerManager;
	}

	public void setPowerManager(PowerManager powerManager) {
		this.powerManager = powerManager;
	}

	public MusicManager getMusicPlayer() {
		return musicManager;
	}

	public void setMusicPlayer(MusicManager musicManager) {
		this.musicManager = musicManager;
	}

	public BluetoothMonitor getBluetoothMonitor() {
		return bluetoothMonitor;
	}

	public void setBluetoothMonitor(BluetoothMonitor bluetoothMonitor) {
		this.bluetoothMonitor = bluetoothMonitor;
	}

	public KeysMonitor getKeysMonitor() {
		return keysMonitor;
	}

	public void setKeysMonitor(KeysMonitor keysMonitor) {
		this.keysMonitor = keysMonitor;
	}
}
