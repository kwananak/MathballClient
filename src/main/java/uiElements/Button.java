package uiElements;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import client.Panel;

class Button extends JPanel implements MouseListener{

	private static final long serialVersionUID = 1L;
	Panel panel;
	Keyboards keyboard;
	String buttonID;
	
	Button(Panel p, Keyboards k, String str, int x, int y, int z, int zz) {
		panel = p;
		keyboard = k;
		buttonID = str;
		this.setBounds(x, y, z, zz);
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
