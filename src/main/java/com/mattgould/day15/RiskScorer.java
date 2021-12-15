package com.mattgould.day15;

public class RiskScorer implements Scorer<Square> {
	@Override
	public int computeCost(Square from, Square to) {
		return to.risk;
	}
}
