package com.mattgould.day10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {

	@Test
	void go() {
		assertEquals(26394, Day10.go("/home/mgould/code/advent-2021/testinput.10.a"));

		assertEquals(26397, Day10.go("/home/mgould/code/advent-2021/testinput.10"));

		assertEquals(3, Day10.go2("/home/mgould/code/advent-2021/testinput.10.a"));

		assertEquals(288957, Day10.go2("/home/mgould/code/advent-2021/testinput.10"));
	}
}
