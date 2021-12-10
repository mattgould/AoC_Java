package com.mattgould.day09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.logging.Logger;

public class Day09 {
	private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

	public static Integer go(String file) {
		Integer sum = 0;
		int[][] map = getMap(file);

		sum = findRisk(map);
		System.out.printf("Sum: %d%n", sum);
		return sum;

	}

	public static Integer go2(String file) {
		Integer product = 0;
		int[][] map = getMap(file);

		product = findLargestBasins(map, 3);
		System.out.printf("Product: %d%n", product);
		return product;

	}

	public static int findRisk(int[][] map) {
		int risk = 0;
		for (MapPoint point : findLowPoints(map)) {
			risk += map[point.y][point.x] + 1;
		}
		return risk;
	}


	private static int[][] getMap(String file) {
		int[][] map;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			ArrayList<String> lines = new ArrayList<>();
			int width = 0;
			String line;
			while ((line = br.readLine()) != null) {
				if (line.length() > width)
					width = line.length();
				lines.add(line);
			}
			map = new int[lines.size()][width];
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < lines.size(); y++) {
					map[y][x] = Integer.parseInt(String.valueOf(lines.get(y).charAt(x)));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			map = new int[1][1];
		}
		return map;
	}





	public static List<MapPoint> findLowPoints(int[][] map) {
		ArrayList<MapPoint> lowPoints = new ArrayList<>();
		int height = map.length;
		int width = map[0].length;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if ((y <= 0 || map[y][x] < map[y-1][x])
					&& (y > height-2 || map[y][x] < map[y+1][x])
					&& (x <= 0 || map[y][x] < map[y][x-1])
				  && (x > width - 2 || map[y][x] < map[y][x+1]))
							{
								lowPoints.add(new MapPoint(x, y));
							}
			}
		}
		return lowPoints;
	}

	public static int findLargestBasins(int[][] map, int numBasins) {
		int product = 0;
		List<MapPoint> lowPoints = findLowPoints(map);
		Integer[] basinSizes = new Integer[lowPoints.size()];
		for (int i = 0; i < lowPoints.size(); i++) {
			MapPoint point = lowPoints.get(i);
			HashMap<String, MapPoint> seen = new HashMap<>();
			seen.put(point.toString(), point);
			seen = getBasinPoints(map, point, seen);
			basinSizes[i] = seen.size();
		}
		logger.info("BasinSizes: " + basinSizes);
		Arrays.sort(basinSizes, Collections.reverseOrder());
		product = basinSizes[0] * basinSizes[1] * basinSizes[2];
		return product;
	}

	public static HashMap<String, MapPoint> getBasinPoints(int[][] map, MapPoint point, HashMap<String, MapPoint> seen) {
		MapPoint above;
		MapPoint below;
		MapPoint left;
		MapPoint right;
		int maxY = map.length - 1;
		int maxX = map[0].length - 1;

		if (point.y - 1 >= 0) {
			above = new MapPoint(point.x, point.y - 1);
			if (map[above.y][above.x] < 9 && !seen.containsKey(above.toString())) {
				seen.put(above.toString(), above);
				seen = getBasinPoints(map, above, seen);
			}
		}
		if (point.y + 1 <= maxY) {
			below = new MapPoint(point.x, point.y + 1);
			if (map[below.y][below.x] < 9 && !seen.containsKey(below.toString())) {
				seen.put(below.toString(), below);
				seen = getBasinPoints(map, below, seen);
			}
		}
		if (point.x - 1 >= 0) {
			left = new MapPoint(point.x - 1, point.y);
			if (map[left.y][left.x] < 9 && !seen.containsKey(left.toString())) {
				seen.put(left.toString(), left);
				seen = getBasinPoints(map, left, seen);
			}
		}
		if (point.x + 1 <= maxX) {
			right = new MapPoint(point.x + 1, point.y);
			if (map[right.y][right.x] < 9 && !seen.containsKey(right.toString())) {
				seen.put(right.toString(), right);
				seen = getBasinPoints(map, right, seen);
			}
		}

		return seen;
	}


}

