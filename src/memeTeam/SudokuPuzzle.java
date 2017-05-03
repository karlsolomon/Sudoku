package memeTeam;

import java.io.*;

public class SudokuPuzzle {
	
	public static final int SIZE = 9;
	public static final int SIZESQRT = 3;
	String[][] puzzle; 
	String[][] finishedPuzzle;
	
	public SudokuPuzzle(int difficulty) {
		puzzle = new String[SIZE][SIZE];
		finishedPuzzle = new String[SIZE][SIZE];
		qqwing.QQWing.generateSudokuPuzzle(difficulty);
		fileReader();	
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				System.out.print(finishedPuzzle[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	private void fileReader() {
		String file = "puzzle.txt"; 
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader); 
			String board = bufferedReader.readLine().trim();
			createPuzzle(puzzle,board);
			String solution = bufferedReader.readLine().trim();
			createPuzzle(finishedPuzzle,solution);
			
			bufferedReader.close();
			
		} catch(FileNotFoundException e) {
			System.out.println("Unable to open file");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createPuzzle(String[][] puzzle, String line) {
		String[] characterArray = line.split(""); 
		for (int elt = 0; elt < characterArray.length; elt++) {
			int row = elt / SIZE; 
			int column = elt % SIZE;
			if (characterArray[elt].charAt(0) == 46) {
				puzzle[row][column] = "*";
			} else {
				puzzle[row][column] = characterArray[elt];
			}
		}
	}
	public String[][] getPuzzle() {
		return puzzle;
	}
	
	public String[][] getFinishedPuzzle() {
		return finishedPuzzle;
	}
	
	public boolean puzzleIsCorrect(String[][] playerPuzzle){
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if(playerPuzzle[i][j] != finishedPuzzle[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean rowIsCorrect(int row, String[][] playerPuzzle) {
		for(int j = 0; j < SIZE; j++) {
			if(!finishedPuzzle[row][j].equals(playerPuzzle[row][j])) {
				return false;
			}
		}
		return true;
	}
	
	public boolean columnIsCorrect(int column, String[][] playerPuzzle) {
		for(int i = 0; i < SIZE; i++) {
			if(!playerPuzzle[i][column].equals(finishedPuzzle[i][column])) {
				return false;
			}
		}
		return true;
	}
	
	public boolean regionIsCorrect(int row, int column, String[][] playerPuzzle) {
		row -= (row%SIZESQRT);
		column -= (column%SIZESQRT);
		for(int i = row; i < row+SIZESQRT; i++) {
			for(int j = column; j < column+SIZESQRT; j++) {
				if(!playerPuzzle[i][j].equals(finishedPuzzle[i][j])) {
					return false;
				}
			}
		}
		return true;
	}
}
