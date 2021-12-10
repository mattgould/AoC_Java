package com.mattgould.day04;

import java.util.ArrayList;

public class BingoBoard {
	ArrayList<Square> squares = new ArrayList<>();
	boolean alreadyWon;

	public BingoBoard(int[][] numbers) {
		int row = 0;
		for (int[] rowValues: numbers) {
			int column = 0;
			for (int value: rowValues) {
				squares.add(new Square(row, column, value));
				column++;
			}
			row++;
		}
		alreadyWon = false;
	}

	public boolean markAndCheckWin(int value) {
		boolean marked = false;
		for (Square square: this.squares) {
			if (square.mark(value)) {
				marked = true;
				break;
			}
		}
		if (marked) {
			alreadyWon = checkWin();
			return alreadyWon;
		}
		return false;
	}

	private boolean checkWin() {
		int[] rowMarks = new int[5];
		int[] columnMarks = new int[5];

		for (Square square : squares) {
			if (square.marked) {
				if (++rowMarks[square.row] >=5) return true;
				if (++columnMarks[square.column] >= 5) return true;
			}
		}
		return false;
	}

	public int score(int value) {
		int sum = 0;
		for (Square square : squares) {
			if (!square.marked) {
				sum += square.value;
			}
		}
		return sum * value;
	}

}
