package client;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {

	private Boolean muted = false;
	private AudioInputStream ais;
	private Clip clip;
	private String theme = "C:\\Users\\kouan\\eclipse-workspace\\client\\src\\main\\resources\\sounds\\theme.wav";
	private String crowd = "C:\\Users\\kouan\\eclipse-workspace\\client\\src\\main\\resources\\sounds\\crowd.wav";
	private String hit = "C:\\Users\\kouan\\eclipse-workspace\\client\\src\\main\\resources\\sounds\\hit.wav";
	private String pitch = "C:\\Users\\kouan\\eclipse-workspace\\client\\src\\main\\resources\\sounds\\pitch.wav";
	private String strike = "C:\\Users\\kouan\\eclipse-workspace\\client\\src\\main\\resources\\sounds\\strike.wav";
	private String out = "C:\\Users\\kouan\\eclipse-workspace\\client\\src\\main\\resources\\sounds\\out.wav";
	
	public void playTheme() {
		play(theme);
	}
	
	public void playCrowd() {
		play(crowd);
	}
	
	public void playHit() {
		play(hit);
	}
	
	public void playPitch() {
		play(pitch);
	}
	
	public void playStrike() {
		play(strike);
	}
	
	public void playOut() {
		play(out);
	}
	
	public void flipMuted() {
		muted = !muted;
	}

	private void play(String str) {
		if(!muted) {
			try {
				ais = AudioSystem.getAudioInputStream(new File(str).getAbsoluteFile());
				clip = AudioSystem.getClip();
				clip.open(ais);
				clip.start();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		}		
	}
}
