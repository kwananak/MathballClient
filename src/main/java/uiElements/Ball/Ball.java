package uiElements.Ball;


import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import client.ResourceLoader;
import uiElements.Drawable;
import uiElements.Panel;

@SuppressWarnings("serial")
public class Ball extends Drawable {
	
	private BufferedImage numbers[] = new BufferedImage[2];
	private BufferedImage lilBall;
	private boolean starting = true;
	private int counter = 30;
	private final static Point[] bases = {new Point(430, 270), new Point(300, 380), new Point(430, 520), new Point(600, 400)};
	private final static int[][] deviations = {{33, 60, 33, -10}, {60, 33, -10, 33}, {33, -10, 33, 60}, {-10, 33, 60, 30}};
	private int plate;
	
	public Ball(Panel panel, String string) throws IOException {
		super(panel, "sprites/ballElements/ball.png");	
		lilBall = ImageIO.read(ResourceLoader.load("sprites/ballElements/lilBall.png"));
		String[] arrString = string.split(",");
		this.plate = Integer.valueOf(arrString[2]);
		setLocation(bases[plate]);	
		BufferedImage numbersSheet = ImageIO.read(ResourceLoader.load("sprites/ballElements/numbers.png"));
		numbers[0] = numbersSheet.getSubimage(0, (Integer.valueOf(arrString[0]) - 1) * 112, 96, 112);
		numbers[1] = numbersSheet.getSubimage(0, (Integer.valueOf(arrString[1]) - 1)* 112, 96, 112);
	}
	
	@Override
	public void draw(Graphics2D g2D) {
		if (starting) {
			g2D.drawImage(lilBall, getX() + deviations[plate][0], getY() + deviations[plate][1], this);
			counter--;
			if (counter == 0) {
				starting = false;
				counter = 30;
			}
		} else if (panel.getEndBall()) {
			g2D.drawImage(lilBall, getX() + deviations[plate][2], getY() + deviations[plate][3], this);
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
