package com.mattgould.day09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day09Test {

	@Test
	void go() {
		assertEquals(15, Day09.go("/home/mgould/code/advent-2021/testinput.09"));
		assertEquals(1134, Day09.go2("/home/mgould/code/advent-2021/testinput.09"));
	}
}
