package com.mattgould.day15;

import com.mattgould.day13.Day13;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day15Test {

	@Test
	void go() {
		assertEquals(40, Day15.go("/home/mgould/code/advent-2021/testinput.15", 1));
		assertEquals(315, Day15.go("/home/mgould/code/advent-2021/testinput.15", 5));
	}
}
