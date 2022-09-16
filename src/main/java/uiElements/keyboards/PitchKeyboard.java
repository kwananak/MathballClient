package uiElements.keyboards;

import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import uiElements.Panel;
import uiElements.ResourceLoader;
import uiElements.characters.Player;

@SuppressWarnings("serial")
public class PitchKeyboard extends Keyboard {
	
	ArrayList<BaseThrowButton> baseThrowButtons = new ArrayList<>();
	
	public PitchKeyboard(Panel panel, Point point) throws IOException {
		super(panel, point, "sprites/pitchKey.png");
		buttons = new Button[2];
		buttons[0] = new Button(panel, this, "0", 15, 15, 80, 90);
		buttons[1] = new Button(panel, this, "4", 110, 15, 80, 90);

		setBounds(getX(), getY(), 200, 15);
		if (panel.isAvailableBasePitch()) {
			for (Player player: panel.getTeamBat().getAllPlayers()) {
				if (player.getBase() == 2) {
					baseThrowButtons.add(new BaseThrowButton(panel, this, "sprites/firstBaseThrow.png", "1", 300, 400, 90, 80));
				}
				if (player.getBase() == 3) {
					baseThrowButtons.add(new BaseThrowButton(panel, this, "sprites/secondBaseThrow.png", "2", 460, 510, 80, 90));
				}
				if (player.getBase() == 4) {
					baseThrowButtons.add(new BaseThrowButton(panel, this, "sprites/thirdBaseThrow.png", "3", 600, 400, 90, 80));
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			buttonPressed("4");
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			buttonPressed("0");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void draw(Graphics2D g2D) {		
		super.draw(g2D);
		if (!baseThrowButtons.isEmpty()) {
			for (BaseThrowButton btb: baseThrowButtons) {
				g2D.drawImage(btb.image, btb.getX(), btb.getY(), this);
			}
		}
	}
		
	@Override
	public void removeButtons() {
		super.removeButtons();
		if (!baseThrowButtons.isEmpty()) {
			for (BaseThrowButton btb: baseThrowButtons) {
				panel.remove(btb);
			}
		}
	}
}
