package com.mattgould.day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.math.BigInteger;
import java.util.logging.Logger;

public class Day14 {
	private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

	public static BigInteger go(String file, int steps) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			String template  = br.readLine();

			br.readLine(); // dump Blank line

			Polymer polymer = new Polymer(template);
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(" ");
				polymer.addRule(parts[0], parts[2]);
			}
			for (int step = 1; step <= steps; step++) {
				polymer.performStep();
			}

			BigInteger calc = polymer.calculate();
			logger.info("Calc: " + calc);

			return calc;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return BigInteger.ZERO;
	}

	public static BigInteger go2(String file, int steps) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			String template  = br.readLine();

			br.readLine(); // dump Blank line

			PolymerB polymer = new PolymerB(template);
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(" ");
				polymer.addRule(parts[0], parts[2]);
			}
			for (int step = 1; step <= steps; step++) {
				polymer.performStep();
			}

			BigInteger calc = polymer.calculate();
			logger.info("Calc: " + calc);

			return calc;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return BigInteger.ZERO;
	}




}
