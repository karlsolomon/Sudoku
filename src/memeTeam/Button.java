package memeTeam;

import processing.core.*;
import processing.event.MouseEvent;

public abstract class Button {
	float xPos; 
	float yPos; 
	float w; 
	float h; 
	PApplet parent;
	int valueToBeShown;
	Button(PApplet p,float xPos, float yPos, float width, float height) {
		this.xPos = xPos; 
		this.yPos = yPos; 
		w = width; 
		h = height;
		parent = p;
		valueToBeShown = 0;
	}
	
	abstract void display();
	abstract void handleClick(MouseEvent e);
	
	public float getXPos() {
		return xPos;
	}
	public float getYPos() {
		return yPos;
	}
	protected float getWidth() {
		return w; 
	}
	protected float getHeight() {
		return h;
	}
	protected PApplet getPApplet() {
		return parent;
	}
	public boolean wasClicked(MouseEvent e) {
		boolean withinX = e.getX()>=xPos && e.getX()<=xPos+w; 
		boolean withinY = e.getY()>=yPos && e.getY()<=yPos+h; 
		return withinX && withinY;
	}
}
