package memeTeam;
import processing.core.*;
public class VisualGrid {
	int gapPerSquare; 
	final int numSquares = 9;
	PApplet parent;
	int buffer;
	public VisualGrid(PApplet p) {
		gapPerSquare = p.height/(numSquares+1);
		parent = p;
		buffer = gapPerSquare/2;
	}
	public void display() {
		drawHorizontalLines(); 
		drawVerticalLines();
		
	}
	public void drawHorizontalLines() {
		for (int i = 0; i < numSquares+1; i++) {
			if (i%3 == 0) {
				parent.strokeWeight(5);
			} else {
				parent.strokeWeight(0);
			}
		
			parent.line(buffer,buffer+i*gapPerSquare,parent.width-buffer,buffer+i*gapPerSquare);
		}
	}
	public void drawVerticalLines() {
		for (int i = 0; i < numSquares+1; i++) {
			if (i%3 == 0) {
				parent.strokeWeight(5); 
			} else {
				parent.strokeWeight(0);
			}
			parent.line(buffer+i*gapPerSquare,buffer,buffer+i*gapPerSquare,parent.height-buffer);
		}
	}
}
