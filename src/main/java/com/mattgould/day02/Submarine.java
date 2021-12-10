package com.mattgould.day02;

public class Submarine {
	private Integer horizontalPosition;
	private Integer depth;
	private Integer aim;

	public Submarine(Integer horizontalPosition, Integer depth, Integer aim) {
		this.horizontalPosition = horizontalPosition;
		this.depth = depth;
		this.aim = aim;
	}

	public void forward(Integer distance) {
		horizontalPosition += distance;
		depth += distance * aim;
	}

	public void down(Integer units) {
		aim += units;
	}
	public void up(Integer units) {
		aim -= units;
	}

	public Integer getHorizontalPosition() {
		return horizontalPosition;
	}

	public Integer getDepth() {
		return depth;
	}

	public Integer getProduct() {
		return depth * horizontalPosition;
	}

	@Override public String toString() {
		return "Submarine{" +
			"horizontalPosition=" + getHorizontalPosition() +
			", depth=" + getDepth() +
			", product=" + getProduct() +
			'}';
	}
}
