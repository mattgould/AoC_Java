package com.mattgould.day14;

import java.math.BigInteger;
import java.util.HashMap;

public class PolymerB {
	String template;
	HashMap<String, String> rules = new HashMap<>();

	HashMap<String, BigInteger> pairCount = new HashMap<>();

	boolean finalized = false;

	public PolymerB(String template) {
		this.template = template;
	}
	public void addRule(String pair, String insert) {
		rules.put(pair, insert);
		finalized = false;
	}

	public void performStep() {
		if (!finalized) finalizeRules();
		HashMap<String, BigInteger> add = new HashMap<>();
		HashMap<String, BigInteger> subtract = new HashMap<>();
		for (String pair : rules.keySet()) {

			BigInteger numberOfPairs = pairCount.getOrDefault(pair, BigInteger.ZERO);
			if (numberOfPairs.compareTo(BigInteger.ZERO) > 0) {
				String insert = rules.get(pair);
				String newPair1 = pair.substring(0,1) + insert;
				String newPair2 = insert + pair.substring(1,2);
				BigInteger alreadyAddedNewPair1 = add.getOrDefault(newPair1, BigInteger.ZERO);
				BigInteger alreadyAddedNewPair2 = add.getOrDefault(newPair2, BigInteger.ZERO);

				BigInteger newAddedNewPair1 = alreadyAddedNewPair1.add(numberOfPairs);
				BigInteger newAddedNewPair2 = alreadyAddedNewPair2.add(numberOfPairs);

				BigInteger alreadySubtractedPair = subtract.getOrDefault(pair, BigInteger.ZERO);

				BigInteger newSubtractedPair = alreadySubtractedPair.add(numberOfPairs);


				add.put(newPair1, newAddedNewPair1);
				add.put(newPair2, newAddedNewPair2);
				subtract.put(pair, newSubtractedPair);

			}

		}
		for (String pair : rules.keySet()) {
			BigInteger currentCount = pairCount.getOrDefault(pair, BigInteger.ZERO);
			currentCount = currentCount.add(add.getOrDefault(pair, BigInteger.ZERO));
			currentCount = currentCount.subtract(subtract.getOrDefault(pair, BigInteger.ZERO));
			pairCount.put(pair, currentCount);
		}

	}

	public void finalizeRules() {
		pairCount.clear();
		for ( String pair : rules.keySet()) {
			pairCount.put(pair, BigInteger.ZERO);
		}
		for (int pos = template.length() - 2; pos >= 0; pos--) {
			String replace = template.substring(pos, pos + 2);
			BigInteger count = pairCount.getOrDefault(replace, BigInteger.ZERO);
			pairCount.put(replace, count.add(BigInteger.ONE));
		}
		finalized = true;
	}

	public BigInteger calculate() {
		HashMap<Character, BigInteger> charCount = new HashMap<>();
		for (String pair : pairCount.keySet()) {
			BigInteger countOfPair = pairCount.get(pair);
			for (Character c : pair.toCharArray()) {
				BigInteger count = charCount.getOrDefault(c, BigInteger.ZERO).add(countOfPair);
				charCount.put(c, count);
			}
		}


		char[] v = template.toCharArray();
		Character first = v[0];
		Character last = v[v.length-1];
		BigInteger maxValue = BigInteger.ZERO;
		BigInteger minValue = new BigInteger("999999999999999999999999999999999999999999999");
		for (char c : charCount.keySet()) {
			BigInteger count = charCount.get(c);
			count = count.divide(BigInteger.TWO);
			if (first.equals(c)) {
				count = count.add(BigInteger.ONE);
			}
			if (last.equals(c)) {
				count = count.add(BigInteger.ONE);
			}
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
