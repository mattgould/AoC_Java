package com.mattgould.day17;

public class Probe {
	long targetMinX;
	long targetMaxX;
	long targetMinY;
	long targetMaxY;

	long xPos;
	long yPos;

	long maxYPos;

	public enum Result {
		HIT,
		MISS_BOTH,
		MISS_X,
		MISS_Y,
	}

	public Probe(String target) {
		String[] targetParts = target.split(" ");
		targetMinX = Long.parseLong(targetParts[2].substring(2, targetParts[2].indexOf('.')));
		targetMaxX = Long.parseLong(targetParts[2].substring(targetParts[2].indexOf('.') + 2, targetParts[3].length() - 1));
		targetMinY = Long.parseLong(targetParts[3].substring(2, targetParts[3].indexOf('.')));
		targetMaxY = Long.parseLong(targetParts[3].substring(targetParts[3].indexOf('.') + 2));
	}

	public void reset() {
		xPos = 0;
		yPos = 0;
		maxYPos = 0;
	}


	public Result fire(long xVelocity, long yVelocity) {
		xPos += xVelocity;
		yPos += yVelocity;
		if (yPos > maxYPos) {
			maxYPos = yPos;
		}
		yVelocity--;
		if (xVelocity > 0) {
			xVelocity--;
		} else if (xVelocity < 0) {
			xVelocity++;
		}

		if (xPos >= targetMinX && xPos <= targetMaxX && yPos >= targetMinY && yPos <= targetMaxY) {
			return Result.HIT;
		}

		if (xPos > targetMaxX && yPos < targetMinY) {
			return Result.MISS_BOTH;
		}

		if (xPos > targetMaxX) {
			return Result.MISS_X;
		}

		if (yPos < targetMinY) {
			return Result.MISS_Y;
		}
		return fire(xVelocity, yVelocity);
	}
}
