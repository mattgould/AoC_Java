package com.mattgould.day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Logger;

public class Day13 {
	private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

	public static int go(String file, int maxFolds) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			Paper paper = new Paper();
			ArrayList<Point> dots = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				if (line.equals("")) {
					break;
				}
				paper.addPoint(new Point(line));
			}
			//System.out.println(paper);

			int foldNumber = 0;
			while ((line = br.readLine()) != null) {
				foldNumber++;
				if (maxFolds > 0 && foldNumber > maxFolds) break;
				String[] parts = line.split(" ");
				String[] foldParts = parts[2].split("=");
				int foldValue = Integer.parseInt(foldParts[1]);
				if (foldParts[0].equals("x")) {
					paper.foldAlongX(foldValue);
				} else {
					paper.foldAlongY(foldValue);
				}
				//System.out.println(paper);
				System.out.printf("Fold: %d, Count: %d%n", foldNumber, paper.getNumDots());
			}
			System.out.println(paper);
			int numPoints = paper.getNumDots();
			logger.info("Count: " + numPoints);
			return numPoints;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}


}
