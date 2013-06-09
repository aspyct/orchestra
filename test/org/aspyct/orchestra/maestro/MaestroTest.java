package org.aspyct.orchestra.maestro;

import static org.mockito.Mockito.*;

import org.aspyct.orchestra.api.bluetooth.BluetoothDevice;
import org.aspyct.orchestra.api.bluetooth.BluetoothMonitor;
import org.aspyct.orchestra.api.keys.KeysMonitor;
import org.aspyct.orchestra.api.music.MusicManager;
import org.aspyct.orchestra.api.power.PowerManager;
import org.aspyct.orchestra.api.timetell.TimeTeller;
import org.aspyct.orchestra.maestro.Maestro;
import org.junit.Before;
import org.junit.Test;

public class MaestroTest {
	private Maestro maestro;
	private PowerManager powerManager;
	private MusicManager musicManager;
	private TimeTeller timeTeller;
	private BluetoothMonitor bluetoothMonitor;
	private KeysMonitor keysMonitor;
	
	@Before
	public void setUp() {
		maestro = new Maestro();
		
		powerManager = mock(PowerManager.class);
		maestro.setPowerManager(powerManager);
		
		musicManager = mock(MusicManager.class);
		maestro.setMusicPlayer(musicManager);
		
		timeTeller = mock(TimeTeller.class);
		maestro.setTimeTeller(timeTeller);
		
		bluetoothMonitor = mock(BluetoothMonitor.class);
		maestro.setBluetoothMonitor(bluetoothMonitor);
		
		keysMonitor = mock(KeysMonitor.class);
		maestro.setKeysMonitor(keysMonitor);
	}

	@Test
	public void powerOffShouldStopMusic() {
		maestro.powerSwitchedOff();
		
		verify(musicManager).stopPlayback();
	}
	
	@Test
	public void keysPluggedInShouldPowerOn() {
		maestro.keysPluggedIn();
		
		verify(powerManager).powerOn();
	}

	@Test
	public void keysUnpluggedShouldPowerOff() {
		bluetoothIsNotPresent();
		musicIsNotPlaying();
		
		maestro.keysUnplugged();
		
		verify(bluetoothMonitor).isAnyDevicePresent();
		verify(musicManager).isMusicPlaying();
		verify(powerManager).powerOff();
	}

	@Test
	public void keysUnpluggedShouldNotPowerOffIfBluetoothIsPresent() {
		bluetoothIsPresent();
		
		maestro.keysUnplugged();
		
		verify(bluetoothMonitor).isAnyDevicePresent();
		verifyZeroInteractions(powerManager, musicManager);
	}
	
	@Test
	public void keysUnpluggedShouldNotPowerOffIfMusicIsPlaying()  {
		musicIsPlaying();
		
		maestro.keysUnplugged();
		
		verify(musicManager).isMusicPlaying();
		verifyZeroInteractions(powerManager);
	}
	
	@Test
	public void bluetoothArriveShouldDecreaseBluetoothScanRate() {
		bluetoothIsNotPresent();
		
		maestro.bluetoothDeviceArrives(new BluetoothDevice());
		
		verify(bluetoothMonitor).decreaseBluetoothScanRate();
	}
	
	@Test
	public void bluetoothLeaveShouldPowerOffIfKeysAreNotPresent() {
		bluetoothIsNotPresent();
		keysAreNotPresent();
		
		maestro.bluetoothDeviceLeaves(new BluetoothDevice());
		
		verify(bluetoothMonitor).isAnyDevicePresent();
		verify(keysMonitor).areKeysPresent();
		verify(powerManager).powerOff();
	}
	
	@Test
	public void bluetoothLeaveShouldIncreaseBluetoothScanRateIfKeysAreNotPresent() {
		bluetoothIsNotPresent();
		keysAreNotPresent();
		
		maestro.bluetoothDeviceLeaves(new BluetoothDevice());
		
		verify(bluetoothMonitor).isAnyDevicePresent();
		verify(keysMonitor).areKeysPresent();
		verify(bluetoothMonitor).increaseBluetoothScanRate();
	}
	
	@Test
	public void bluetoothLeaveShouldNotIncreaseBluetoothScanRateIfKeysArePresent() {
		bluetoothIsNotPresent();
		keysArePresent();
		
		maestro.bluetoothDeviceLeaves(new BluetoothDevice());
		
		verify(bluetoothMonitor).isAnyDevicePresent();
		verify(keysMonitor).areKeysPresent();
		verify(bluetoothMonitor, never()).increaseBluetoothScanRate(); 
	}
	
	@Test
	public void bluetoothLeaveShouldNotPowerOffIfMusicIsPlayingAndItsDaytime() {
		bluetoothIsNotPresent();
		keysArePresent();
		musicIsPlaying();
		itsDaytime();
		
		maestro.bluetoothDeviceLeaves(new BluetoothDevice());
		
		verify(bluetoothMonitor).isAnyDevicePresent();
		verify(keysMonitor).areKeysPresent();
		verify(musicManager).isMusicPlaying();
		verify(timeTeller).isNight();
		verifyZeroInteractions(powerManager);
	}
	
	@Test
	public void bluetoothLeaveShouldFadeMusicOutIfMusicIsPlayingAndItsNight() {
		bluetoothIsNotPresent();
		keysArePresent();
		musicIsPlaying();
		itsNight();
		
		maestro.bluetoothDeviceLeaves(new BluetoothDevice());
		
		verify(bluetoothMonitor).isAnyDevicePresent();
		verify(keysMonitor).areKeysPresent();
		verify(musicManager).isMusicPlaying();
		verify(timeTeller).isNight();
		verify(musicManager).fadeOut();
		verifyZeroInteractions(powerManager);
	}
	
	@Test
	public void bluetoothLeaveShouldNotFadeMusicIfMusicIsPlayingAndItsDaytime() {
		bluetoothIsNotPresent();
		keysArePresent();
		musicIsPlaying();
		itsDaytime();
		
		maestro.bluetoothDeviceLeaves(new BluetoothDevice());
		
		verify(bluetoothMonitor).isAnyDevicePresent();
		verify(keysMonitor).areKeysPresent();
		verify(musicManager).isMusicPlaying();
		verify(timeTeller).isNight();
		verifyZeroInteractions(powerManager);
		verify(musicManager, never()).fadeOut();
	}
	
	@Test
	public void musicStartsShouldPowerOn() {
		maestro.musicStartedPlaying();
		
		verify(powerManager).powerOn();
	}
	
	@Test
	public void musicStopsShouldPowerOffIfBluetoothNotPresentAndItsNight() {
		bluetoothIsNotPresent();
		itsNight();
		
		maestro.musicStoppedPlaying();
		
		verify(powerManager).powerOff();
		verify(bluetoothMonitor).isAnyDevicePresent();
		verify(timeTeller).isNight();
	}
	
	@Test
	public void musicStopsShouldNotPowerOffIfItsNotNight() {
		bluetoothIsNotPresent();
		itsDaytime();
		
		maestro.musicStoppedPlaying();
		
		verify(powerManager, never()).powerOff();
		verify(bluetoothMonitor).isAnyDevicePresent();
		verify(timeTeller).isNight();
	}
	
	@Test
	public void musicStopsShouldNotPowerOffIfBluetoothIsPresent() {
		bluetoothIsPresent();
		
		maestro.musicStoppedPlaying();
		
		verify(powerManager, never()).powerOff();
		verify(bluetoothMonitor).isAnyDevicePresent();
		verify(timeTeller, never()).isNight();
	}
	
	private void itsNight() {
		when(timeTeller.isMorning()).thenReturn(false);
		when(timeTeller.isDaytime()).thenReturn(false);
		when(timeTeller.isNight()).thenReturn(true);
	}

	private void itsDaytime() {
		when(timeTeller.isMorning()).thenReturn(false);
		when(timeTeller.isDaytime()).thenReturn(true);
		when(timeTeller.isNight()).thenReturn(false);
	}

	private void musicIsNotPlaying() {
		when(musicManager.isMusicPlaying()).thenReturn(false);
	}

	private void bluetoothIsNotPresent() {
		when(bluetoothMonitor.isAnyDevicePresent()).thenReturn(false);
	}
	
	private void keysArePresent() {
		when(keysMonitor.areKeysPresent()).thenReturn(true);
	}

	private void keysAreNotPresent() {
		when(keysMonitor.areKeysPresent()).thenReturn(false);
	}

	private void bluetoothIsPresent() {
		when(bluetoothMonitor.isAnyDevicePresent()).thenReturn(true);
	}

	private void musicIsPlaying() {
		when(musicManager.isMusicPlaying()).thenReturn(true);
	}
}
