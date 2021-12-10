package com.mattgould.day08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day08Test {

	@Test
	void go() {
		assertEquals(26, Day08.go("/home/mgould/code/advent-2021/testinput.08"));
		assertEquals(61229, Day08.go2("/home/mgould/code/advent-2021/testinput.08"));
	}
}
