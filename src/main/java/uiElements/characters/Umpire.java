package uiElements.characters;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import uiElements.Drawable;
import uiElements.Panel;
import uiElements.ResourceLoader;

@SuppressWarnings("serial")
public class Umpire extends Drawable {
	
	private String talk = " ";
	private BufferedImage bubble;
	
	public Umpire(Panel panel, Point point, String imagePath) throws IOException {
		super(panel, point, imagePath);
		bubble = ImageIO.read(ResourceLoader.load("sprites/umpire/bubble.png"));
	}
	
	@Override
	public void draw(Graphics2D g2D) {
		super.draw(g2D);
		if (!talk.equals(" ")) {
			int nuX = (getX() - (talk.length() * 15)) - 15;
			g2D.drawImage(bubble.getSubimage(0, 0, 16, 80), nuX - 15, getY() - 42, null);
			for (int i = 1; i < talk.length() - 1; i++) {
				g2D.drawImage(bubble.getSubimage(12, 0, 16, 80), (nuX - 15) + (i * 16), getY() - 42, null);
			}
			g2D.drawImage(bubble.getSubimage(28, 0, 44, 80), (nuX - 15) + ((talk.length() - 1)* 16), getY() - 42, null);
			g2D.setPaint(Color.black);
			g2D.setFont(new Font("Fixedsys",Font.BOLD,30));
			g2D.drawString(talk, nuX, getY());
		}
	}
	
	public void setTalk(String str) {
		talk = str;
	}

}
