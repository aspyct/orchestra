package org.aspyct.orchestra.api.music;

public interface MusicManager {
	void stopPlayback();
	void startPlayback();
	
	void addMusicListener(MusicListener listener);
	void removeMusicListener(MusicListener listener);
	boolean isMusicPlaying();
	void fadeOut();
}
