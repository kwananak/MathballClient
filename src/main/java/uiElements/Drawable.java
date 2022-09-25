package uiElements;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import client.ResourceLoader;

@SuppressWarnings("serial")
public class Drawable extends JPanel {

	protected Panel panel;
	protected BufferedImage sprite;
	
	public Drawable() {}
	
	public Drawable(Panel panel, String imagePath) throws IOException {
		this.panel = panel;
		this.sprite = ImageIO.read(ResourceLoader.load(imagePath));
	}
	
	public Drawable(Panel panel, Point point) {
		this.panel = panel;
		this.setLocation(point);
		setDoubleBuffered(true);
	}
	
	public Drawable(Panel panel, Point point, String imagePath) throws IOException {
		this.panel = panel;
		this.setLocation(point);
		this.sprite = ImageIO.read(ResourceLoader.load(imagePath));
		setDoubleBuffered(true);
	}
	
	public void draw(Graphics2D g2D) {
		g2D.drawImage(sprite, getX(), getY(), null);
	}
	
	public void setSprite(BufferedImage newSprite) {
		sprite = newSprite;
	}
	
}
