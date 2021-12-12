package com.mattgould.day12;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class Day12 {
	private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
	public static final String START = "start";
	public static final String END = "end";
	public static HashMap<String, ArrayList<String>> caves = new HashMap<>();

	public static int go(String file) {

		getCaves(file);
		ArrayList<ArrayList<String>> paths = getValidPathsOncePerSmall(new ArrayList<>(List.of(START)));
		logger.info("Num Paths: " + paths.size());
		return paths.size();
	}

	public static int go2(String file) {

		getCaves(file);
		ArrayList<ArrayList<String>> paths = getValidPathsOneSmallTwice(new ArrayList<>(List.of(START)));
		logger.info("Num Paths: " + paths.size());
		return paths.size();
	}

	public static ArrayList<ArrayList<String>> getValidPathsOncePerSmall(ArrayList<String> pathSoFar) {
		ArrayList<ArrayList<String>> newPaths = new ArrayList<>();
		String currentCaveName = pathSoFar.get(pathSoFar.size()-1);
		for (String cave : caves.get(currentCaveName)) {
			ArrayList<String> newPath = new ArrayList<>(pathSoFar);
			newPath.add(cave);
			if (END.equals(cave)) {
				newPaths.add(newPath);
			}
			if (isValidPathOncePerSmall(newPath)) {
				newPaths.addAll(getValidPathsOncePerSmall(newPath));
			}
		}
		return newPaths;
	}

	public static boolean isValidPathOncePerSmall(ArrayList<String> newPath) {
		ArrayList<String> smallCaves = new ArrayList<>();
		for ( String caveName : newPath ) {
			if (caveName.toLowerCase().equals(caveName)) {
				if (smallCaves.contains(caveName)) {
					return false;
				}
				smallCaves.add(caveName);
			}
		}
		return true;
	}

	public static ArrayList<ArrayList<String>> getValidPathsOneSmallTwice(ArrayList<String> pathSoFar) {
		ArrayList<ArrayList<String>> newPaths = new ArrayList<>();
		String currentCaveName = pathSoFar.get(pathSoFar.size()-1);
		for (String cave : caves.get(currentCaveName)) {
			ArrayList<String> newPath = new ArrayList<>(pathSoFar);
			newPath.add(cave);
			if (END.equals(cave)) {
				newPaths.add(newPath);
			}
			if (isValidPathOnceSmallTwice(newPath)) {
				newPaths.addAll(getValidPathsOneSmallTwice(newPath));
			}
		}
		return newPaths;
	}

	public static boolean isValidPathOnceSmallTwice(ArrayList<String> newPath) {
		ArrayList<String> smallCaves = new ArrayList<>();
		Integer twiceSmalls = 0;
		for ( String caveName : newPath ) {
			if (caveName.toLowerCase().equals(caveName)) {
				if (smallCaves.contains(caveName)) {
					if (++twiceSmalls > 1) {
						return false;
					}
				}
				smallCaves.add(caveName);
			}
		}
		return true;
	}


	private static void getCaves(String file) {
		caves = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split("-");
				if (!parts[0].equals(END)) {
					if (caves.containsKey(parts[0])) {
						if (!parts[1].equals(START)) {
							ArrayList<String> links = caves.get(parts[0]);
							links.add(parts[1]);
						}
					} else {
						if (!parts[1].equals(START)) {
							caves.put(parts[0], new ArrayList<>(List.of(parts[1])));
						}
					}
				} else {
					caves.put(END, new ArrayList<>());
				}

				if (!parts[1].equals(END)) {
					if (caves.containsKey(parts[1])) {
						if (!parts[0].equals(START)) {
							ArrayList<String> links = caves.get(parts[1]);
							links.add(parts[0]);
						}
					} else {
						if (!parts[0].equals(START)) {
							caves.put(parts[1], new ArrayList<>(List.of(parts[0])));
						}
					}
				} else {
					caves.put(END, new ArrayList<>());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
