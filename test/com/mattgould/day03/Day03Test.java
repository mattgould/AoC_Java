package com.mattgould.day03;

import com.mattgould.day03.Day03;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day03Test {

	@Test
	void go() {
		int consumption = Day03.submarineConsumption("/home/mgould/code/advent-2021/testinput.03");
		assertEquals(198, consumption);

		int lifeSupportRating = Day03.lifeSupportRating("/home/mgould/code/advent-2021/testinput.03");
		assertEquals(230, lifeSupportRating);
	}
}
