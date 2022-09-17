package uiElements.keyboards;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.IOException;

import uiElements.Panel;

@SuppressWarnings("serial")
public class MenuKeyboard extends Keyboard {
	
	public MenuKeyboard(Panel panel) throws IOException {
		super(panel, new Point(300, 300), "sprites/keyboards/menuKey.png");
		setSize(400, 15);
		setOpaque(false);
		buttons = new Button[1];
		buttons[0] = new Button(panel, this, "ready", 130, 140, 145, 55);
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			buttonPressed("ready");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

}
