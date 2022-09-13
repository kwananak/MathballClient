package client;

import java.awt.Point;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import uiElements.Panel;
import uiElements.keyboards.AnswerKeyboard;
import uiElements.keyboards.Ball;
import uiElements.keyboards.InningKeyboard;
import uiElements.keyboards.PitchKeyboard;

public class Talker extends Thread {
	
	private ArrayList<String> inputLog = new ArrayList<>();
	private final Socket socket;
	private final BufferedReader in;
	private final Panel panel;
	private final AudioPlayer audioPlayer;
	private boolean team;
	private Point answerKeysCoords;
	private Point pitchKeysCoords = new Point(360, 430);
	
	public Talker(Socket socket, Panel panel, AudioPlayer audioPlayer) throws IOException {
		this.panel = panel;
		this.socket = socket;
		this.audioPlayer = audioPlayer; 
		in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));		
	}

	public void run() {
		System.out.println("Talker started");
		try {
			while (true) {
				String serverResponse = in.readLine();
				inputLog.add(serverResponse);
				if (serverResponse.startsWith("command")) {
					String[] arrResp = serverResponse.split(":");
					switch (arrResp[1]) {
						case "sender":
							audioPlayer.playPitch(); 
							answerKeysCoords.setLocation(Sender.send(new AnswerKeyboard(panel, answerKeysCoords, team), socket, panel)); 
							panel.removeLastDrawable();
							break;
						case "inningSender":
							panel.getUmpire().setTalk(arrResp[2]);
							Sender.send(new InningKeyboard(panel), socket, panel);
							break;
						case "inningStart": 
							panel.inningStart(arrResp[2]); 
							break;
						case "turnStart": 
							panel.turnStart();
							panel.getJumbotron().updateJumbotron(arrResp[2]);
							panel.getUmpire().setTalk(" ");
							break;
						case "cycleBases":
							audioPlayer.playHit();
							audioPlayer.playCrowd();
							panel.cycleBases(arrResp[2]);
							panel.getJumbotron().setMainDisplay(arrResp[2]);
							break;
						case "clearBatter":
							panel.clearBatter();
							panel.getUmpire().setTalk("Out!");
							break;
						case "returnBench":
							panel.returnBench();
							break;
						case "jumbotron":
							panel.getJumbotron().updateJumbotron(arrResp[2]);
							break;
						case "umpire":
							panel.getUmpire().setTalk(arrResp[2]);
							break;
						case "pitch":
							panel.getUmpire().setTalk(arrResp[2]);
							pitchKeysCoords.setLocation(Sender.send(new PitchKeyboard(panel, pitchKeysCoords), socket, panel));
							break;
						case "team":
							if (arrResp[2].equals("true")) {
								team = true;
								answerKeysCoords = new Point(120, 540);
							} else {
								team = false;
								answerKeysCoords = new Point(720, 540);
							}
							break;
						case "startLoop":
							panel.getJumbotron().updateJumbotron(arrResp[2]);
							panel.getUmpire().setTalk(" ");
							break;
						case "ball":
							panel.getUmpire().setTalk(" ");
							panel.getDrawables().add(new Ball(panel, arrResp[2]));
							break;
						case "startGame":
							panel.addTeamsToDrawables();
							panel.getUmpire().setTalk(arrResp[2]);				
					}
				} else {
					System.out.println("server says: " + inputLog.get(inputLog.size()-1));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}		
	
}
