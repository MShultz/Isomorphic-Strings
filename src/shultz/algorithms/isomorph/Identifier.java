package shultz.algorithms.isomorph;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Identifier {
	private Reader fileReader;
	private Writer outputWriter;
	private Storage isomorphStorage;
	private final int CHARACTER_INCREMENT = 1;

	public Identifier(String fileName) throws FileNotFoundException {
		fileReader = new Reader(fileName);
		isomorphStorage = new Storage();
		outputWriter = new Writer(isomorphStorage);
	}

	public void identify() {
		String currentWord = fileReader.getNextWord();
		while (currentWord != null) {
			addExactPatternToStorage(identifyExactIsomorphicPattern(currentWord), currentWord);
			addLoosePatternToStorage(identifyLooseIsomorphicPattern(currentWord), currentWord);
			currentWord = fileReader.getNextWord();
		}
		isomorphStorage.determineNonIsomorphs();
		outputWriter.writeToFile();
		outputWriter.closeFile();

	}

	private void addExactPatternToStorage(String pattern, String currentWord) {
		if (isomorphStorage.exactPatternExists(pattern)) {
			isomorphStorage.addWordToExistingExactPattern(pattern, currentWord);
		} else {
			isomorphStorage.addExactPattern(pattern, currentWord);
		}
	}

	private void addLoosePatternToStorage(String pattern, String currentWord) {
		if (isomorphStorage.loosePatternExists(pattern)) {
			isomorphStorage.addWordToExistingLoosePattern(pattern, currentWord);
		} else {
			isomorphStorage.addLoosePattern(pattern, currentWord);
		}
	}

	private String identifyExactIsomorphicPattern(String currentWord) {
		String pattern = "";
		int count = 0;
		for (int i = 0; i < currentWord.length(); ++i) {
			String currentChar = currentWord.charAt(i) + "";
			int firstIndexOfChar = currentWord.indexOf(currentChar);
			if (firstIndexOfChar < i) {
				pattern += " " + firstIndexOfChar;
			} else {
				pattern += " " + count;
				++count;
			}
		}
		return pattern.trim();
	}

	private String identifyLooseIsomorphicPattern(String currentWord) {
		String pattern = "";
		List<Integer> loosePattern = new ArrayList<Integer>(getCharacterMapping(currentWord).values());
		Collections.sort(loosePattern);
		for (Integer count : loosePattern) {
			pattern += " " + count;
		}
		return pattern.trim();
	}

	private HashMap<Character, Integer> getCharacterMapping(String currentWord) {
		HashMap<Character, Integer> loosePattern = new HashMap<Character, Integer>();
		for (int i = 0; i < currentWord.length(); ++i) {
			char currentCharacter = currentWord.charAt(i);
			if (loosePattern.containsKey(currentCharacter)) {
				loosePattern.put(currentCharacter, loosePattern.get(currentCharacter) + CHARACTER_INCREMENT);
			} else {
				loosePattern.put(currentCharacter, CHARACTER_INCREMENT);
			}
		}
		return loosePattern;
	}
}
