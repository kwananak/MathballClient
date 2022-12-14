package uiElements;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import client.AudioPlayer;
import client.ResourceLoader;
import client.Sounds;
import uiElements.characters.Player;
import uiElements.characters.Team;
import uiElements.characters.Umpire;

@SuppressWarnings("serial")
public class Panel extends JPanel implements Runnable{
	
	private final BufferedImage background;
	private Team[] teams = {new Team(this, "sprites/redSprite.png", "sprites/redField.png", "sprites/redBat.png", "sprites/redCelebField.png", "sprites/redCeleb.png", 9, 150), new Team(this, "sprites/blueSprite.png", "sprites/blueField.png", "sprites/blueBat.png", "sprites/blueCelebField.png", "sprites/blueCeleb.png", 639, 150)};
	private Umpire umpire = new Umpire(this, new Point(575, 180), "sprites/umpire/umpiSprite.png");
	private Team teamField, teamBat;
	Jumbotron jumbotron = new Jumbotron();
	private AudioPlayer audioPlayer;	
	ArrayList<Drawable> drawables = new ArrayList<>();
	private final int PANEL_WIDTH = 1000;
	private final int PANEL_HEIGHT = 800;
	private int FPS = 60;
	private Thread gameThread;
	private boolean endBall = false;
	private boolean availableBasePitch = true;
	
	public Panel(AudioPlayer audioPlayer) throws IOException{
		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		this.setDoubleBuffered(true);
		this.audioPlayer = audioPlayer;
		background = ImageIO.read(ResourceLoader.load("sprites/back.png"));
	}

	public void setEndBallFalse() {
		endBall = false;
	}
	
	public void setEndBallTrue() {
		endBall = true;
	}
	
	public boolean getEndBall() {
		return endBall;
	}
	
	public void startUIThread() {
		gameThread = new Thread(this);
		audioPlayer.play(Sounds.THEME);
		drawables.add(jumbotron);
		gameThread.start();
	}

	@Override
	public void run() {
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
		for (Team team: teams) {
			team.moveTeam();
		}
	}
	
	public void paintComponent(Graphics g) {		
		super.paintComponent(g);		
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(background, 0, 0, null);		
		for (Drawable drawable: drawables) {
			drawable.draw(g2D);
		}		
	}
	
	public void inningStart(String str) {		
		if (str.equals("top")) {
			teamField = teams[1];
			teamBat = teams[0];
		} else {
			teamField = teams[0];
			teamBat = teams[1];
		}	
		audioPlayer.play(Sounds.CROWD);
		teamField.getPlayer(0).setDestination(Bases.MOUNT.point);
		teamField.getPlayer(1).setDestination(Bases.HOME.field);
		teamField.getPlayer(2).setDestination(Bases.FIRST.field);
		teamField.getPlayer(3).setDestination(Bases.SECOND.field);
		teamField.getPlayer(4).setDestination(Bases.THIRD.field);		
		for (Player player: teamField.getAllPlayers()) {
			player.setField();
		}
	}
	
	public void turnStart() {	
		availableBasePitch = true;
		teamBat.getPlayer(teamBat.getBatter()).setDestination(Bases.HOME.bat);
		teamBat.getPlayer(teamBat.getBatter()).stopAnimation();
		teamBat.getPlayer(teamBat.getBatter()).setBase(1);
	}

	public void cycleBases(String str) {
		int a = Integer.valueOf(str);
		for (int i = 0; i < a; i++) {
			teamBat.cheers();
			for (Player player: teamBat.getAllPlayers()) {;
				if (player.getBase() > 0) {
					player.setBase(player.getBase()+1);
					switch (player.getBase()) {
						case 5:
							player.setDestination(Bases.HOME.bat);
							audioPlayer.play(Sounds.CROWD);
							player.returnBench();;
							break;
						case 4:
							player.setDestination(Bases.THIRD.bat);
							break;
						case 3:
							player.setDestination(Bases.SECOND.bat);
							break;
						case 2:
							player.setDestination(Bases.FIRST.bat);
							player.setIdle();
							teamBat.cycleBatter();
							break;
					}
				}
			}
		}
	}
	
	public void cycleBasesSteal() {
			teamBat.cheers();
			for (Player player: teamBat.getAllPlayers()) {;
				if (player.getBase() > 1) {
					player.setBase(player.getBase()+1);
					switch (player.getBase()) {
						case 5:
							player.setDestination(Bases.HOME.bat);
							audioPlayer.play(Sounds.CROWD);
							player.returnBench();;
							break;
						case 4:
							player.setDestination(Bases.THIRD.bat);
							break;
						case 3:
							player.setDestination(Bases.SECOND.bat);
							break;
					}
				}
			}
	}
	
	public void clearBatter() {
		teamField.cheers();
		audioPlayer.play(Sounds.CROWD);
		teamBat.getPlayer(teamBat.getBatter()).returnBench();
		teamBat.getPlayer(teamBat.getBatter()).setIdle();
		teamBat.cycleBatter();
	}
	
	public void returnBench() {
		teamField.cheers();
		audioPlayer.play(Sounds.CROWD);
		for (Team team: teams) {
			team.returnBench();
		}
	}
	
	public void removeLastDrawable() {
		drawables.remove(drawables.size() - 1);
	}
	
	public Umpire getUmpire() {
		return umpire;
	}

	public Jumbotron getJumbotron() {
		return jumbotron;
	}

	public ArrayList<Drawable> getDrawables() {
		return drawables;
	}
	
	public void addTeamsToDrawables() {	
		for (Team team: teams) {
			for (Player player: team.getAllPlayers()) {
				drawables.add(player);
				try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
			}
		}
		drawables.remove(umpire);
		drawables.add(umpire);
	}

	public void removeRunner(int i) {
		for (Player player: teamBat.getAllPlayers()) {;
			if (player.getBase() == i) {
				player.returnBench();
			}
		}
	}

	public Team getTeamBat() {
		return teamBat;		
	}

	public boolean isAvailableBasePitch() {
		return availableBasePitch;
	}

	public void setAvailableBasePitch(boolean availableBasePitch) {
		this.availableBasePitch = availableBasePitch;
	}
	
}
