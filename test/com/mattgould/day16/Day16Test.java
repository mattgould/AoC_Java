package com.mattgould.day16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day16Test {

	@Test
	void go() {
		assertEquals(6, Day16.go("D2FE28"));
		assertEquals(16, Day16.go("8A004A801A8002F478"));
		assertEquals(12, Day16.go("620080001611562C8802118E34"));
		assertEquals(23, Day16.go("C0015000016115A2E0802F182340"));
		assertEquals(31, Day16.go("A0016C880162017C3686B18A3D4780"));
	}

	@Test
	void go2() {
		assertEquals(3L, Day16.go2("C200B40A82"));
		assertEquals(54L, Day16.go2("04005AC33890"));
		assertEquals(7L, Day16.go2("880086C3E88112"));
		assertEquals(9L, Day16.go2("CE00C43D881120"));
		assertEquals(1L, Day16.go2("D8005AC2A8F0"));
		assertEquals(0L, Day16.go2("F600BC2D8F"));
		assertEquals(0L, Day16.go2("9C005AC2F8F0"));
		assertEquals(1L, Day16.go2("9C0141080250320F1802104A08"));
	}

	@Test
	void hexToBits() {
		assertEquals("110100101111111000101000", Day16.hexToBits("D2FE28"));
		assertEquals("00111000000000000110111101000101001010010001001000000000", Day16.hexToBits("38006F45291200"));
		assertEquals("11101110000000001101010000001100100000100011000001100000", Day16.hexToBits("EE00D40C823060"));
	}
}
