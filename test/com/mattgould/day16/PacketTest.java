package com.mattgould.day16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacketTest {

	@Test
	void constructLiteralValue() {
		Packet p = new Packet("110100101111111000101000");
		assertEquals(6, p.version);
		assertEquals(4, p.type);
		assertEquals(2021, p.value);
		assertEquals(6, p.getVersionSum());
	}

	@Test
	void constructOperatorLengthType0() {
		Packet p = new Packet("00111000000000000110111101000101001010010001001000000000");
		assertEquals(1, p.version);
		assertEquals(6, p.type);
		assertEquals(0, p.lengthType);
		assertEquals(27, p.length);
		assertEquals(0, p.value);
		assertEquals(2, p.subPackets.size());
		assertEquals(9, p.getVersionSum());

	}

	@Test
	void constructOperatorLengthType1() {
		Packet p = new Packet("11101110000000001101010000001100100000100011000001100000");
		assertEquals(7, p.version);
		assertEquals(3, p.type);
		assertEquals(1, p.lengthType);
		assertEquals(3, p.length);
		assertEquals(0, p.value);
		assertEquals(3, p.subPackets.size());
		assertEquals(14, p.getVersionSum());
	}
}
