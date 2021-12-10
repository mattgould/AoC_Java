package com.mattgould.day04;

import com.mattgould.day04.Day04;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day04Test {

	@Test
	void go() {
		Assertions.assertEquals(4512, Day04.go("/home/mgould/code/advent-2021/testinput.04", true));
		assertEquals(1924, Day04.go("/home/mgould/code/advent-2021/testinput.04", false));
	}
}
