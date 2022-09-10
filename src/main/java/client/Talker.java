package client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Talker extends Thread {
	
	private static ArrayList<String> inputLog = new ArrayList<>();
	private Socket server;
	private BufferedReader in;
	private Panel panel;
	private AudioPlayer audioPlayer;
	
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
							sender(server); 
							break;
						case "inningSender":
							panel.umpire.setTalk(arrResp[2]);
							inningSender(server);
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
							sender(server);
							break;
						case "team":
							panel.keyboard.setColor(arrResp[2]);
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

	private void inningSender(Socket s) {
		panel.inningKeyboard.flipKeyOn();
		while (true) {
			try {Thread.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
			if (!panel.inningKeyboard.getStoredAnswer().equals("")) {
				break;
			}
		}
		try {
			Socket sendSock = s;
			PrintWriter out = new PrintWriter(sendSock.getOutputStream(), true);
			out.println(panel.inningKeyboard.getStoredAnswer());
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true)
			if (panel.inningKeyboard.isKeyOn()) {
				panel.inningKeyboard.flipKeyOn();
				break;
			}
		panel.inningKeyboard.clearStoredAnswer();
	}
	
	private void sender(Socket s) {
		panel.keyboard.flipKeyOn();
		while (true) {
			try {Thread.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
			if (!panel.keyboard.getStoredAnswer().equals("")) {
				break;
			}
		}
		try {
			Socket sendSock = s;
			PrintWriter out = new PrintWriter(sendSock.getOutputStream(), true);
			out.println(panel.keyboard.getStoredAnswer());
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true)
			if (panel.keyboard.isKeyOn()) {
				panel.keyboard.flipKeyOn();
				break;
			}
		panel.keyboard.clearStoredAnswer();
	}
	
}
