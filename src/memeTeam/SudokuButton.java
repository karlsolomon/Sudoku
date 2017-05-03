package memeTeam;
import java.util.ArrayList;

import processing.core.*;
import processing.event.MouseEvent;
public class SudokuButton extends Button {
	String value;
	private boolean isClicked = false;
	boolean isGiven = false;
	private int textFill;
	private static final int xBuffer = -5;
	private static final int yBuffer = 10;
	
	public SudokuButton(PApplet p,float xPos, float yPos, float size) {
		super(p,xPos, yPos, size, size);
		this.value = "";
		this.textFill = p.color(255,50,50);
	}
	
	public SudokuButton(PApplet p,float xPos, float yPos, float size, String value) {
		super(p,xPos, yPos, size, size);
		this.isGiven = true;
		this.value = value;
		this.textFill = p.color(0);
	}
	
	@Override
	void display() {
		PApplet p = super.getPApplet();
		if (isClicked) {
			p.fill(255,255,4,80);
		} else {
			p.fill(255,255,255,30);
		}
		super.getPApplet().rect(super.getXPos(), super.getYPos(), super.getWidth(), super.getHeight());
		if(this.value != "") {
			p.fill(this.textFill);
			p.text(this.value, super.getXPos() + super.getWidth()/2 + xBuffer, super.getYPos() + super.getHeight()/2 + yBuffer);
		}
	}
	
	public void setValue(String value) {
		if(value == "0") {
			this.value = "";
		} else {
			this.value = value;
		}
	}
	
	public String getValue() {
		return this.value;
	}
	
	public void animateCorrect() {
		//TODO: PROBABLY NEED AN ANIMATION HIERARCHY HERE. MAYBE ADD A SPARKLY TEXTURE OVER THE 
	}
	
	public void animateInvalid() {
		//TODO: MAYBE VIBRATE NUMBER & TURN RED, THEN CLEAR THE VALUE
	}
	
	public void handleClick(MouseEvent e, ArrayList<Button> buttons) {
		if(!isGiven) {
			if(this.wasClicked(e)) {
				for(Button b : buttons) {
					if(b instanceof SudokuButton) {
						((SudokuButton) b).isClicked = false;
					}
				}
				this.isClicked = !this.isClicked;
			}
		}
	}
	
	public boolean isClicked() {
		return this.isClicked;
	}

	@Override
	void handleClick(MouseEvent e) {
		//TODO
	}
}
