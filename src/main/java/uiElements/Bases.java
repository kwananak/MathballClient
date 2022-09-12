package uiElements;

import java.awt.Point;

public abstract class Bases {

	final static Point homeCoords = new Point(441, 180);
	final static Point firstCoords = new Point(156, 339);
	final static Point secondCoords = new Point(429, 540);
	final static Point thirdCoords = new Point(699, 360);
	final static Point mountCoords = new Point(441, 360);
	
	final static Point homeCoordsField = new Point((int) homeCoords.getX(), (int) homeCoords.getY() - 30);
	final static Point firstCoordsField = new Point((int) firstCoords.getX() - 60, (int) firstCoords.getY());
	final static Point secondCoordsField = new Point((int) secondCoords.getX() - 60, (int) secondCoords.getY());
	final static Point thirdCoordsField = new Point((int) thirdCoords.getX() - 60, (int) thirdCoords.getY());
	
	public final static Point homeCoordsBat = new Point((int) homeCoords.getX() - 60, (int) homeCoords.getY() + 9);
	final static Point firstCoordsBat = new Point((int) firstCoords.getX() + 60, (int) firstCoords.getY());
	final static Point secondCoordsBat = new Point((int) secondCoords.getX() + 60, (int) secondCoords.getY());
	final static Point thirdCoordsBat = new Point((int) thirdCoords.getX() + 60, (int) thirdCoords.getY());

}
