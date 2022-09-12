package uiElements.characters;

public class PlayerAnimator {

	private Player player;
	private final int velocity = 3;
	
	int framesCount = 10;
	int animCount = 9;
	boolean animFlip = true;
	
	
	public PlayerAnimator(Player player) {
		this.player = player;
	}
	
	public void move() {
		if (player.destinations.size() > 0) {
			if (player.getBase() == 1) {
				player.setBat();
			}
			if (player.getX() < player.destinations.get(0).getX() - velocity + 1) {
				player.setLocation(player.getX() + velocity, player.getY());
			}			
			if (player.getX() > player.destinations.get(0).getX() + velocity - 1) {
				player.setLocation(player.getX() - velocity, player.getY());
			}			
			if (player.getY() < player.destinations.get(0).getY() - velocity + 1) {
				player.setLocation(player.getX(), player.getY() + velocity);
			}			
			if (player.getY() > player.destinations.get(0).getY() + velocity - 1) {
				player.setLocation(player.getX(), player.getY() - velocity);
			}
			if (player.getLocation().equals(player.destinations.get(0))) {
				player.destinations.remove(0);
			}
		}
		
	}

	public void animate() {
		if (animFlip) {
			if (player.field) {
				player.setCelebField();
			} else {
				player.setCeleb();
			}
		} else {
			if (player.field) {
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
				if (player.field) {
					player.setField();
				} else {
					player.setIdle();
				}
			}
		}

		
	}
}
