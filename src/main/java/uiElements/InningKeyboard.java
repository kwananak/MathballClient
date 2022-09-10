package uiElements;

import java.io.IOException;

import javax.imageio.ImageIO;

import client.Panel;

public class InningKeyboard extends Keyboards{
	
	Button button1, button2, button3, button4, button5;
	
	public InningKeyboard(Panel p) {
		super(p);
		coords[0] = 315;
		coords[1] = 360;
		try {
			board = ImageIO.read(ResourceLoader.load("sprites/inningKey.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setButtons() {
		button1 = new Button(panel,this,"1", coords[0] + 10, coords[1] + 10, 60, 60);
		button2 = new Button(panel,this,"2", coords[0] + 80, coords[1] + 10, 60, 60);
		button3 = new Button(panel,this,"3", coords[0] + 150, coords[1] + 10, 60, 60);
		button4 = new Button(panel,this,"4", coords[0] + 220, coords[1] + 10, 60, 60);
		button5 = new Button(panel,this,"5", coords[0] + 290, coords[1] + 10, 60, 60);
	}

	public void addInput(String buttonID) {
		setStoredAnswer(buttonID);
		System.out.println(buttonID);
	}
		
}
