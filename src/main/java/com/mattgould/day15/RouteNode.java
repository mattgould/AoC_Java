package com.mattgould.day15;
import java.util.StringJoiner;

class RouteNode<T extends GraphNode> implements Comparable<RouteNode> {
	private final T current;
	private T previous;
	private int routeScore;
	private int estimatedScore;

	RouteNode(T current) {
		this(current, null, Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	RouteNode(T current, T previous, int routeScore, int estimatedScore) {
		this.current = current;
		this.previous = previous;
		this.routeScore = routeScore;
		this.estimatedScore = estimatedScore;
	}

	T getCurrent() {
		return current;
	}

	T getPrevious() {
		return previous;
	}

	int getRouteScore() {
		return routeScore;
	}

	int getEstimatedScore() {
		return estimatedScore;
	}

	void setPrevious(T previous) {
		this.previous = previous;
	}

	void setRouteScore(int routeScore) {
		this.routeScore = routeScore;
	}

	void setEstimatedScore(int estimatedScore) {
		this.estimatedScore = estimatedScore;
	}

	@Override
	public int compareTo(RouteNode other) {
		if (this.estimatedScore > other.estimatedScore) {
			return 1;
		} else if (this.estimatedScore < other.estimatedScore) {
			return -1;
		} else {
			return 0;
		}
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", RouteNode.class.getSimpleName() + "[", "]").add("current=" + current)
			.add("previous=" + previous).add("routeScore=" + routeScore).add("estimatedScore=" + estimatedScore)
			.toString();
	}
}
