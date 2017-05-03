package memeTeam;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
public class Main extends PApplet{
	GUI gui;
	VisualGrid grid;
	String keyDown;
	static Sound sound;
	public static void main(String[] args) {
		PApplet.main("memeTeam.Main");
	}
	public void settings() {
		size(500,500);
	}
	
	public void setup() {
		SudokuPuzzle puzzle = new SudokuPuzzle(0);
		grid = new VisualGrid(this);
		gui = new GUI(this,puzzle);
		keyDown = "a";
		Main.sound = new Sound(this);
	}
	public void draw() {
		background(255);
		grid.display();		
		gui.display();
	}
	
	public void mouseClicked(MouseEvent e) {
		gui.mouseClicked(e);
	}
	public void keyPressed(KeyEvent e) {
		if (key >= '0' && key <= '9'){
			gui.keyPressed(e);
		}
		else if(key == 'm') {
			sound.toggleBackground();
		}
	}
	
	public void keyReleased() {
		keyDown = "a";
	}
}
