package uiElements;

import java.awt.Point;

public enum Bases {
	
	HOME,
	FIRST(new Point(156, 339)),
	SECOND(new Point(429, 540)),
	THIRD(new Point(699, 360)),
	MOUNT(new Point(441, 360));
	
	final Point point, field, bat;
	
	Bases(Point point) {
		this.point = point;
		field = new Point((int) point.getX() - 60, (int) point.getY());
		bat = new Point((int) point.getX() + 60, (int) point.getY());
	}
	
	Bases() {
		point = new Point(441, 180);
		field = new Point((int) point.getX(), (int) point.getY() - 30);
		bat = new Point((int) point.getX() - 60, (int) point.getY());
	}
	
}
