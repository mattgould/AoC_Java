package com.mattgould.day05;

import java.util.ArrayList;
import java.util.List;

public class VentLine {
	Point start;
	Point end;
	public VentLine(String ventDefinition) {
		String[] parts = ventDefinition.split(" ");
		String[] startPart = parts[0].split(",");
		String[] endPart = parts[2].split(",");
		start = new Point(Integer.parseInt(startPart[0]), Integer.parseInt(startPart[1]));
		end = new Point(Integer.parseInt(endPart[0]), Integer.parseInt(endPart[1]));
	}

	public List<Point> getPoints(boolean diagonals) {
		ArrayList<Point> points = new ArrayList<>();
		if (start.x == end.x) {
			if (start.y < end.y) {
				for (int i = start.y; i <= end.y; i++) {
					points.add(new Point(start.x, i));
				}
			} else {
				for (int i = end.y; i <= start.y; i++) {
					points.add(new Point(start.x, i));
				}
			}
		} else if (start.y == end.y) {
			if (start.x < end.x) {
				for (int i = start.x; i <= end.x; i++) {
					points.add(new Point(i, start.y));
				}
			} else {
				for (int i = end.x; i <= start.x; i++) {
					points.add(new Point(i, start.y));
				}
			}
		} else if(diagonals){
			if (start.x > end.x) {
				if (start.y > end.y) {
					for (int i = 0; i <= start.x - end.x; i++) {
						points.add(new Point(start.x - i, start.y - i));
					}
				} else {
					for (int i = 0; i <= start.x - end.x; i++) {
						points.add(new Point(start.x - i, start.y + i));
					}
				}
			} else {
				if (start.y > end.y) {
					for (int i = 0; i <= end.x - start.x; i++) {
						points.add(new Point(start.x + i, start.y - i));
					}
				} else {
					for (int i = 0; i <= end.x - start.x; i++) {
						points.add(new Point(start.x + i, start.y + i));
					}
				}

			}
		}
		return points;
	}

	public List<Point> newGetPoints() {
		ArrayList<Point> points = new ArrayList<>();
		int xDirection = Integer.compare(end.x, start.x);
		int yDirection = Integer.compare(end.y, start.y);
		int length = Math.abs((start.y == end.y) ? start.x - end.x : start.y - end.y);
		for (int i = 0; i <= length; i++) {
			points.add(new Point(start.x + i * xDirection, start.y + i * yDirection));
		}
		return points;
	}

	}
