package uiElements;

import java.awt.Point;
import java.io.IOException;

import javax.imageio.ImageIO;

import client.Panel;

@SuppressWarnings("serial")
public class InningKeyboard extends Keyboard {
	
	public InningKeyboard(Panel panel) {
		super(panel, new Point(315, 360));
		try {
			sprite = ImageIO.read(ResourceLoader.load("sprites/inningKey.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
	
	public void setButtons(Panel panel) {
		buttons = new Button[5];
		buttons[0] = new Button(panel, this, "1", 10, 10, 60, 60);
		buttons[1] = new Button(panel, this, "2", 80, 10, 60, 60);
		buttons[2] = new Button(panel, this, "3", 150, 10, 60, 60);
		buttons[3] = new Button(panel, this, "4", 220, 10, 60, 60);
		buttons[4] = new Button(panel, this, "5", 290, 10, 60, 60);
		setBounds(getX(), getY(), 360, 10);
	}

	public void addInput(String buttonID) {
		setStoredAnswer(buttonID);
	}
	
}
