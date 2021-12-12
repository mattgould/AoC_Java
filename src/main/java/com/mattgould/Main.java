package com.mattgould;

import com.google.common.base.Stopwatch;

import com.mattgould.day01.Day01;
import com.mattgould.day02.Day02;
import com.mattgould.day03.Day03;
import com.mattgould.day04.Day04;
import com.mattgould.day05.Day05;
import com.mattgould.day06.Day06;
import com.mattgould.day07.Day07;
import com.mattgould.day08.Day08;
import com.mattgould.day09.Day09;
import com.mattgould.day10.Day10;
import com.mattgould.day11.Day11;
import com.mattgould.day12.Day12;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

		Stopwatch timer = Stopwatch.createUnstarted();
		timer.start();
		Day01.go("/home/mgould/code/advent-2021/input.01");
		System.out.println("Day01 took: " + timer.stop());
		timer.reset();
		timer.start();
		Day02.go("/home/mgould/code/advent-2021/input.02");
		System.out.println("Day02 took: " + timer.stop());
		timer.reset();
		timer.start();
		Day03.go("/home/mgould/code/advent-2021/input.03");
		System.out.println("Day03 took: " + timer.stop());
		timer.reset();
		timer.start();
		Day04.go("/home/mgould/code/advent-2021/input.04", true);
		System.out.println("Day04 Win took: " + timer.stop());
		timer.reset();
		timer.start();
		Day04.go("/home/mgould/code/advent-2021/input.04", false);
		System.out.println("Day04 Lose took: " + timer.stop());
		timer.reset();
		timer.start();
		Day05.go("/home/mgould/code/advent-2021/input.05", false);
		System.out.println("Day05 no diagonals took: " + timer.stop());
		timer.reset();
		timer.start();
		Day05.go("/home/mgould/code/advent-2021/input.05", true);
		System.out.println("Day05 with diagonals took: " + timer.stop());
		timer.reset();
		timer.start();
		Day05.go2("/home/mgould/code/advent-2021/input.05");
		System.out.println("Day05 with diagonals optimized took: " + timer.stop());
		timer.reset();
		timer.start();
		Day06.go2("/home/mgould/code/advent-2021/input.06", Arrays.asList(80,256));
		System.out.println("Day06 took: " + timer.stop());
		timer.reset();
		timer.start();
		Day07.go("/home/mgould/code/advent-2021/input.07");
		System.out.println("Day07a took: " + timer.stop());
		timer.reset();
		timer.start();
		Day07.go2("/home/mgould/code/advent-2021/input.07");
		System.out.println("Day07b took: " + timer.stop());
		timer.reset();
		timer.start();
		Day08.go("/home/mgould/code/advent-2021/input.08");
		System.out.println("Day08a took: " + timer.stop());
		timer.reset();
		timer.start();
		Day08.go2("/home/mgould/code/advent-2021/input.08");
		System.out.println("Day08b took: " + timer.stop());
		timer.reset();
		timer.start();
		Day09.go("/home/mgould/code/advent-2021/input.09");
		System.out.println("Day09a took: " + timer.stop());
		timer.reset();
		timer.start();
		Day09.go2("/home/mgould/code/advent-2021/input.09");
		System.out.println("Day09b took: " + timer.stop());
		timer.reset();
		timer.start();
		Day10.go("/home/mgould/code/advent-2021/input.10");
		System.out.println("Day10a took: " + timer.stop());
		timer.reset();
		timer.start();
		Day10.go2("/home/mgould/code/advent-2021/input.10");
		System.out.println("Day10b took: " + timer.stop());
		timer.reset();
		timer.start();
		Day11.go("/home/mgould/code/advent-2021/input.11", 100);
		System.out.println("Day11a took: " + timer.stop());
		timer.reset();
		timer.start();
		Day11.go2("/home/mgould/code/advent-2021/input.11");
		System.out.println("Day11a took: " + timer.stop());
		timer.reset();
		timer.start();
		Day12.go("/home/mgould/code/advent-2021/input.12");
		System.out.println("Day12a took: " + timer.stop());
		timer.reset();
		timer.start();
		Day12.go2("/home/mgould/code/advent-2021/input.12");
		System.out.println("Day12b took: " + timer.stop());
	}

}
