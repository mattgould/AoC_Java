package com.mattgould.day01;

import com.mattgould.day01.Day01;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day01Test {

	@Test
	void go() {
		Integer count = Day01.go("/home/mgould/code/advent-2021/testinput.01");
		assertEquals(5, count);
	}
}
