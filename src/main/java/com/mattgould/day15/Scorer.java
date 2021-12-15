package com.mattgould.day15;

public interface Scorer<T extends GraphNode> {
	int computeCost(T from, T to);
}
