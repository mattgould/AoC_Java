package com.mattgould.day16;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

public class Day16 {
	private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

	public static Integer go(String packetHex) {
		Integer sum = 0;
		Packet p = new Packet(hexToBits(packetHex));
		sum = p.getVersionSum();
		System.out.printf("Version Sum: %d%n", sum);

		return sum;

	}
	public static Long go2(String packetHex) {
		long value;
		Packet p = new Packet(hexToBits(packetHex));
		value = p.value;

		System.out.printf("Value: %d%n", p.value);

		return value;

	}

	public static String hexToBits(String hex) {
		StringBuilder bits = new StringBuilder();
		for (Character c : hex.toCharArray()) {
			int val;
			if (c >= 'A') {
				val = c - 'A' + 10;
			} else {
				val = c - '0';
			}
			bits.append(String.format( "%4s", Integer.toBinaryString(val)));
		}
		return bits.toString().replace(' ', '0');
	}

}


