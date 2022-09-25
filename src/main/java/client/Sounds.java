package client;

public enum Sounds {
	
	THEME("sounds/theme.wav"),
	CROWD("sounds/crowd.wav"),
	HIT("sounds/hit.wav"),
	PITCH("sounds/pitch.wav"),
	STRIKE("sounds/strike.wav"),
	OUT("sounds/out.wav");
	
	final String path;
	
	Sounds(String str) {
		path = str;
	}
	
}
