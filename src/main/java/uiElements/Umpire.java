package uiElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

import client.Panel;

@SuppressWarnings("serial")
public class Umpire extends Drawable {
	
	String talk = " ";
	
	public Umpire(Panel panel, Point point, String imagePath) {
		super(panel, point, imagePath);
	}
	
	@Override
	public void draw(Graphics2D g2D) {
		g2D.drawImage(sprite, getX(), getY(), null);
		g2D.setPaint(Color.white);
		g2D.setFont(new Font("Fixedsys",Font.BOLD,30));
		g2D.drawString(talk, getX() - 120, getY() - 15);
	}
	
	public void setTalk(String str) {
		talk = str;
	}
	
}
