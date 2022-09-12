package uiElements;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.IOException;
import javax.imageio.ImageIO;

import client.Panel;

@SuppressWarnings("serial")
public class AnswerKeyboard extends Keyboard {

	private String[] answer = {"  ", "  ", "  ", "  ", "  "};

	public AnswerKeyboard(Panel panel, Point point, boolean team) throws IOException {
		super(panel, point);
		if(team) {			
			sprite = ImageIO.read(ResourceLoader.load("sprites/redKey.png"));
		} else {
			sprite = ImageIO.read(ResourceLoader.load("sprites/blueKey.png"));
		}
		buttons = new Button[12];
		buttons[0] = new Button(panel, this, "1", 12, 56, 36, 36);
		buttons[1] = new Button(panel, this, "2", 54, 56, 36, 36);
		buttons[2] = new Button(panel, this, "3", 96, 56, 36, 36);
		buttons[3] = new Button(panel, this, "4", 12, 98, 36, 36);
		buttons[4] = new Button(panel, this, "5", 54, 98, 36, 36);
		buttons[5] = new Button(panel, this, "6", 96, 98, 36, 36);
		buttons[6] = new Button(panel, this, "7", 12, 140, 36, 36);
		buttons[7] = new Button(panel, this, "8", 54, 140, 36, 36);
		buttons[8] = new Button(panel, this, "9", 96, 140, 36, 36);
		buttons[9] = new Button(panel, this, "0", 12, 182, 36, 36);
		buttons[10] = new Button(panel, this, "C", 54, 182, 36, 36);
		buttons[11] = new Button(panel, this, "E", 96, 182, 36, 36);
		setBounds(getX(), getY(), 140, 10);
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
	
	@Override
	public void draw(Graphics2D g2D) {
		super.draw(g2D);
		String answerString = "";
		for (String digit: answer) {
			answerString += digit;
		}
		g2D.setFont(new Font("Fixedsys",Font.BOLD, 36));
		g2D.drawString(answerString, getX() + 20, getY() + 46);		
	}

}
