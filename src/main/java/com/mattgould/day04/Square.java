package com.mattgould.day04;

class Square {
	int row;
	int column;
	int value;
	boolean marked;
	public Square(int row, int column, int value) {
		this.row = row;
		this.column = column;
		this.value = value;
		this.marked = false;
	}
	public boolean mark(int value){
		if (value == this.value) {
			this.marked = true;
			return true;
		}
		return false;
	}
}
