package com.mattgould.day17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProbeTest {

	@Test
	void fire() {
		Probe p = new Probe("target area: x=20..30, y=-10..-5");
		assertEquals(20, p.targetMinX);
		assertEquals(30, p.targetMaxX);
		assertEquals(-10, p.targetMinY);
		assertEquals(-5, p.targetMaxY);
		assertEquals(Probe.Result.HIT, p.fire(7, 2));

		p.reset();
		assertEquals(Probe.Result.HIT, p.fire(6,3));

		p.reset();
		assertEquals(Probe.Result.HIT, p.fire(9,0));

		p.reset();
		assertEquals(Probe.Result.MISS_X, p.fire(17,-4));

		p.reset();
		assertEquals(Probe.Result.HIT, p.fire(6, 9));
		assertEquals(45, p.maxYPos);

		int countHits = 0;
		int maxHitX = -1000;
		int maxHitY = -1000;
		int minHitX = 1000;
		int minHitY = 1000;
		for (int x = -1000; x <= 1000; x++) {
			for (int y = -1000; y <= 1000; y++) {
				p.reset();
				Probe.Result result = p.fire(x, y);
				String maxY = "";
				if (result.equals(Probe.Result.HIT)) {
					countHits++;
					if (x > maxHitX) maxHitX = x;
					if (y > maxHitY) maxHitY = y;
					if (x < minHitX) minHitX = x;
					if (y < minHitY) minHitY = y;
					maxY = String.format(" (%d)", p.maxYPos);

				}

			}
		}

		System.out.printf("X: %d..%d Y: %d..%d Hits:%d%n", minHitX, maxHitX, minHitY, maxHitY, countHits);

		assertEquals(112, countHits);
	}
}
