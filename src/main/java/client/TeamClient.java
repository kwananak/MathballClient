package client;


import java.awt.Graphics2D;
import uiElements.PlayerClient;

public class TeamClient {
	Panel panel;
	String sprite;
	
	PlayerClient[] players = new PlayerClient[5];

	public TeamClient(Panel p, String s, int i, int j) {
		panel = p;
		sprite = s;
		players[0] = new PlayerClient(panel, sprite, i, j);
		players[1] = new PlayerClient(panel, sprite, i+60, j);
		players[2] = new PlayerClient(panel, sprite, i+120, j);
		players[3] = new PlayerClient(panel, sprite, i+180, j);
		players[4] = new PlayerClient(panel, sprite, i+240, j);
	}
	
	public void moveTeam() {
		for (PlayerClient player: players) {
			player.move();
		}
	}
	
	public void drawTeam(Graphics2D g2D) {
		for (PlayerClient player: players) {
			player.draw(g2D);
		}
	}
	
	public void returnBench() {
		for (PlayerClient player: players) {
			player.returnBench();
		}	
	}
}
