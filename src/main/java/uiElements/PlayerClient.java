package uiElements;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import client.Panel;

public class PlayerClient {
	
	int Velocity = 3;
	int[] coords = new int[2];
	private int[] benchSpot = new int[2];
	Panel panel;	
	ArrayList<int[]> destinations = new ArrayList<>();
	boolean field, home, onBase, bench, running = false;
	int base = 0;
	BufferedImage sprite;
	
	public PlayerClient(Panel panel, String str, int x, int y) {
		this.panel = panel;
		try {
			this.sprite = ImageIO.read(ResourceLoader.load(str));
		} catch (IOException e) {
			e.printStackTrace();
		}
		coords[0] = x;
		coords[1] = y;
		benchSpot[0] = x;
		benchSpot[1] = y;
	}
	
	public void move() {
		if (destinations.size() > 0) {
			if (coords[0] < destinations.get(0)[0] - Velocity + 1) {
				coords[0] = coords[0] + Velocity;
			}			
			if (coords[0] > destinations.get(0)[0] + Velocity - 1) {
				coords[0] =  coords[0] - Velocity;
			}			
			if (coords[1] < destinations.get(0)[1] - Velocity + 1) {
				coords[1] = coords[1] + Velocity;
			}			
			if (coords[1] > destinations.get(0)[1] + Velocity - 1) {
				coords[1] = coords[1] - Velocity;
			}
			if (coords[0] == destinations.get(0)[0] && coords[1] == destinations.get(0)[1]) {
				destinations.remove(0);
			}
		}		
	}
	
	public void draw(Graphics2D g2D) {
		g2D.drawImage(sprite, coords[0], coords[1], null);
	}
	
	public void setDestination(int[] mountCoords) {
		destinations.add(mountCoords);
	}
	
	public void setBase(int i) {
		base = i;
	}
	
	public int getBase() {
		return base;
	}
	
	public void returnBench() {
		destinations.add(getBenchSpot());
	}

	public int[] getBenchSpot() {
		return benchSpot;
	}

}
