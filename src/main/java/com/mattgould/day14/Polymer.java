package com.mattgould.day14;

import java.math.BigInteger;
import java.util.HashMap;

public class Polymer {
	String template;
	HashMap<String, String> rules = new HashMap<>();

	public Polymer(String template) {
		this.template = template;
	}
	public void addRule(String pair, String insert) {
		rules.put(pair, insert);
	}

	public void performStep() {
		for (int pos = template.length() - 2; pos >= 0; pos--) {
			String replace = template.substring(pos, pos + 2);
			String insert = rules.get(replace);
			template = addChar(template, insert, pos + 1);
		}
	}

	public static String addChar(String str, String ch, int position) {
		StringBuilder sb = new StringBuilder(str);
		sb.insert(position, ch);
		return sb.toString();
	}

	public BigInteger calculate() {
		HashMap<Character, BigInteger> charCount = new HashMap<>();
		for (char c : template.toCharArray()) {
			BigInteger count = charCount.getOrDefault(c, BigInteger.ZERO).add(BigInteger.ONE);
			charCount.put(c, count);
		}

		BigInteger maxValue = BigInteger.ZERO;
		BigInteger minValue = new BigInteger("999999999999999999999999999999999999999999999");
		for (char c : charCount.keySet()) {
			BigInteger count = charCount.get(c);
			if (count.compareTo(maxValue) > 0) {
				maxValue = count;
			}
			if (count.compareTo(minValue) < 0) {
				minValue = count;
			}
		}
		return maxValue.subtract(minValue);
	}

}
