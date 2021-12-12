package com.mattgould.day11;

import com.mattgould.day10.Day10;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day11Test {

	@Test
	void go() {
		assertEquals(204, Day11.go("/home/mgould/code/advent-2021/testinput.11", 10));
		assertEquals(1656, Day11.go("/home/mgould/code/advent-2021/testinput.11", 100));

		assertEquals(195, Day11.go2("/home/mgould/code/advent-2021/testinput.11"));

	}
}
