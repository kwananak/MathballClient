package uiElements.characters;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import uiElements.Drawable;
import uiElements.Panel;
import uiElements.ResourceLoader;

@SuppressWarnings("serial")
public class Player extends Drawable {
	
	private final Point benchSpot;	
	private ArrayList<Point> destinations = new ArrayList<>();
	private int base = 0;
	private final BufferedImage fieldSprite, batSprite, idleSprite, celebFieldSprite, celebSprite;
	private PlayerAnimator animator;	
	private boolean animationFlipper, field = false;
	
	public Player(Panel panel, Point point, String imagePath, String fieldImage, String batImage, String celebFieldImage, String celebImage) throws IOException {
		super(panel, point, imagePath);
		benchSpot = new Point(point);
		idleSprite = ImageIO.read(ResourceLoader.load(imagePath));
		fieldSprite = ImageIO.read(ResourceLoader.load(fieldImage));
		batSprite = ImageIO.read(ResourceLoader.load(batImage));
		celebFieldSprite = ImageIO.read(ResourceLoader.load(celebFieldImage));
		celebSprite = ImageIO.read(ResourceLoader.load(celebImage));
		animator = new PlayerAnimator(this);
	}
	
	public void returnBench() {
		setBase(0);
		setIdle();
		destinations.add(getBenchSpot());
	}
		
	public void move() {
		animator.move();
	}
	
	public void setDestination(Point destinationCoords) {
		destinations.add(destinationCoords);
	}	
	
	public void draw(Graphics2D g2D) {
		if (animationFlipper) {
			animator.animate();
		}
		super.draw(g2D);
	}
	
	public void arrived() {
		destinations.remove(0);
	}
	
	public boolean hasDestinations() {
		if (destinations.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Point getNextDestination() {
		return destinations.get(0);
	}
		
	public int getBase() {
		return base;
	}
	
	public Point getBenchSpot() {
		return benchSpot;
	}
	
	public boolean getField() {
		return field;
	}
	
	public void setBase(int i) {
		base = i;
	}
	
	public void setField() {
		field = true;
		sprite = fieldSprite;		
	}

	public void setBat() {
		sprite = batSprite;		
	}

	public void setIdle() {
		field = false;
		sprite = idleSprite;		
	}

	public void setCeleb() {
		sprite = celebSprite;				
	}

	public void setCelebField() {
		sprite = celebFieldSprite;
	}
		
	public void setAnimationFlipper(boolean bool) {
		animationFlipper = bool;
	}
	
}
