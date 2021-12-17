package com.mattgould.day17;

import com.mattgould.day16.Packet;

import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

public class Day17 {
	private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

	public static long go(String target) {
		long maxY = Long.MIN_VALUE;
		long bestX = 0;
		long bestY = 0;

		long countHits = 0;
		long maxHitX = -1000;
		long maxHitY = -1000;
		long minHitX = 1000;
		long minHitY = 1000;

		Probe p = new Probe(target);

		for (long x = -1000; x <= p.targetMaxX; x++) {
			for (long y = p.targetMinY; y <= 1000; y++) {
				p.reset();
				Probe.Result result = p.fire(x, y);
				String max = "";
				if (result.equals(Probe.Result.HIT)) {
					countHits++;
					if (x > maxHitX) maxHitX = x;
					if (y > maxHitY) maxHitY = y;
					if (x < minHitX) minHitX = x;
					if (y < minHitY) minHitY = y;

					if (p.maxYPos > maxY) {
						maxY = p.maxYPos;
						bestX = x;
						bestY = y;
					}

				}
			}
		}

		System.out.printf("X: %d..%d Y: %d..%d Hits:%d%n", minHitX, maxHitX, minHitY, maxHitY, countHits);

		System.out.printf("%nMaxY: %d%nInitial Velocity: %d,%d%n", maxY, bestX, bestY);

		return maxY;

	}

}


