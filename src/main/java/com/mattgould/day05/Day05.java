package com.mattgould.day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Day05 {
	private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

	public static int go(String file, boolean diagonals) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			int maxX = 0;
			int maxY = 0;
			ArrayList<VentLine> ventLines = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				VentLine ventLine = new VentLine(line);
				if (ventLine.start.x > maxX) maxX = ventLine.start.x;
				if (ventLine.end.x > maxX) maxX = ventLine.end.x;
				if (ventLine.start.y > maxY) maxY = ventLine.start.y;
				if (ventLine.end.y > maxY) maxY = ventLine.end.y;
				ventLines.add(ventLine);
			}
			int[][] map = new int[maxX+1][maxY+1];
			for (VentLine ventLine: ventLines) {
				for (Point point: ventLine.getPoints(diagonals)) {
					map[point.y][point.x]++;
				}
			}
//			printMap(map);
			int count = 0;
			for (int x = 0; x <= maxX; x++) {
				for (int y = 0; y <= maxY; y++) {
					if (map[x][y] >= 2) count++;
				}
			}
			logger.info("Count: " + count);
			return count;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;

	}

	public static int go2(String file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			int maxX = 0;
			int maxY = 0;
			ArrayList<VentLine> ventLines = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				VentLine ventLine = new VentLine(line);
				if (ventLine.start.x > maxX) maxX = ventLine.start.x;
				if (ventLine.end.x > maxX) maxX = ventLine.end.x;
				if (ventLine.start.y > maxY) maxY = ventLine.start.y;
				if (ventLine.end.y > maxY) maxY = ventLine.end.y;
				ventLines.add(ventLine);
			}
			int[][] map = new int[maxX+1][maxY+1];
			for (VentLine ventLine: ventLines) {
				for (Point point: ventLine.newGetPoints()) {
					map[point.y][point.x]++;
				}
			}
//			printMap(map);
			int count = 0;
			for (int x = 0; x <= maxX; x++) {
				for (int y = 0; y <= maxY; y++) {
					if (map[x][y] >= 2) count++;
				}
			}
			logger.info("Count: " + count);
			return count;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;

	}

	private static void printMap(int[][] map) {
		StringBuilder sb = new StringBuilder();
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[0].length; y++) {
				if (map[x][y] > 0) sb.append(map[x][y]);
				else sb.append(".");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}
