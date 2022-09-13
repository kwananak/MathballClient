package uiElements.keyboards;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.IOException;

import uiElements.Drawable;
import uiElements.Panel;

@SuppressWarnings("serial")
public class Ball extends Drawable {
	
	private String string;
	private int xDiv = 15;
	
	public Ball(Panel panel, String string) throws IOException {
		super(panel, new Point(430, 270), "sprites/ball.png");
		this.string = string;
		String[] arrString = string.split("X");
		if (Integer.valueOf(arrString[0]) < 10) {
			xDiv = 38;
		}		
	}
	
	@Override
	public void draw(Graphics2D g2D) {
		super.draw(g2D);
		g2D.setPaint(Color.black);
		g2D.setFont(new Font("Fixedsys",Font.BOLD,40));
		g2D.drawString(string, getX() + xDiv, getY() + 83);
	}

}
