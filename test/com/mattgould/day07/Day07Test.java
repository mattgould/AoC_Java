package com.mattgould.day07;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day07Test {

	@Test
	void go() {
		assertEquals(2, Day07.go("/home/mgould/code/advent-2021/testinput.07"));
		assertEquals(5, Day07.go2("/home/mgould/code/advent-2021/testinput.07"));
	}

	@Test
	void fuelCost() {
		assertEquals(0, Day07.fuelCost(0));
		assertEquals(1, Day07.fuelCost(1));
		assertEquals(3, Day07.fuelCost(2));
		assertEquals(6, Day07.fuelCost(3));
		assertEquals(10, Day07.fuelCost(4));
		assertEquals(15, Day07.fuelCost(5));
	}
}
