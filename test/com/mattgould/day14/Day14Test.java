package com.mattgould.day14;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class Day14Test {

	@Test
	void go() {
		assertEquals(new BigInteger("1588"), Day14.go("/home/mgould/code/advent-2021/testinput.14", 10));
		assertEquals(new BigInteger("1588"), Day14.go2("/home/mgould/code/advent-2021/testinput.14", 10));
		assertEquals(new BigInteger("2188189693529"), Day14.go2("/home/mgould/code/advent-2021/testinput.14", 40));

	}
}
