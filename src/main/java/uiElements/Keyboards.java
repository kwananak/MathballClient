package uiElements;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import client.Panel;

public class Keyboards {

	protected boolean keyOn = false;
	private String storedAnswer = "";
	protected BufferedImage board;
	protected Panel panel;
	protected int[] coords = {0, 0};
	
	public Keyboards(Panel p) {
		panel = p;
	}
	
	
	public void flipKeyOn() {
		keyOn = !keyOn;
	}
	
	public boolean isKeyOn() {
		return keyOn;
	}

	public void clearStoredAnswer() {
		setStoredAnswer("");
	}

	public String getStoredAnswer() {
		return storedAnswer ;
	}

	public void setStoredAnswer(String storedAnswer) {
		this.storedAnswer = storedAnswer;
	}
	
	public void draw(Graphics2D g2D) {
		if (keyOn) {	
			g2D.drawImage(board, coords[0], coords[1], null);
		}
	}

	public void addInput(String buttonID) {	}
	
}
