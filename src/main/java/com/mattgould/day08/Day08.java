package com.mattgould.day08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Logger;

public class Day08 {
	private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

	public static Integer go(String file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			Integer count = 0;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(" \\| " );
				String[] outputDigits = parts[1].trim().split(" ");
				for (String digit : outputDigits) {
					Integer length = digit.length();
					switch (length) {
						case 2: //one
						case 3: //seven
						case 4: //four
						case 7: //eight
							count++;
							break;
						default:
							break;
					}
				}
			}
			System.out.printf("Count: %d%n", count);
			return count;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;

	}

	public static Integer go2(String file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			Integer sum = 0;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(" \\| ");
				HashMap<String, Integer> digitMap = determineDigits(parts[0]);
				String[] outputDigits = parts[1].trim().split(" ");
				int number = 0;
				for (String digit : outputDigits) {
					digit = alphabetize(digit);
					number *= 10;
					number += digitMap.get(digit);
				}
				sum += number;
			}

			System.out.printf("Sum: %d%n", sum);
			return sum;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;

	}

	public static HashMap<String, Integer> determineDigits(String allDigits) {
		HashMap<String, Integer> digitMap = new HashMap<>();
		String[] digitArray = new String[10];
		String[] digits = allDigits.trim().split(" ");
		ArrayList<String> sixWireNumbers = new ArrayList<>();
		ArrayList<String> fiveWireNumbers = new ArrayList<>();
		for (String digit : digits) {
			digit = alphabetize(digit);
			int length = digit.length();
			switch (length) {
				case 2: //one
					digitArray[1] = digit;
					break;
				case 3: //seven
					digitArray[7] = digit;
					break;
				case 4: //four
					digitArray[4] = digit;
					break;
				case 7: //eight
					digitArray[8] = digit;
					break;
				case 6: // zero, six, nine
					sixWireNumbers.add(digit);
					break;
				case 5: // two, three, five
					fiveWireNumbers.add(digit);
					break;
				default:
					throw new RuntimeException("Invalid Digit");
			}
		}
		for (String sixWire : sixWireNumbers) {
			if (digitArray[9] == null && containsAllBut(sixWire, digitArray[4], 0)) {
				digitArray[9] = sixWire;
			} else if (digitArray[0] == null && containsAllBut(sixWire, digitArray[1], 0)) {
				digitArray[0] = sixWire;
			} else {
				digitArray[6] = sixWire;
			}
		}
		for (String fiveWire : fiveWireNumbers) {
			if (digitArray[3] == null && containsAllBut(fiveWire, digitArray[1], 0)) {
				digitArray[3] = fiveWire;
			} else if (digitArray[5] == null && containsAllBut(fiveWire, digitArray[4], 1)) {
				digitArray[5] = fiveWire;
			} else {
				digitArray[2] = fiveWire;
			}
		}

		for (int i = 0; i <= 9; i++) {
			digitMap.put(digitArray[i], i);
		}

		return digitMap;
	}

	public static String alphabetize(String in) {
		char[] tempArray = in.toCharArray();
		Arrays.sort(tempArray);
		return new String(tempArray);
	}

	public static boolean containsAllBut(String digit, String mustContain, int maxMissing) {
		int misses = 0;
		for (char c : mustContain.toCharArray()) {
			if (!digit.contains(String.valueOf(c)) && ++misses > maxMissing) {
				return false;
			}
		}
		return true;
	}

}
