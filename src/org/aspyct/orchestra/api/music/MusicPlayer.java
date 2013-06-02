package org.aspyct.orchestra.api.music;

public interface MusicPlayer {
	void addMusicListener(MusicListener listener);
	void removeMusicListener(MusicListener listener);
}
