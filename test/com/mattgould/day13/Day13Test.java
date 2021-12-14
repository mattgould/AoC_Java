package com.mattgould.day13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day13Test {

	@Test
	void go() {
		assertEquals(17, Day13.go("/home/mgould/code/advent-2021/testinput.13", 1));
		assertEquals(16, Day13.go("/home/mgould/code/advent-2021/testinput.13", 0));

	}
}
