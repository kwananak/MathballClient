package uiElements.keyboards;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import client.ResourceLoader;
import uiElements.Panel;

@SuppressWarnings("serial")
public class BaseThrowButton extends JPanel implements MouseListener {

	Keyboard keyboard;
	BufferedImage image;
	String buttonID;
	Panel panel;
	
	public BaseThrowButton (Panel panel, Keyboard keyboard, String imagePath, String buttonID, int i, int j, int k, int l) throws IOException {
		image = ImageIO.read(ResourceLoader.load(imagePath));
		this.panel = panel;
		this.keyboard = keyboard;
		this.buttonID = buttonID;
		setBounds(i, j, k, l);
		addMouseListener(this);
		setOpaque(false);
		panel.add(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		keyboard.buttonPressed(buttonID);
		panel.setAvailableBasePitch(false);
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
