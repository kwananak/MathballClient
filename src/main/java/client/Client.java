package client;

import java.net.*;
import java.io.*;

public class Client {
	
	static Window window = new Window();
	static AudioPlayer audioPlayer = new AudioPlayer();
	static Panel panel = new Panel(audioPlayer);

	
	public static void main(String[] args) throws IOException {
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
