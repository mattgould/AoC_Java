package com.mattgould.day15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Day15 {
	private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

	public static int go(String file, int multiplier) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			Set<Square> squares = new HashSet<>();
			Map<String, Set<String>> connections = new HashMap<>();
			ArrayList<String> lines = new ArrayList<>();
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			int numDataColumns = lines.get(0).length();
			int numDataRows = lines.size();

			int numColumns = numDataColumns * multiplier;
			int numRows = numDataRows * multiplier;

			for (int row = 0; row < numRows; row++) {
				int dataRow = row % numDataRows;
				int rowRepeat = row / numDataRows;
				for (int column = 0; column < numColumns; column++) {
					int dataColumn = column % numDataColumns;
					int columnRepeat = column / numDataColumns;
					int risk = lines.get(dataRow).charAt(dataColumn) - '0';
					risk += columnRepeat + rowRepeat;
					if (risk > 9) risk -= 9;

					squares.add(new Square(column + "," + row, risk));
				}
			}


			for (int r = 0; r < numRows; r++) {
				for (int c = 0; c < numColumns; c++) {
					connections.put(c + "," + r, buildConnections(r, c, numRows-1, numColumns-1));
				}
			}

			int risk = 0;
			Graph<Square> cavern = new Graph<>(squares, connections);
			RouteFinder<Square> routeFinder = new RouteFinder<>(cavern, new RiskScorer(), new RiskScorer());
			List<Square> route = routeFinder.findRoute(cavern.getNode("0,0"),
				cavern.getNode((numColumns - 1)	+ ","	+ (numRows - 1)));
			System.out.println(route.stream().map(Square::getId).collect(Collectors.toList()));


			for (int s = 1; s < route.size(); s++) {
				risk += route.get(s).getRisk();
			}

			logger.info("Risk: " + risk);
			return risk;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static Set<String> buildConnections(int r, int c, int maxR, int maxC) {

		Set<String> nodeConnections = new HashSet<>();
		if (c > 0) { // left
			nodeConnections.add((c - 1)	+ ","	+ r);
		}

		if (c < maxC) { // right
			nodeConnections.add((c + 1)	+ ","	+ r);
		}

		if (r > 0) { // up
			nodeConnections.add(c	+ "," + (r - 1));
		}

		if (r < maxR) { // down
			nodeConnections.add(c	+ "," + (r + 1));
		}
		return nodeConnections;

	}


}

