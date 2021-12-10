package com.mattgould.day06;

public class LanternFish {
	int timer;
	public LanternFish(int timer) {
		this.timer = timer;
	}
	public LanternFish() {
		this(8);
	}

	public boolean ageFish() {
		if (timer-- == 0) {
			timer = 6;
			return true;
		}
		return false;
	}
}
