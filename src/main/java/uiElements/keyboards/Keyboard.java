package uiElements.keyboards;

import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import uiElements.Drawable;
import uiElements.Panel;

@SuppressWarnings("serial")
public abstract class Keyboard extends Drawable implements MouseListener {

	private String storedAnswer = "";
	private boolean pressed = false;
	protected Button[] buttons;
	private Point initMouse;
	private Point initCoords;
	
	public Keyboard(Panel panel, Point point) {
		super(panel, point);
		setDoubleBuffered(true);
		addMouseListener(this);
		setOpaque(false);
		panel.add(this);
	}
	
	public Keyboard(Panel panel, Point point, String imagePath) throws IOException {
		super(panel, point, imagePath);
		setDoubleBuffered(true);
		addMouseListener(this);
		setOpaque(false);
		panel.add(this);
	}

	public void addInput(String buttonID) {
		setStoredAnswer(buttonID);
	}

	public String getStoredAnswer() {
		return storedAnswer ;
	}

	public void setStoredAnswer(String storedAnswer) {
		this.storedAnswer = storedAnswer;
	}	
	
	public void removeButtons() {
		for (Button button: buttons) {
			panel.remove(button);
		}
	}
	
	@Override
	public void draw(Graphics2D g2D) {		
		if (pressed) {
			setLocation((int) (initCoords.getX() + MouseInfo.getPointerInfo().getLocation().getX() - initMouse.getX()), (int) (initCoords.getY() + MouseInfo.getPointerInfo().getLocation().getY() - initMouse.getY()));
		}
		super.draw(g2D);
	}

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
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
		
}
