package uiElements;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import client.Panel;

@SuppressWarnings("serial")
public class Button extends JPanel implements MouseListener{

	private final Keyboard keyboard;
	private final String buttonID;
	private final Point position;
	
	public Button(Panel panel, Keyboard keyboard, String buttonID, int x, int y, int width, int height) {
		this.keyboard = keyboard;
		this.buttonID = buttonID;
		position = new Point(x, y);
		setBounds(keyboard.getX() + (int) position.getX(), keyboard.getY() + (int) position.getY(), width, height);
		addMouseListener(this);
		setOpaque(false);
		panel.add(this);
	}
	
	public void resetButton() {
		setBounds(keyboard.getX() + (int) position.getX(), keyboard.getY() + (int) position.getY(), (int) getBounds().getWidth(), (int) getBounds().getHeight());
	}
	
	public void mouseClicked(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		keyboard.addInput(buttonID);
	}

	public void mouseReleased(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

}
