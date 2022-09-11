package client;

import java.awt.Graphics2D;
import java.awt.Point;

import uiElements.Player;

public class Team {

	Player[] players = new Player[5];

	public Team(Panel panel, String imagePath, int x, int y) {

		players[0] = new Player(panel, new Point(x, y), imagePath);
		players[1] = new Player(panel, new Point(x + 60, y), imagePath);
		players[2] = new Player(panel, new Point(x + 120, y), imagePath);
		players[3] = new Player(panel, new Point(x + 180, y), imagePath);
		players[4] = new Player(panel, new Point(x + 240, y), imagePath);
	}
	
	public void moveTeam() {
		for (Player player: players) {
			player.move();
		}
	}
	
	public void drawTeam(Graphics2D g2D) {
		for (Player player: players) {
			player.draw(g2D);
		}
	}
	
	public void returnBench() {
		for (Player player: players) {
			player.returnBench();
		}	
	}
	
}
