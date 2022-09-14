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
		buttons[0] = new Button(panel, this, "1", 16, 68, 38, 38);
		buttons[1] = new Button(panel, this, "2", 60, 68, 38, 38);
		buttons[2] = new Button(panel, this, "3", 104, 68, 38, 38);
		buttons[3] = new Button(panel, this, "4", 16, 110, 38, 38);
		buttons[4] = new Button(panel, this, "5", 60, 110, 38, 38);
		buttons[5] = new Button(panel, this, "6", 104, 110, 38, 38);
		buttons[6] = new Button(panel, this, "7", 16, 152, 38, 38);
		buttons[7] = new Button(panel, this, "8", 60, 152, 38, 38);
		buttons[8] = new Button(panel, this, "9", 104, 152, 38, 38);
		buttons[9] = new Button(panel, this, "0", 16, 194, 38, 38);
		buttons[10] = new Button(panel, this, "C", 60, 194, 38, 38);
		buttons[11] = new Button(panel, this, "E", 104, 194, 38, 38);
		setBounds(getX(), getY(), 140, 15);
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
		g2D.drawString(answerString, getX() + 31, getY() + 52);		
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
