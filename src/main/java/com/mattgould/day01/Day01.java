package com.mattgould.day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

public class Day01 {
	private Day01() {}

	private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

	public static Integer go(String file) {
		DepthWindow depthWindow = new DepthWindow(3);
		Integer lastSum = null;
		Integer count = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				depthWindow.addDepth(Integer.parseInt(line));
				Integer cur = depthWindow.getTotalDepth();

				if (lastSum != null && cur != null && cur > lastSum) {
					count++;
				}
				lastSum = cur;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Count: " + count);
		logger.info("Count: " + count);

		return count;
	}
}
