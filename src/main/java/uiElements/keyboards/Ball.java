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
	
	public Ball(Panel panel, String string) throws IOException {
		super(panel, new Point(440, 300), "sprites/ball.png");
		this.string = string;
	}
	
	@Override
	public void draw(Graphics2D g2D) {
		super.draw(g2D);
		g2D.setPaint(Color.black);
		g2D.setFont(new Font("Fixedsys",Font.BOLD,40));
		g2D.drawString(string, getX() + 10, getY() + 70);
	}

}
