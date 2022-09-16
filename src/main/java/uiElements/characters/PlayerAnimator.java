package uiElements.characters;

public class PlayerAnimator {

	private Player player;
	private final int velocity = 3;	
	private int framesCount = 0;
	private int animCount = 10;
	private int animSpeed = 15;
	private boolean animFlip = false;
	private boolean animPrimer = true;
	
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

	public void celebrate() {
		if (animPrimer) {
			animStarter();			
		}
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
			framesCount = animSpeed;
			animCount--;
			animFlip = !animFlip;
			if (animCount == 0) {
				interrupt();
			}
		}		
	}
	
	private void animStarter() {
		framesCount = (int) (((Math.random() * 10) / 2) * 3);
		animPrimer = false;
	}
	
	public void interrupt() {
		animCount = 9;
		animPrimer = true;
		player.setAnimationFlipper(false);
		if (player.getField()) {
			player.setField();
		} else {
			if (player.getBase() == 1) {
				player.setBat();
			} else {
				player.setIdle();
			}
		}
	}
	
}
