// Tous droits réservés, copyright 2022 Dominic Daoust

package client;

import java.net.*;

import uiElements.Panel;
import uiElements.Window;

import java.io.*;

public class Client {
	
	public static void main(String[] args) throws IOException {
		Window window = new Window();
		AudioPlayer audioPlayer = new AudioPlayer();
		Panel panel = new Panel(audioPlayer);
		Socket server = new Socket("192.168.1.195", 5565);
		System.out.println("connected to server");
		Talker talker = new Talker(server, panel, audioPlayer);
		
		window.add(panel);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		talker.start();
		panel.startUIThread();	
	}
	
}
