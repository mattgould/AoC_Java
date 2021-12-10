package com.mattgould.day06;

import java.math.BigInteger;

public class School {
	BigInteger[] fish;

	public School(int[] startingFish) {
		fish = new BigInteger[9];
		for (int i = 0; i < 9; i++) {
			fish[i] = BigInteger.ZERO;
		}
		for (int timeTilSpawn : startingFish) {
			fish[timeTilSpawn]=fish[timeTilSpawn].add(BigInteger.ONE);
		}
	}

	public void ageFish() {
		BigInteger spawning = fish[0];
		fish[0] = fish[1];
		fish[1] = fish[2];
		fish[2] = fish[3];
		fish[3] = fish[4];
		fish[4] = fish[5];
		fish[5] = fish[6];
		fish[6] = fish[7].add(spawning);
		fish[7] = fish[8];
		fish[8] = spawning;
	}
	public BigInteger countFish() {
		BigInteger count = BigInteger.ZERO;
		for (int i = 0; i < fish.length; i++) {
			 count = count.add(fish[i]);
		}
		return count;
	}

	public String printTimers() {
		StringBuilder sb = new StringBuilder();
		for (int i= 0; i < fish.length; i++) {
			sb.append(" ");
			sb.append(i);
			sb.append(": ");
			sb.append(fish[i]);
		}
		sb.append(" Total: ");
		sb.append(countFish());
		return sb.toString();
	}
}
