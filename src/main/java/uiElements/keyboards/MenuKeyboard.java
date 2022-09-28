package uiElements.keyboards;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JTextField;

import uiElements.Panel;

@SuppressWarnings("serial")
public class MenuKeyboard extends Keyboard {
	
	private String name = "";
	
	public MenuKeyboard(Panel panel) throws IOException {
		super(panel, new Point(300, 300), "sprites/keyboards/menuKey.png");
		setSize(400, 15);
		setOpaque(false);
		buttons = new Button[2];
		buttons[0] = new Button(panel, this, "ready", 130, 140, 145, 55);
		buttons[1] = new LoginButton(panel, this, "login", 130, 250, 145, 55);
		buttons[1].setOpaque(true);
	}
	
	public MenuKeyboard(Panel panel, String card) throws IOException {
		super(panel, new Point(300, 300), "sprites/keyboards/menuKey.png");
		setSize(400, 15);
		setOpaque(false);
		buttons = new Button[1];
		buttons[0] = new Button(panel, this, "ready", 130, 140, 145, 55);
		name = card.toString().replace(",", "  ");
	}

	
	public void buttonPressed() {
		if (name.length() > 0) {
			buttonPressed(name);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if ((Character.isDigit(c) || Character.isAlphabetic(c)) && name.length() < 12) {
			name += c;
		}	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER && name.length() > 0) {
			buttonPressed(name);
		} else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && name.length() > 0) {
			name = name.substring(0, name.length() - 1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void draw(Graphics2D g2D) {
		super.draw(g2D);
		g2D.setPaint(Color.white);
		g2D.setFont(new Font("Fixedsys", Font.BOLD, 50));
		g2D.drawString(name, getX() + 30, getY() + 250);
	}
	
}
