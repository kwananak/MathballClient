package uiElements.keyboards;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.IOException;

import uiElements.Panel;

@SuppressWarnings("serial")
public class InningKeyboard extends Keyboard {
	
	public InningKeyboard(Panel panel) throws IOException {
		super(panel, new Point(315, 360), "sprites/keyboards/inningKey.png");
		buttons = new Button[5];
		buttons[0] = new Button(panel, this, "1", 10, 15, 60, 60);
		buttons[1] = new Button(panel, this, "2", 80, 15, 60, 60);
		buttons[2] = new Button(panel, this, "3", 150, 15, 60, 60);
		buttons[3] = new Button(panel, this, "4", 220, 15, 60, 60);
		buttons[4] = new Button(panel, this, "5", 290, 15, 60, 60);
		setBounds(getX(), getY(), 360, 15);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if (Character.isDigit(c)) {
			String nu = "";
			nu += c;
			if (Integer.valueOf(nu) > 0 && Integer.valueOf(nu) < 6) {
				buttonPressed(nu);
			}			
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
	
}
