package client;

import java.awt.Graphics2D;
import java.awt.Point;
import java.io.IOException;

import uiElements.Player;

public class Team {

	private Player[] players = new Player[5];
	private int batter = 0;

	public Team(Panel panel, String imagePath, String fieldImage, String batImage, String celebImage, int x, int y) throws IOException {
		players[0] = new Player(panel, new Point(x, y), imagePath, fieldImage, batImage, celebImage);
		players[1] = new Player(panel, new Point(x + 60, y), imagePath, fieldImage, batImage, celebImage);
		players[2] = new Player(panel, new Point(x + 120, y), imagePath, fieldImage, batImage, celebImage);
		players[3] = new Player(panel, new Point(x + 180, y), imagePath, fieldImage, batImage, celebImage);
		players[4] = new Player(panel, new Point(x + 240, y), imagePath, fieldImage, batImage, celebImage);
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
	
	public void cycleBatter() {
		batter++;
		if (batter == 5) {
			batter = 0;
		}
	}
	
	public int getBatter() {
		return batter;
	}
	
	public Player getPlayer(int i) {
		return players[i];
	}
	
	public Player[] getAllPlayers() {
		return players;
	}
}
