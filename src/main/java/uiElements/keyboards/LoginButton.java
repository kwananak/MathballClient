package uiElements.keyboards;

import java.awt.event.MouseEvent;

import uiElements.Panel;

@SuppressWarnings("serial")
public class LoginButton extends Button {

	MenuKeyboard keyboard;
	
	public LoginButton(Panel panel, Keyboard keyboard, String buttonID, int x, int y, int width, int height) {
		super(panel, keyboard, buttonID, x, y, width, height);
		
	}

	public void mousePressed(MouseEvent e) {
		keyboard.buttonPressed();
	}
	
}
