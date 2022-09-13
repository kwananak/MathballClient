package uiElements.keyboards;


import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import uiElements.Drawable;
import uiElements.Panel;
import uiElements.ResourceLoader;

@SuppressWarnings("serial")
public class Ball extends Drawable {
	
	private BufferedImage[] numbers = new BufferedImage[2];
	
	public Ball(Panel panel, String string) throws IOException {
		super(panel, new Point(430, 270), "sprites/ball.png");
		String[] arrString = string.split("X");
		numbers[0] = ImageIO.read(ResourceLoader.load("sprites/numbers/number" + Integer.valueOf(arrString[0]) +".png"));
		numbers[1] = ImageIO.read(ResourceLoader.load("sprites/numbers/number" + Integer.valueOf(arrString[1]) +".png"));				
	}
	
	@Override
	public void draw(Graphics2D g2D) {
		super.draw(g2D);
		g2D.drawImage(numbers[0], getX() - 60, getY() + 10, this);
		g2D.drawImage(numbers[1], getX() + 110, getY() + 10, this);
	}

}
