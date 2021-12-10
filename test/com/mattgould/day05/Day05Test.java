package com.mattgould.day05;

import com.mattgould.day05.Day05;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day05Test {

	@Test
	void go() {
		Assertions.assertEquals(5, Day05.go("/home/mgould/code/advent-2021/testinput.05", false));
		assertEquals(12, Day05.go("/home/mgould/code/advent-2021/testinput.05", true));
		assertEquals(12, Day05.go2("/home/mgould/code/advent-2021/testinput.05"));
	}
}
