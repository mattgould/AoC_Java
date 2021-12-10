package com.mattgould.day07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class Day07 {

	private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

	public static Integer go(String file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			ArrayList<Integer> positions = new ArrayList<>();
			line = br.readLine();
			for (String number : line.split(",")) {
				positions.add(Integer.parseInt(number));
			}

			Integer max = Collections.max(positions);
			Integer min = Collections.min(positions);

			Integer median = getMedianFloor(positions);

			Integer bestPosition = 0;
			Integer bestCost = Integer.MAX_VALUE;
			for (Integer i = median; i <= median+1; i++) {
				Integer cost = getCost(positions, i);
				if (cost < bestCost) {
					bestCost = cost;
					bestPosition = i;
				}
			}

			System.out.printf("Best Position: %d  Best Cost: %d%n", bestPosition, bestCost);

			return bestPosition;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public static Integer go2(String file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			ArrayList<Integer> positions = new ArrayList<>();
			line = br.readLine();
			for (String number : line.split(",")) {
				positions.add(Integer.parseInt(number));
			}

			Integer sum = 0;
			for (Integer position : positions) {
				sum += position;
			}
			Integer mean = sum / positions.size();

			Integer bestPosition = 0;
			Integer bestCost = Integer.MAX_VALUE;
			for (Integer i = mean; i <= mean +1; i++) {
				Integer cost = getCost2(positions, i);
				if (cost < bestCost) {
					bestCost = cost;
					bestPosition = i;
				}
			}

			System.out.printf("Best Position: %d  Best Cost: %d%n", bestPosition, bestCost);

			return bestPosition;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return 0;
	}

	private static Integer getCost(List<Integer> positions, Integer end) {
		Integer cost = 0;
		for (Integer position: positions) {
			cost += Math.abs(position - end);
		}
		return cost;
	}

	public static Integer getCost2(List<Integer> positions, Integer endPosition) {
		Integer cost = 0;
		for (Integer position: positions) {
			cost += fuelCost(Math.abs(position - endPosition));
		}
		return cost;
	}

	public static Integer fuelCost(Integer distance) {
		return (distance * (1+distance))/2;
	}

	public static Integer getMedianFloor(List<Integer> list) {
		Collections.sort(list);
		return list.get(list.size()/2);
	}

}
