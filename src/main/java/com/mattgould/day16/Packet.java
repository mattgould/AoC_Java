package com.mattgould.day16;

import com.mattgould.ANSI;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

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

	@Override public String toString() {
		StringBuilder representation = new StringBuilder();
		if (type != 4) {
			ArrayList<String> subPacketStrings = new ArrayList<>();
			for (Packet p : subPackets) {
				subPacketStrings.add(p.toString());
			}
			String start = "";
			String delimiter = "";
			switch (type) {
				case 0: // sum
					start = "(";
					delimiter = " + ";
					break;
				case 1: // product
					start = "(";
					delimiter = " * ";
					break;
				case 2: // min
					start = "min(";
					delimiter = ", ";
					break;
				case 3: //max
					start = "max(";
					delimiter = ", ";
					break;
				case 5: // >
					start = "(";
					delimiter = " > ";
					break;
				case 6: // <
					start = "(";
					delimiter = " < ";
					break;
				case 7: // ==
					start = "(";
					delimiter = " == ";
					break;
			}
			representation.append(start);
			representation.append(String.join(delimiter, subPacketStrings));
			representation.append(")");
			representation.append(ANSI.BRIGHT_WHITE);
			representation.append("[");
			representation.append(value);
			representation.append("]");
			representation.append(ANSI.RESET);
		} else {
			representation.append(ANSI.YELLOW);
			representation.append(value);
			representation.append(ANSI.RESET);
		}


		return representation.toString();
	}
}


