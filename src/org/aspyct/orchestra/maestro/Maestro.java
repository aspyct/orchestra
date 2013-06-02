package org.aspyct.orchestra.maestro;

import org.aspyct.orchestra.api.bluetooth.BluetoothDevice;
import org.aspyct.orchestra.api.bluetooth.BluetoothListener;
import org.aspyct.orchestra.api.keys.KeysListener;
import org.aspyct.orchestra.api.music.MusicListener;
import org.aspyct.orchestra.api.power.PowerListener;

public class Maestro implements BluetoothListener, PowerListener, KeysListener, MusicListener {
	@Override
	public void deviceArrives(BluetoothDevice device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deviceLeaves(BluetoothDevice device) {
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
}
