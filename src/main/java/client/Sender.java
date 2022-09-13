package client;

import java.awt.Point;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import uiElements.Panel;
import uiElements.keyboards.Keyboard;

public abstract class Sender {
	
	public static Point send(Keyboard keyboard, Socket server, Panel panel) {		
		panel.getDrawables().add(keyboard);		
		while (true) {
			try {Thread.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
			if (!keyboard.getStoredAnswer().equals("")) {
				break;
			}
		}
		try {
			Socket sendSock = server;
			PrintWriter out = new PrintWriter(sendSock.getOutputStream(), true);
			out.println(keyboard.getStoredAnswer());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Point savedCoords = new Point(keyboard.getLocation());
		keyboard.removeButtons();
		panel.removeLastDrawable();
		panel.remove(keyboard);
		panel.getUmpire().setTalk(" ");
		return savedCoords;		
	}
	
}
