package uiElements.keyboards;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.imageio.ImageIO;

import uiElements.Panel;
import uiElements.ResourceLoader;

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
	
	@Override
	public void buttonPressed(String str) {
		if (str.equals("C")) {
			deleteInput();
		} else if (str.equals("E")) {
			confirmInput();
		} else {		
			addInput(str);
		}	
	}
	
	public void addInput(String str) {
		answer[0] = answer[1];
		answer[1] = answer[2];
		answer[2] = answer[3];
		answer[3] = answer[4];
		answer[4] = str;
	}
	
	public void deleteInput() {
		answer[4] = answer[3];
		answer[3] = answer[2];
		answer[2] = answer[1];
		answer[1] = answer[0];
		answer[0] = "  ";
	}
	
	public void confirmInput() {
		if (!answer[4].equals("  ")) {
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
		}
	}
	
	@Override
	public void draw(Graphics2D g2D) {
		super.draw(g2D);
		String answerString = "";
		for (String digit: answer) {
			answerString += digit;
		}
		g2D.setPaint(Color.white);
		g2D.setFont(new Font("Fixedsys",Font.BOLD, 36));
		g2D.drawString(answerString, getX() + 20, getY() + 46);		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if (Character.isDigit(c)) {
			String nu = "";
			nu += c;
			addInput(nu);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) {
			deleteInput();
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			confirmInput();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

}
