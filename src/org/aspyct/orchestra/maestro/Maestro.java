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
		bluetoothMonitor.decreaseBluetoothScanRate();
		
		if (keysMonitor.areKeysPresent() && timeTeller.isMorning()) {
			openReminders();
		}
	}

	private void openReminders() {
		// nothing to do
	}

	@Override
	public void bluetoothDeviceLeaves(BluetoothDevice device) {
		if (!bluetoothMonitor.isAnyDevicePresent()) {
			if (!keysMonitor.areKeysPresent()) {
				bluetoothMonitor.increaseBluetoothScanRate();
				powerManager.powerOff();
			}
			else if (!musicManager.isMusicPlaying()) {
				powerManager.powerOff();
			}
			else if (timeTeller.isNight()) {
				musicManager.fadeOut();
			}
		}
	}

	@Override
	public void keysPluggedIn() {
		powerManager.powerOn();
	}

	@Override
	public void keysUnplugged() {
		if (!(bluetoothMonitor.isAnyDevicePresent() || musicManager.isMusicPlaying())) {
			powerManager.powerOff();
		}
	}

	@Override
	public void powerSwitchedOn() {
		// nothing to do
	}

	@Override
	public void powerSwitchedOff() {
		musicManager.stopPlayback();
	}

	@Override
	public void musicStartedPlaying() {
		powerManager.powerOn();
	}

	@Override
	public void musicStoppedPlaying() {
		if (!bluetoothMonitor.isAnyDevicePresent()) {
			powerManager.powerOff();
		}
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
