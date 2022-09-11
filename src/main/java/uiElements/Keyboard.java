package uiElements;

import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import client.Panel;

@SuppressWarnings("serial")
public class Keyboard extends Drawable implements MouseListener {

	String storedAnswer = "";
	boolean pressed = false;
	Button[] buttons;
	Point initMouse;
	Point initCoords;
	
	public Keyboard(Panel panel, Point point) {
		super(panel, point);
		setDoubleBuffered(true);
		addMouseListener(this);
		setOpaque(false);
		panel.add(this);
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
	
	@Override
	public void draw(Graphics2D g2D) {		
		if (pressed) {
			setLocation((int) (initCoords.getX() + MouseInfo.getPointerInfo().getLocation().getX() - initMouse.getX()), (int) (initCoords.getY() + MouseInfo.getPointerInfo().getLocation().getY() - initMouse.getY()));
		}
		super.draw(g2D);
	}

	public void addInput(String buttonID) {}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {		
		initMouse = new Point(MouseInfo.getPointerInfo().getLocation());
		initCoords = getLocation();
		pressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		pressed = false;
		for (Button button: buttons) {
			button.resetButton();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	public void removeButtons() {
		for (Button button: buttons) {
			panel.remove(button);
		}
	}
	
}
