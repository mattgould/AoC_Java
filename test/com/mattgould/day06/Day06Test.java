package com.mattgould.day06;

import com.mattgould.day05.Day05;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day06Test {

	@Test
	void go() {
		assertEquals(26, Day06.go("/home/mgould/code/advent-2021/testinput.06", 18));
		assertEquals(5934, Day06.go("/home/mgould/code/advent-2021/testinput.06", 80));
	}

	@Test
	void go2() {
		assertEquals(BigInteger.valueOf(26), Day06.go2("/home/mgould/code/advent-2021/testinput.06", Arrays.asList(18)));
		assertEquals(BigInteger.valueOf(5934), Day06.go2("/home/mgould/code/advent-2021/testinput.06", Arrays.asList(80)));
	}
}
