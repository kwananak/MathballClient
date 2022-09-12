package client;

import java.awt.Point;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import uiElements.InningKeyboard;
import uiElements.AnswerKeyboard;
import uiElements.PitchKeyboard;

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
							answerKeysCoords.setLocation(Sender.send(new AnswerKeyboard(panel, answerKeysCoords, team), socket, panel)); 
							break;
						case "inningSender":
							panel.umpire.setTalk(arrResp[2]);
							Sender.send(new InningKeyboard(panel), socket, panel);
							break;
						case "inningStart": 
							panel.inningStart(arrResp[2]); 
							break;
						case "turnStart": 
							panel.turnStart();
							panel.jumbotron.updateJumbotron(arrResp[2]);
							panel.umpire.setTalk(" ");
							break;
						case "cycleBases":
							audioPlayer.playHit();
							panel.cycleBases(arrResp[2]);
							panel.jumbotron.setMainDisplay(arrResp[2]);
							break;
						case "clearBatter":
							panel.clearBatter();
							panel.umpire.setTalk("Out!");
							break;
						case "returnBench":
							panel.returnBench();
							break;
						case "jumbotron":
							panel.jumbotron.updateJumbotron(arrResp[2]);
							break;
						case "umpire":
							panel.umpire.setTalk(arrResp[2]);
							break;
						case "pitch":
							audioPlayer.playPitch();
							panel.umpire.setTalk(arrResp[2]);
							pitchKeysCoords.setLocation(Sender.send(new PitchKeyboard(panel, pitchKeysCoords), socket, panel));
							break;
						case "team":
							if (arrResp[2].equals("true")) {
								team = true;
								answerKeysCoords = new Point(270, 330);
							} else {
								team = false;
								answerKeysCoords = new Point(550, 330);
							}
							break;
						case "startLoop":
							panel.jumbotron.updateJumbotron(arrResp[2]);
							panel.umpire.setTalk(" ");
							break;
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
