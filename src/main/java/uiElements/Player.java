package uiElements;

import java.awt.Point;
import java.util.ArrayList;
import client.Panel;

@SuppressWarnings("serial")
public class Player extends Drawable {
	
	int Velocity = 3;
	private Point benchSpot = new Point();	
	ArrayList<Point> destinations = new ArrayList<>();
	boolean field, home, onBase, bench, running = false;
	int base = 0;
	
	public Player(Panel panel, Point point, String imagePath) {
		super(panel, point, imagePath);
		benchSpot.setLocation(point);;
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
	
	public void setDestination(int[] destinationCoords) {
		Point destCo = new Point(destinationCoords[0], destinationCoords[1]);
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
		destinations.add(getBenchSpot());
	}

	public Point getBenchSpot() {
		return benchSpot;
	}

}
