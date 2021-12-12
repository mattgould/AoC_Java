package com.mattgould.day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.logging.Logger;

public class Day11 {
	private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

	public static Integer go(String file, int steps) {
		int flashes = 0;
		int[][] grid = getGrid(file);

		for (int step = 0; step < steps; step++) {
			flashes += performStep(grid);
		}

		System.out.printf("Flashes: %d%n", flashes);
		return flashes;

	}

	public static Integer go2(String file) {
		int[][] grid = getGrid(file);

		int step = 1;
		while (performStep(grid) != 100) {
			 step++;
		}
		System.out.printf("All flashed: %d%n", step);
		return step;

	}


	private static int[][] getGrid(String file) {
		int[][]	grid = new int[10][10];

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			int row = 0;
			while ((line = br.readLine()) != null) {
				for (int column = 0; column < 10; column++) {
					grid[row][column] = Integer.parseInt(String.valueOf(line.charAt(column)));
				}
				row++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return grid;
	}

	public static int performStep(int[][] grid) {
		int flashes = 0;
		boolean[][] flashed = new boolean[10][10];
		for (int row = 0; row < 10; row++) {
			for (int column = 0; column < 10; column++) {
				grid[row][column]++;
			}
		}

		int newFlashes;
		do {
			newFlashes = 0;
			for (int row = 0; row < 10; row++) {
				for (int column = 0; column < 10; column++) {
					if (!flashed[row][column] && grid[row][column] > 9) {
						newFlashes++;
						flashed[row][column] = true;
						for (int deltaRow = -1; deltaRow <= 1; deltaRow++) {
							for (int deltaColumn = -1; deltaColumn <= 1; deltaColumn++) {
								try {
									grid[row + deltaRow][column + deltaColumn]++;
								} catch (IndexOutOfBoundsException e) {
									//ignore e
								}
							}
						}
					}
				}
			}
			flashes += newFlashes;
		} while (newFlashes > 0);
		for (int row = 0; row < 10; row++) {
			for (int column = 0; column < 10; column++) {
				if (flashed[row][column]) {
					grid[row][column] = 0;
				}
			}
		}
		return flashes;
	}

}

