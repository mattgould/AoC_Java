package com.mattgould.day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Day04 {

	private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

	public static int go(String file, boolean win) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			String[] strArray = line.split(",");
			int[] numbers = new int[strArray.length];
			for(int i = 0; i < strArray.length; i++) {
				numbers[i] = Integer.parseInt(strArray[i]);
			}

			br.readLine();
			int[][] values = new int[5][5];
			int row = 0;
			ArrayList<BingoBoard> boards = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				if (line.equals("")) {
					boards.add(new BingoBoard(values));
					values = new int[5][5];
					row = 0;
					continue;
				}
				String[] lineNumbers = line.trim().split(" +", 5);
				for (int i = 0; i < lineNumbers.length; i++) {
					values[row][i] = Integer.parseInt(lineNumbers[i]);
				}
				row++;

			}
			boards.add(new BingoBoard(values));

			if (win) {
				int winningScore = playToWin(boards, numbers);
				logger.info("Winning Score: " + winningScore);
				return winningScore;
			} else {

				int losingScore = playToLose(boards, numbers);
				logger.info("Losing Score: " + losingScore);
				return losingScore;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		// return the score
		return 0;

	}

	private static int playToWin(ArrayList<BingoBoard> boards, int[] numbers) {
		for (int number: numbers) {
			for (BingoBoard board : boards) {
				if (board.markAndCheckWin(number)) {
					return board.score(number);
				}
			}
		}
		return 0;
	}
	private static int playToLose(ArrayList<BingoBoard> boards, int[] numbers) {
		int numBoards = boards.size();
		int wonBoards = 0;
		for (int number: numbers) {
			for (BingoBoard board : boards) {
				if (!board.alreadyWon) {
					if (board.markAndCheckWin(number)) {
						wonBoards++;
						if (numBoards == wonBoards) {
							return board.score(number);
						}

					}
				}
			}
		}
		return 0;
	}


}
