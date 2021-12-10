package com.mattgould.dayx;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.Date;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DayX {
	private DayX() {}

	public static void start() {
		DayX.go(Date.from(Instant.now()), "MMddyyyy");
		DayX.go(Date.from(Instant.now()), "yyyyMMdd");
		DayX.go(Date.from(Instant.now()), "ddMMyyyy");

		DayX.go(Date.from(Instant.now()), "MMddyy");
		DayX.go(Date.from(Instant.now()), "yyMMdd");
		DayX.go(Date.from(Instant.now()), "ddMMyy");

		DayX.go(Date.from(Instant.now()), "Mdyy");
		DayX.go(Date.from(Instant.now()), "yyMd");
		DayX.go(Date.from(Instant.now()), "dMyy");

	}

	public static void go(Date date, String datePattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
		String palindrome = "";
		String ambigram = "";

		while (palindrome.equals("") || ambigram.equals("")) {
			date = Date.from(date.toInstant().plus(1, ChronoUnit.DAYS));
			String dateString = simpleDateFormat.format(date);
			if (isPalindrome(dateString)) {
				if (palindrome.equals("")) {
					palindrome = dateString;
				}
				if (onlyAmbigramNumbers(dateString)) {
					ambigram = dateString;
				}
			}

		}
		System.out.println("Format:" + datePattern);
		System.out.println("Next Palindrome: " + palindrome);
		System.out.println("Next Ambigram: " + ambigram);

	}
	private static boolean isPalindrome(String dateString) {
		int length = dateString.length();
		int forward = 0;
		int backward = length - 1;
		while (backward > forward) {
			if (dateString.charAt(forward++) != dateString.charAt(backward--))
				return false;
		}
		return true;
	}

	private static boolean onlyAmbigramNumbers(String dateString) {
		Set<Character> filter = new HashSet<>(Arrays.asList('0', '1', '2', '5', '8', '0'));
		String filtered = dateString.chars ()
			.filter(i -> filter.contains((char) i))
			.mapToObj(i -> "" + (char) i)
			.collect(Collectors.joining());

		return filtered.equals(dateString);

	}
}
