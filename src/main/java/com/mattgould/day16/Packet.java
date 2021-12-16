package com.mattgould.day16;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Packet {
	int version;
	int type;
	long value;
	int lengthType;
	int length;
	int consumedBits;
	List<Packet> subPackets;

	public Packet(String bits) {
		version = Integer.parseInt(bits.substring(0, 3), 2);
		type = Integer.parseInt(bits.substring(3, 6), 2);
		consumedBits = 6;
		if (type == 4) {
			parseValue(bits.substring(6));
		} else {
			subPackets = new ArrayList<>();
			lengthType = Integer.parseInt(bits.substring(6, 7), 2);
			consumedBits += 1;
			if (lengthType == 0) {
				length = Integer.parseInt(bits.substring(7, 22), 2);
				consumedBits += 15;
				parseType0SubPackets(bits.substring(22, 22+length));
			} else {
				length = Integer.parseInt(bits.substring(7, 18), 2);
				consumedBits += 11;
				parseType1SubPackets(bits.substring(18), length);
			}
		}
		calculate();
	}

	private void parseValue(String bits) {
		int numStart = 0;
		String continueBit;
		StringBuilder numberBits = new StringBuilder();
		do {
			 continueBit = bits.substring(numStart, numStart + 1);
			 numberBits.append(bits.substring(numStart + 1, numStart + 5));
			numStart +=5;
		} while (continueBit.equals("1"));
		value = Long.parseLong(numberBits.toString(), 2);
		consumedBits += numStart;
	}

	private void parseType0SubPackets(String bits) {
		while (!bits.equals("")) {
			Packet subPacket = new Packet(bits);
			subPackets.add(subPacket);
			bits = bits.substring(subPacket.consumedBits);
			consumedBits += subPacket.consumedBits;
		}
	}

	private void parseType1SubPackets(String bits, int numPackets) {
		for (int i = 0; i < numPackets; i++) {
			Packet subPacket = new Packet(bits);
			subPackets.add(subPacket);
			bits = bits.substring(subPacket.consumedBits);
			consumedBits += subPacket.consumedBits;
		}
	}

	public int getVersionSum() {
		int sum = version;
		if (subPackets != null) {
			for (Packet p : subPackets) {
				sum += p.getVersionSum();
			}
		}
		return sum;
	}

	private void calculate() {
		switch (type) {
			case 0: // sum
				value = sum();
				break;
			case 1: // product
				value = product();
				break;
			case 2: // min
				value = min();
				break;
			case 3: //max
				value = max();
				break;
			case 5: // >
				value = greaterThan();
				break;
			case 6: // <
				value = lessThan();
				break;
			case 7: // ==
				value = equalTo();
				break;
		}
	}

	private long sum() {
		long sum = 0;
		for (Packet p : subPackets) {
			sum += p.value;
		}
		return sum;
	}
	private long product() {
		long product = 1;
		for (Packet p : subPackets) {
			product *= p.value;
		}
		return product;
	}

	private long min() {
		long min = Long.MAX_VALUE;
		for (Packet p : subPackets) {
			if (p.value < min) min = p.value;
		}
		return min;
	}

	private long max() {
		long max = 0L;
		for (Packet p : subPackets) {
			if (p.value > max) max = p.value;
		}
		return max;
	}

	private long greaterThan() {
		if (subPackets.get(0).value > subPackets.get(1).value) {
			return 1L;
		} else {
			return 0L;
		}
	}

	private long lessThan() {
		if (subPackets.get(0).value < subPackets.get(1).value) {
			return 1L;
		} else {
			return 0L;
		}
	}

	private long equalTo() {
		if (subPackets.get(0).value == subPackets.get(1).value) {
			return 1L;
		} else {
			return 0L;
		}
	}
}


