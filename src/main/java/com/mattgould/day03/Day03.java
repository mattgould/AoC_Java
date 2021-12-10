package com.mattgould.day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class Day03 {
	private Day03() {
	}

	private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

	public static void go(String file) {
		submarineConsumption(file);
		lifeSupportRating(file);
	}

	public static Integer submarineConsumption(String file) {

		int consumption = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			int lineCount = 0;
			HashMap<Integer, Integer> counts = new HashMap<>();
			while ((line = br.readLine()) != null) {
				lineCount++;
				int len = line.length();
				for(int i = 0; i < len; i++) {
					if (line.charAt(i) == '1') {
						int power = len - i - 1;
						int position = (int) Math.pow(2, power);
						if (counts.containsKey(position)) {
							counts.put(position, counts.get(position) + 1);
						} else {
							counts.put(position, 1);
						}
					}
				}
			}
			int half = lineCount / 2;
			int gamma = 0;
			int epsilon = 0;

			for (var entry : counts.entrySet()) {
				if (entry.getValue() > half) {
					gamma += entry.getKey();
				} else {
					epsilon += entry.getKey();
				}
			}


			consumption = gamma * epsilon;
			System.out.println("Gamma: " + gamma + "(" + Integer.toBinaryString(gamma) + ")");
			System.out.println("Epsilon: " + epsilon + "(" + Integer.toBinaryString(epsilon) + ")");
			System.out.println("Consumption: " + consumption);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return consumption;
	}

	public static int lifeSupportRating(String dateFile) {
		ArrayList<String> initialBucket = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(dateFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				initialBucket.add(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		String oxygenGeneratorRatingData = dataParser(initialBucket, 0, true);
		int oxygenGeneratorRating = Integer.parseInt(oxygenGeneratorRatingData, 2);
		logger.info("Oxygen Generator Reading: " + oxygenGeneratorRating + "(" + oxygenGeneratorRatingData + ")");

		String co2ScrubberRatingData = dataParser(initialBucket, 0, false);
		int co2ScrubberRating = Integer.parseInt(co2ScrubberRatingData, 2);
		logger.info("CO2 Scrubber Reading: " + co2ScrubberRating + "(" + co2ScrubberRatingData + ")");

		System.out.println("Life Support Rating: " + oxygenGeneratorRating * co2ScrubberRating);
		return oxygenGeneratorRating * co2ScrubberRating;
	}

	public static String dataParser(ArrayList<String> bucket, int position, boolean greater) {
		ArrayList<String> bucketZero = new ArrayList<>();
		ArrayList<String> bucketOne = new ArrayList<>();
		if (bucket.size() == 1) {
			return bucket.get(0);
		}
		for (String reading : bucket) {
			if (reading.charAt(position) == '1') {
				bucketOne.add(reading);
			} else {
				bucketZero.add(reading);
			}
		}
		if (greater == (bucketOne.size() >= bucketZero.size())) {
			return dataParser(bucketOne, position + 1, greater);
		} else {
			return dataParser(bucketZero, position + 1, greater);
		}

	}

}
