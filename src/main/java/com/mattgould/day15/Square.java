package com.mattgould.day15;

import java.util.StringJoiner;

public class Square implements GraphNode {
	final String id;

	final int risk;

	public Square(String id, int risk) {
		this.id = id;
		this.risk = risk;
	}

	@Override
	public String getId() {
		return id;
	}

	public int getRisk() {
		return risk;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Square.class.getSimpleName() + "[", "]").add("id='" + id + "'")
			.add("risk='" + risk + "'").toString();
	}


}
