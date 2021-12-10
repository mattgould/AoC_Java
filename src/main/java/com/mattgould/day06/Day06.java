package com.mattgould.day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class Day06 {

	private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

	public static int go(String file, int days) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			ArrayList<LanternFish> lanternFish = new ArrayList<>();
			line = br.readLine();
			String[] strings = line.split(",");
			for (String fishAge : strings) {
				lanternFish.add(new LanternFish(Integer.parseInt(fishAge)));
			}

//			printAges(lanternFish);
			for (int d = 0; d < days; d++) {
				int newFish = 0;
				for (LanternFish fish: lanternFish) {
					if (fish.ageFish()) {
						newFish++;
					}
				}
				for (int f = 0; f < newFish; f++) {
					lanternFish.add(new LanternFish());
				}
//				printAges(lanternFish);
			}
			System.out.printf("Number of Fish after %d days: %d%n", days, lanternFish.size());
			return lanternFish.size();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	private static void printAges(List<LanternFish> fishes) {
		StringBuilder sb = new StringBuilder();
		sb.append("Fish: ");
		for (LanternFish fish : fishes) {
			sb.append(fish.timer);
			sb.append(",");
		}
		System.out.println(sb.substring(0, sb.length() - 1));
	}

	public static BigInteger go2(String file, List<Integer> days) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			int maxDays = 0;
			for (int day : days) {
				if (day > maxDays)
					maxDays = day;
			}
			String line = br.readLine();
			String[] strings = line.split(",");
			int[] initialFish = new int[strings.length];
			for (int i = 0; i < initialFish.length; i++) {
				initialFish[i] = Integer.parseInt(strings[i]);
			}
			School school = new School(initialFish);
			var lastcount = BigInteger.ZERO;
			System.out.println("Initial Fish:" + school.printTimers());
						for (int d = 0; d < maxDays; d++) {
				school.ageFish();
							var thisCount = school.countFish();
							if (thisCount.compareTo(lastcount) < 0) {
								System.out.printf("Count did not increase on Day %d %n("
									+ "\tLast:%s%n\tThis:%s%n)%n", d+1, lastcount, thisCount);
							}
							lastcount = thisCount;
				if (days.contains(d +1)) {
					//System.out.printf("Day %3d: %s%n", d + 1, school.printTimers());
					System.out.printf("Day %d Total: %s%n", d+1, lastcount.toString());
				}
			}
			return school.countFish();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return BigInteger.ZERO;
	}

}
