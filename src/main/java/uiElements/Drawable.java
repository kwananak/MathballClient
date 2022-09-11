package uiElements;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import client.Panel;

@SuppressWarnings("serial")
public class Drawable extends JPanel {

	Panel panel;
	BufferedImage sprite;
	
	public Drawable(Panel panel, Point point) {
		this.panel = panel;
		this.setLocation(point);
	}
	
	public Drawable(Panel panel, Point point, String imagePath) {
		this.panel = panel;
		this.setLocation(point);
		try {
			this.sprite = ImageIO.read(ResourceLoader.load(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2D) {
		g2D.drawImage(sprite, getX(), getY(), null);
	}
	
}
