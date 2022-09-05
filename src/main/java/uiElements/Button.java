package uiElements;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import client.Panel;

class Button extends JPanel implements MouseListener{

	private static final long serialVersionUID = 1L;
	Panel panel;
	Keyboard keyboard;
	String buttonID;
	
	Button(Panel p, Keyboard k, String str) {
		panel = p;
		keyboard = k;
		buttonID = str;
		this.setDoubleBuffered(true);
		this.addMouseListener(this);
		this.setOpaque(false);
		this.panel.add(this);
	}
	
	
	
	public void mouseClicked(MouseEvent e) {			
	}

	public void mousePressed(MouseEvent e) {
		keyboard.addInput(buttonID);		
	}

	public void mouseReleased(MouseEvent e) {		
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {		
	}

}
