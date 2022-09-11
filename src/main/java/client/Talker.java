package client;

import java.awt.Point;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import uiElements.InningKeyboard;
import uiElements.AnswerKeyboard;
import uiElements.Keyboard;

public class Talker extends Thread {
	
	private ArrayList<String> inputLog = new ArrayList<>();
	private Socket server;
	private BufferedReader in;
	private Panel panel;
	private AudioPlayer audioPlayer;
	private boolean team;
	private Point keysCoords;
	
	public Talker(Socket s, Panel panel, AudioPlayer ap) throws IOException {
		this.panel = panel;
		server = s; 
		in = new BufferedReader(new InputStreamReader(server.getInputStream()));
		audioPlayer = ap;
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
							answerSender(); 
							break;
						case "inningSender":
							panel.umpire.setTalk(arrResp[2]);
							inningSender();
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
							answerSender();
							break;
						case "team":
							if (arrResp[2].equals("true")) {
								team = true;
								keysCoords = new Point(270, 330);
							} else {
								team = false;
								keysCoords = new Point(550, 330);
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

	private void inningSender() {
		InningKeyboard inningKeyboard = new InningKeyboard(panel);
		inningKeyboard.setButtons(panel);
		panel.drawables.add(inningKeyboard);
		sender(inningKeyboard);
		panel.drawables.remove(panel.drawables.size()-1);
		inningKeyboard.removeButtons();
		panel.remove(inningKeyboard);
	}
	
	private void answerSender() {
		AnswerKeyboard answerKeyboard = new AnswerKeyboard(panel, keysCoords);
		answerKeyboard.setColor(team);
		panel.drawables.add(answerKeyboard);
		sender(answerKeyboard);
		panel.drawables.remove(panel.drawables.size()-1);
		keysCoords.setLocation(answerKeyboard.getLocation());
		answerKeyboard.removeButtons();
		panel.remove(answerKeyboard);
	}
	
	private void sender(Keyboard key) {
		while (true) {
			try {Thread.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
			if (!key.getStoredAnswer().equals("")) {
				break;
			}
		}
		try {
			Socket sendSock = server;
			PrintWriter out = new PrintWriter(sendSock.getOutputStream(), true);
			out.println(key.getStoredAnswer());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
