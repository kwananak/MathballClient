package client;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {

	private Boolean muted = true;
	private AudioInputStream ais;
	private Clip clip;

	public void flipMuted() {
		muted = !muted;
	}

	public void play(Sounds sound) {
		if(!muted) {
			try {
				ais = AudioSystem.getAudioInputStream(ResourceLoader.load(sound.path));
				clip = AudioSystem.getClip();
				clip.open(ais);
				clip.start();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		}		
	}
}
