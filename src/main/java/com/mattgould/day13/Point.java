package com.mattgould.day13;

import java.util.ArrayList;
import java.util.HashMap;

public class Point {
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Point(String point) {
		String[] parts = point.split(",");
		x = Integer.parseInt(parts[0]);
		y = Integer.parseInt(parts[1]);
	}

	public void horizontalFold(int foldLine) {
		if (y > foldLine) {
			y = y - (y - foldLine) * 2;
		}
	}
	public void verticalFold(int foldLine) {
		if (x > foldLine) {
			x = x - (x - foldLine) * 2;
		}
	}

	@Override public String toString() {
		return x + "," + y;
	}
}
