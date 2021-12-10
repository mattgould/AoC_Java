package com.mattgould.day02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day02Test {

	@Test
	void go() {
		Submarine submarine = Day02.go("/home/mgould/code/advent-2021/testinput.02");
		assertEquals(15, submarine.getHorizontalPosition());
		assertEquals(60, submarine.getDepth());
		assertEquals(900, submarine.getProduct());
	}
}
