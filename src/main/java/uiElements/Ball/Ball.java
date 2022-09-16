package uiElements.Ball;


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
	private BufferedImage lilBall;
	private boolean starting = true;
	private int counter = 30;
	
	public Ball(Panel panel, String string) throws IOException {
		super(panel, new Point(430, 270), "sprites/ballElements/ball.png");
		lilBall = ImageIO.read(ResourceLoader.load("sprites/ballElements/lilBall.png"));
		String[] arrString = string.split(",");
		BufferedImage numbersSheet = ImageIO.read(ResourceLoader.load("sprites/ballElements/numbers.png"));
		numbers[0] = numbersSheet.getSubimage(0, (Integer.valueOf(arrString[0]) - 1) * 112, 96, 112);
		numbers[1] = numbersSheet.getSubimage(0, (Integer.valueOf(arrString[1]) - 1)* 112, 96, 112);
	}
	
	@Override
	public void draw(Graphics2D g2D) {
		if (starting) {
			g2D.drawImage(lilBall, getX() + 33, getY() + 60, this);
			counter--;
			if (counter == 0) {
				starting = false;
				counter = 30;
			}
		} else if (panel.getEndBall()) {
			g2D.drawImage(lilBall, getX() + 33, getY() - 10, this);
			counter--;
			if (counter == 0) {
				panel.setEndBallFalse();
				panel.getDrawables().remove(this);
			}
				
		} else {
			super.draw(g2D);
			g2D.drawImage(numbers[0], getX() - 60, getY() + 10, this);
			g2D.drawImage(numbers[1], getX() + 110, getY() + 10, this);
		}
	}

}
