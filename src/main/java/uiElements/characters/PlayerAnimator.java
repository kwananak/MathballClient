package uiElements.characters;

public class PlayerAnimator {

	private Player player;
	private final int velocity = 3;	
	private int framesCount = 10;
	private int animCount = 9;
	private boolean animFlip = true;
	
	public PlayerAnimator(Player player) {
		this.player = player;
	}
	
	public void move() {
		if (player.hasDestinations()) {
			if (player.getBase() == 1) {
				player.setBat();
			}
			if (player.getX() < player.getNextDestination().getX() - velocity + 1) {
				player.setLocation(player.getX() + velocity, player.getY());
			}			
			if (player.getX() > player.getNextDestination().getX() + velocity - 1) {
				player.setLocation(player.getX() - velocity, player.getY());
			}			
			if (player.getY() < player.getNextDestination().getY() - velocity + 1) {
				player.setLocation(player.getX(), player.getY() + velocity);
			}			
			if (player.getY() > player.getNextDestination().getY() + velocity - 1) {
				player.setLocation(player.getX(), player.getY() - velocity);
			}
			if (player.getLocation().equals(player.getNextDestination())) {
				player.arrived();
			}
		}		
	}

	public void animate() {
		if (animFlip) {
			if (player.getField()) {
				player.setCelebField();
			} else {
				player.setCeleb();
			}
		} else {
			if (player.getField()) {
				player.setField();
			} else {
				player.setIdle();
			}
		}			
		framesCount--;
		if (framesCount == 0) {
			framesCount = 10;
			animCount--;
			animFlip = !animFlip;
			if (animCount == 0) {
				animCount = 9;
				player.setAnimationFlipper(false);
				if (player.getField()) {
					player.setField();
				} else {
					player.setIdle();
				}
			}
		}		
	}
	
}
