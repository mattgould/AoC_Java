package com.mattgould.day13;

import java.util.ArrayList;
import java.util.HashMap;

public class Paper {
	ArrayList<Point> dots = new ArrayList<>();
	int maxX = 0;
	int maxY = 0;

	public void addPoint(Point point) {
		if (point.x > maxX) {
			maxX = point.x;
		}
		if (point.y > maxY) {
			maxY = point.y;
		}
		dots.add(point);
	}

	public void foldAlongY(int foldLine) {
		for (Point p : dots) {
			p.horizontalFold(foldLine);
		}
		deDuplicatePoints();
		maxY = foldLine - 1;
	}

	public void foldAlongX(int foldLine) {
		for (Point p : dots) {
			p.verticalFold(foldLine);
		}
		deDuplicatePoints();
		maxX = foldLine - 1;
	}

	public void deDuplicatePoints() {
		HashMap<String, Point> pointMap = new HashMap<>();
		for (Point p : dots) {
			pointMap.put(p.toString(), p);
		}
		dots = new ArrayList<>(pointMap.values());
	}

	public int getNumDots() {
		return dots.size();
	}

	@Override public String toString() {
		boolean[][] out = new boolean[maxY + 1][maxX + 1];
		for (Point dot : dots) {
			out[dot.y][dot.x] = true;
		}
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < maxY + 1; y++) {
			for (int x = 0; x < maxX + 1; x++) {
				if (out[y][x]) {
					sb.append("0");
				} else {
					sb.append(" ");
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
