package memeTeam;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.util.*;
public class GUI {
	SudokuPuzzle puzzle;
	SudokuButton[][] board;
	PApplet parent;
	ArrayList<Button> buttons;
	public GUI(PApplet p,SudokuPuzzle puzzle) {
		this.puzzle = puzzle;
		this.parent = p;
		this.parent.textSize(20);
		createSudokuButtons(puzzle.getPuzzle().length);
	}
	public void display() {
		parent.noStroke();
		displayButtons();
		parent.stroke(0);
	}
	
	public void keyPressed(KeyEvent e) {		
		for(int i = 0; i < SudokuPuzzle.SIZE; i++) {
			for(int j = 0; j < SudokuPuzzle.SIZE; j++) {
				if(board[i][j].isClicked()) {
					board[i][j].setValue(Character.toString(e.getKey()));
					String[][] stringBoard = boardToString();
					boolean row = puzzle.rowIsCorrect(i,stringBoard);
					boolean column = puzzle.columnIsCorrect(j, stringBoard);
					boolean region = puzzle.regionIsCorrect(i, j, stringBoard);
					if(row || column || region) {
						Main.sound.correctSection();
						if(row) {
							animateCorrect(i, 0, i+1, SudokuPuzzle.SIZE);
						}
						if(column) {
							animateCorrect(0, j, 1, j);
						}
						if(region){
							int startRow = i - i%SudokuPuzzle.SIZESQRT;
							int startColumn = j - j%SudokuPuzzle.SIZESQRT;
							animateCorrect(startRow, startColumn, startRow + SudokuPuzzle.SIZESQRT, startColumn + SudokuPuzzle.SIZESQRT);
						}
					}
					if(puzzle.puzzleIsCorrect(stringBoard)) {
						Main.sound.correctBoard();
					}
				}
			}
		}
	}
	
	private String[][] boardToString() {
		String[][] stringBoard = new String[SudokuPuzzle.SIZE][SudokuPuzzle.SIZE];
		for(int i = 0; i < SudokuPuzzle.SIZE; i++) {
			for(int j = 0; j < SudokuPuzzle.SIZE; j++) {
				stringBoard[i][j] = board[i][j].getValue();
			}
		}
		return stringBoard;
	}
	
	public void animateCorrect(int row1, int column1, int row2, int column2) {
		for(int i = row1; i < row2; i++) {
			for(int j = column1; j < column2; j++) {
				board[i][j].animateCorrect();
			}
		}
	}
	
	public void animateConflict(int newRow, int newColumn, int oldRow, int oldColumn) { //do when there is an obvious conflict in row/column/section
		//TO
		
	}
	
	public void mouseClicked(MouseEvent e) {		
		for(Button b: buttons) {
			if(b instanceof SudokuButton) {
				((SudokuButton) b).handleClick(e, buttons);
			}
			else{
				b.handleClick(e);
			}
		}
	}	
	
	private void createSudokuButtons(int numSquares) {
		this.board = new SudokuButton[SudokuPuzzle.SIZE][SudokuPuzzle.SIZE];
		float size = parent.height/(numSquares+1);
		float buffer = parent.height/(numSquares+1)/2;
		buttons = new ArrayList<Button>();
		for (int row = 0; row < SudokuPuzzle.SIZE; row++) {
			for (int col = 0; col < SudokuPuzzle.SIZE; col++) {
				String value = puzzle.getPuzzle()[row][col];
				if (value == "*") {
					board[row][col] = new SudokuButton(parent,col*size+buffer,row*size+buffer,size);
					
				} else {
					board[row][col] = new SudokuButton(parent,col*size+buffer,row*size+buffer,size,value);
				}
				buttons.add(board[row][col]);
			}
		}
	}
	private void displayButtons() {		
		for (Button b: buttons) {
			b.display();
		}
	}
	
	public ArrayList<Button> getButtons() {
		return buttons;
	}
}
