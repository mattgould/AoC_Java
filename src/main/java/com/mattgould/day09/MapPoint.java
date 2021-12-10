package com.mattgould.day09;

import java.security.PublicKey;

public class MapPoint {
	int x;
	int y;
	public MapPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override public String toString() {
		return x + "," + y;
	}
}

