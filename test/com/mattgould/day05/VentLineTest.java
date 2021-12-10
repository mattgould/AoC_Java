package com.mattgould.day05;

import com.google.common.base.Stopwatch;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VentLineTest {

	@Test
	void getPoints() {

		VentLine horizontalVentLine = new VentLine("0,0 -> 0,100000000");
		VentLine verticalVentLine = new VentLine("0,0 -> 100000000,0");
		VentLine diagonalVentLine = new VentLine("0,0 -> 100000000,100000000");


		Stopwatch timer = Stopwatch.createStarted();
		horizontalVentLine.getPoints(true);
		System.out.println("Horizontal old took: " + timer.stop());
		timer = Stopwatch.createStarted();
		horizontalVentLine.newGetPoints();
		System.out.println("Horizontal new took: " + timer.stop());

		timer = Stopwatch.createStarted();
		verticalVentLine.getPoints(true);
		System.out.println("Vertical old took: " + timer.stop());
		timer = Stopwatch.createStarted();
		verticalVentLine.newGetPoints();
		System.out.println("Vertical new took: " + timer.stop());

		timer = Stopwatch.createStarted();
		diagonalVentLine.getPoints(true);
		System.out.println("Diagonal old took: " + timer.stop());
		timer = Stopwatch.createStarted();
		diagonalVentLine.newGetPoints();
		System.out.println("Diagonal new took: " + timer.stop());
	}

	@Test
	void newGetPoints() {
	}
}
