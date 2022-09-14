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
	
	private BufferedImage numbers[] = new BufferedImage[2];
	
	public Ball(Panel panel, String string) throws IOException {
		super(panel, new Point(430, 270), "sprites/ballElements/ball.png");
		String[] arrString = string.split(",");
		BufferedImage numbersSheet = ImageIO.read(ResourceLoader.load("sprites/ballElements/numbers.png"));
		numbers[0] = numbersSheet.getSubimage(0, (Integer.valueOf(arrString[0]) - 1) * 112, 96, 112);
		numbers[1] = numbersSheet.getSubimage(0, (Integer.valueOf(arrString[1]) - 1)* 112, 96, 112);
	}
	
	@Override
	public void draw(Graphics2D g2D) {
		super.draw(g2D);
		g2D.drawImage(numbers[0], getX() - 60, getY() + 10, this);
		g2D.drawImage(numbers[1], getX() + 110, getY() + 10, this);
	}

}
