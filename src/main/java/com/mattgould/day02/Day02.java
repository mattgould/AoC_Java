package com.mattgould.day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

public class Day02 {
	private Day02() {
	}

	private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

	public static Submarine go(String file) {

		Submarine submarine = new Submarine(0, 0, 0);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				String [] tokens = line.split(" ");
				String direction = tokens[0];
				Integer units = Integer.parseInt(tokens[1]);
				switch (direction) {
					case "forward":
						submarine.forward(units);
						break;
					case "down":
						submarine.down(units);
						break;
					case "up":
						submarine.up(units);
						break;
					default:
						logger.severe(direction + " is not a valid direction");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info(submarine.toString());

		return submarine;
	}
}
