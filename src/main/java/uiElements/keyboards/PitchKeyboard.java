package uiElements.keyboards;

import java.awt.Point;
import java.io.IOException;

import uiElements.Panel;

@SuppressWarnings("serial")
public class PitchKeyboard extends Keyboard {

	public PitchKeyboard(Panel panel, Point point) throws IOException {
		super(panel, point, "sprites/pitchKey.png");
		buttons = new Button[2];
		buttons[0] = new Button(panel, this, "0", 10, 10, 130, 160);
		buttons[1] = new Button(panel, this, "1", 150, 10, 130, 160);
		setBounds(getX(), getY(), 290, 10);
	}

}
