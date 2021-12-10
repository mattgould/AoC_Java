package com.mattgould.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.logging.Logger;

public class Day10 {
	private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

	public static Integer go(String file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			Integer score = 0;
			String line;
			while ((line = br.readLine()) != null) {
				Integer lineScore = scoreLine(line);
				score += lineScore;
			}
			System.out.printf("Score: %d%n", score);

			return score;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;

	}
	public static Long go2(String file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			ArrayList<Long> scores = new ArrayList<>();
			Long score = 0L;
			String line;
			while ((line = br.readLine()) != null) {
				Long lineScore = scoreLine2(line);
				if (lineScore > 0) {
					scores.add(lineScore);
				}
			}

			score = getMedian(scores);
			System.out.printf("Score: %d%n", score);

			return score;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0L;

	}

	private static Long scoreLine2(String line) {
		Stack<Character> stack = new Stack<>();
		for (Character c :line.toCharArray()) {
			switch (c) {
				case '(':
				case '[':
				case '{':
				case '<':
					stack.push(c);
					break;
				case ')':
					if (stack.empty() || '(' != stack.peek()) {
						return 0L;
					} else {
						stack.pop();
					}
					break;
				case ']':
					if (stack.empty() || '[' != stack.peek()) {
						return 0L;
					} else {
						stack.pop();
					}
					break;
				case '}':
					if (stack.empty() || '{' != stack.peek()) {
						return 0L;
					} else {
						stack.pop();
					}
					break;
				case '>':
					if (stack.empty() || '<' != stack.peek()) {
						return 0L;
					} else {
						stack.pop();
					}
					break;
			}
		}

		Long score = 0L;
		while (!stack.empty()) {
			score *= 5L;
			switch (stack.pop()) {
				case '(':
					score += 1L;
					break;
				case '[':
					score += 2L;
					break;
				case '{':
					score += 3L;
					break;
				case '<':
					score += 4L;
					break;
			}
		}
		return score;
	}

	private static Integer scoreLine(String line) {
		Stack<Character> stack = new Stack<>();
		for (Character c :line.toCharArray()) {
			switch (c) {
				case '(':
				case '[':
				case '{':
				case '<':
					stack.push(c);
					break;
				case ')':
					if (stack.empty() || '(' != stack.peek()) {
						return 3;
					} else {
						stack.pop();
					}
					break;
				case ']':
					if (stack.empty() || '[' != stack.peek()) {
						return 57;
					} else {
						stack.pop();
					}
					break;
				case '}':
					if (stack.empty() || '{' != stack.peek()) {
						return 1197;
					} else {
						stack.pop();
					}
					break;
				case '>':
					if (stack.empty() || '<' != stack.peek()) {
						return 25137;
					} else {
						stack.pop();
					}
					break;
			}
		}
		return 0;
	}

	public static Long getMedian(ArrayList<Long> scores) {
		Long[] sorted = scores.toArray(new Long[0]);
		Arrays.sort(sorted);
		int mid = sorted.length/2;
		return sorted[mid];
	}

}

