package uiElements;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import client.Panel;

@SuppressWarnings("serial")
public class Player extends Drawable {
	
	private final int Velocity = 3;
	private final Point benchSpot;	
	private ArrayList<Point> destinations = new ArrayList<>();
	private int base = 0;
	private final BufferedImage fieldSprite, batSprite, idleSprite;
	Image celebSprite;
	
	public Player(Panel panel, Point point, String imagePath, String fieldImage, String batImage, String celebImage) throws IOException {
		super(panel, point, imagePath);
		benchSpot = new Point(point);
		idleSprite = ImageIO.read(ResourceLoader.load(imagePath));
		fieldSprite = ImageIO.read(ResourceLoader.load(fieldImage));
		batSprite = ImageIO.read(ResourceLoader.load(batImage));
		celebSprite = ImageIO.read(ResourceLoader.load(celebImage));
	}
	
	public void move() {
		if (destinations.size() > 0) {
			if (getX() < destinations.get(0).getX() - Velocity + 1) {
				setLocation(getX() + Velocity, getY());
			}			
			if (getX() > destinations.get(0).getX() + Velocity - 1) {
				setLocation(getX() - Velocity, getY());
			}			
			if (getY() < destinations.get(0).getY() - Velocity + 1) {
				setLocation(getX(), getY() + Velocity);
			}			
			if (getY() > destinations.get(0).getY() + Velocity - 1) {
				setLocation(getX(), getY() - Velocity);
			}
			if (getLocation().equals(destinations.get(0))) {
				destinations.remove(0);
			}
		}		
	}
	
	public void setDestination(Point destinationCoords) {
		Point destCo = new Point(destinationCoords);
		destinations.add(destCo);
	}
	
	public void setBase(int i) {
		base = i;
	}
	
	public int getBase() {
		return base;
	}
	
	public void returnBench() {
		setBase(0);
		setIdleSprite();
		destinations.add(getBenchSpot());
	}

	public Point getBenchSpot() {
		return benchSpot;
	}

	public void setIdleSprite() {
		setSprite(idleSprite);
	}
	
	public void setBatSprite() {
		setSprite(batSprite);
	}
	
	public void setFieldSprite() {
		setSprite(fieldSprite);
	}
	
}
