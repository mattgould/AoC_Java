package com.mattgould.day01;

import java.util.LinkedList;

public class DepthWindow {
	private final Integer windowSize;
	private LinkedList<Integer> depths;

	public DepthWindow(Integer windowSize) {
		this.windowSize = windowSize;
		depths = new LinkedList<>();
	}

	public void addDepth(Integer depth) {
		depths.add(depth);
		while (depths.size() > windowSize) {
			depths.removeFirst();
		}
	}

	public Integer getTotalDepth() {
		if (depths.size() == windowSize) {
			return depths.stream().mapToInt(Integer::intValue).sum();
		}
		return null;
	}
}
