package uiElements;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import client.Panel;

public class Keyboard extends Keyboards {
		
	int[] coords = {0,230};
	String[] answer = {"  ", "  ", "  ", "  ", "  "};
	Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0, buttonC, buttonE;

	public Keyboard(Panel p) {
		super(p);
	}

	public void addInput(String str) {
		if (str.equals("C")) {
			answer[4] = answer[3];
			answer[3] = answer[2];
			answer[2] = answer[1];
			answer[1] = answer[0];
			answer[0] = "  ";
		} else if (str.equals("E")) {
			String answerString = "";
			for (String digit: answer) {
				if (!digit.equals("  ")) {
					answerString += digit;
				}
			}
			setStoredAnswer(answerString);
			answer[0] = "  ";
			answer[1] = "  ";
			answer[2] = "  ";
			answer[3] = "  ";
			answer[4] = "  ";
		} else {		
			answer[0] = answer[1];
			answer[1] = answer[2];
			answer[2] = answer[3];
			answer[3] = answer[4];
			answer[4] = str;
		}	
	}
		
	public void setColor(String str) {	
		if(str.equals("true")) {
			try {
				board = ImageIO.read(ResourceLoader.load("sprites/redKey.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			coords[0] = 170;
		} else {
			try {
				board = ImageIO.read(ResourceLoader.load("sprites/blueKey.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			coords[0] = 450;
		} 
		
		button1 = new Button(panel,this,"1", coords[0] + 132, coords[1] + 156, 36, 36);
		button2 = new Button(panel,this,"2", coords[0] + 174, coords[1] + 156, 36, 36);
		button3 = new Button(panel,this,"3", coords[0] + 216, coords[1] + 156, 36, 36);
		button4 = new Button(panel,this,"4", coords[0] + 132, coords[1] + 198, 36, 36);
		button5 = new Button(panel,this,"5", coords[0] + 174, coords[1] + 198, 36, 36);
		button6 = new Button(panel,this,"6", coords[0] + 216, coords[1] + 198, 36, 36);
		button7 = new Button(panel,this,"7", coords[0] + 132, coords[1] + 240, 36, 36);
		button8 = new Button(panel,this,"8", coords[0] + 174, coords[1] + 240, 36, 36);
		button9 = new Button(panel,this,"9", coords[0] + 216, coords[1] + 240, 36, 36);
		button0 = new Button(panel,this,"0", coords[0] + 132, coords[1] + 282, 36, 36);
		buttonC = new Button(panel,this,"C", coords[0] + 174, coords[1] + 282, 36, 36);
		buttonE = new Button(panel,this,"E", coords[0] + 216, coords[1] + 282, 36, 36);
	}
	
	public void draw(Graphics2D g2D) {
		if (keyOn) {
			String answerString = "";
			for (String digit: answer) {
				answerString += digit;
			}	
			g2D.drawImage(board, coords[0], coords[1], null);
			g2D.setFont(new Font("Fixedsys",Font.BOLD, 36));
			g2D.drawString(answerString, coords[0] + 142, coords[1] + 142);
		}
	}

}
