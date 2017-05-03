package memeTeam;
import ddf.minim.*;

public class Sound {	
	Main main;
	boolean muted = false;
	Minim minim;
	AudioPlayer background;
	AudioPlayer correctSection;
	AudioPlayer correctBoard;
	AudioPlayer invalidEntry;
	
	public Sound(Main main) {
		this.main = main;
		minim = new Minim(main);
		background = minim.loadFile("background.wav");
		correctSection = minim.loadFile("chime_up.wav");
		correctBoard = minim.loadFile("fanfare3.wav");
		invalidEntry = minim.loadFile("boing_x.wav");
		background.play();
		background.loop();
	}
	
	void toggleBackground() {
		if(background.isPlaying()) {
			System.out.println("mute");
			background.pause();
		} else {
			System.out.println("play");
			background.play();
		}
	}
	
	void correctSection() {
		correctSection.play();
	}
	
	void correctBoard() {
		correctBoard.play();
	}
	
	void invalidEntry() {
		invalidEntry.play();
	}	
}
