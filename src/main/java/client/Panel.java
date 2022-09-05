package client;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;

import uiElements.Jumbotron;
import uiElements.Keyboard;
import uiElements.PlayerClient;
import uiElements.ResourceLoader;
import uiElements.Umpire;

public class Panel extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	BufferedImage background;
	ArrayList<TeamClient> teams = new ArrayList<TeamClient>(Arrays.asList(new TeamClient(this, "sprites/redSprite.png", 9, 150), new TeamClient(this, "sprites/blueSprite.png", 630, 150)));
	BasesClient bases = new BasesClient(teams);
	Umpire umpire = new Umpire(this, "sprites/umpiSprite.png", 530, 180);
	TeamClient teamField = null;
	TeamClient teamBat = null;
	int batter = 0;
	Jumbotron jumbotron = new Jumbotron(this);
	Keyboard keyboard = new Keyboard(this);
	
	final int PANEL_WIDTH = 1000;
	final int PANEL_HEIGHT = 800;
	int FPS = 60;
	
	Thread gameThread;
	
	public Panel(){
		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		this.setDoubleBuffered(true);
	}
	
	public void startUIThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		try {
			background = ImageIO.read(ResourceLoader.load("sprites/back.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {			
			currentTime = System.nanoTime();			
			delta += (currentTime - lastTime) / drawInterval;			
			lastTime = currentTime;
			
			if(delta>=1) {
				update();
				repaint();
				delta--;
			}
		}
	}
	
	public void update() {
		for (TeamClient team: teams) {
			team.moveTeam();
		}
	}
	
	public void paintComponent(Graphics g) {		
		super.paintComponent(g);		
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.drawImage(background, 0, 0, null);		

		umpire.draw(g2D);
		teams.get(0).drawTeam(g2D);
		teams.get(1).drawTeam(g2D);
		
		jumbotron.draw(g2D);
		keyboard.draw(g2D);
	}
	
	public void inningStart(String str) {	
		batter = 0;
		
		if (str.equals("top")) {
			teamField = teams.get(1);
			teamBat = teams.get(0);
		} else {
			teamField = teams.get(0);
			teamBat = teams.get(1);
		}
	
		teamField.players[0].setDestination(bases.mountCoords);
		teamField.players[1].setDestination(bases.homeCoordsField);
		teamField.players[2].setDestination(bases.firstCoordsField);
		teamField.players[3].setDestination(bases.secondCoordsField);
		teamField.players[4].setDestination(bases.thirdCoordsField);
	}
	
	public void turnStart() {	
		teamBat.players[batter].setDestination(bases.homeCoordsBat);
		teamBat.players[batter].setBase(1);
		batter++;
		if (batter == 5) {
			batter = 0;
		}
	}

	public void cycleBases(String str) {
		int a = Integer.valueOf(str);
		for (int i = 0; i < a; i++) {
			for (PlayerClient player: teamBat.players) {
				if (player.getBase() > 0) {
					player.setBase(player.getBase()+1);
					switch (player.getBase()) {
						case 5:
							player.setDestination(bases.homeCoordsBat);
							player.setDestination(player.getBenchSpot());
							player.setBase(0);
							break;
						case 4:
							player.setDestination(bases.thirdCoordsBat);
							break;
						case 3:
							player.setDestination(bases.secondCoordsBat);
							break;
						case 2:
							player.setDestination(bases.firstCoordsBat);
							break;
					}
				}
			}
		}
	}
	
	public void clearBatter() {
		for (PlayerClient player: teamBat.players) {
			if (player.getBase() == 1) {
				player.setDestination(player.getBenchSpot());
			}
		}
	}
	
	public void returnBench() {
		for (TeamClient team: teams) {
			team.returnBench();
		}
	}
}
