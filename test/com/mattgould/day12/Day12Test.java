package com.mattgould.day12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {

	@Test
	void go() {
		assertEquals(10, Day12.go("/home/mgould/code/advent-2021/testinput.12.a"));
		assertEquals(19, Day12.go("/home/mgould/code/advent-2021/testinput.12.b"));
		assertEquals(226, Day12.go("/home/mgould/code/advent-2021/testinput.12.c"));

		assertEquals(36, Day12.go2("/home/mgould/code/advent-2021/testinput.12.a"));
		assertEquals(103, Day12.go2("/home/mgould/code/advent-2021/testinput.12.b"));
		assertEquals(3509, Day12.go2("/home/mgould/code/advent-2021/testinput.12.c"));

	}
}
