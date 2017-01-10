package shultz.algorithms.isomorph;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Storage {
	private LinkedHashMap<String, ArrayList<String>> exactIsomorphs;
	private LinkedHashMap<String, ArrayList<String>> looseIsomorphs;
	private ArrayList<String> nonIsomorphs;

	public Storage() {
		initializeContainers();
	}

	//FIX WORD ORDER!!!
	
	public void initializeContainers() {
		exactIsomorphs = new LinkedHashMap<String, ArrayList<String>>();
		looseIsomorphs = new LinkedHashMap<String, ArrayList<String>>();
		nonIsomorphs = new ArrayList<String>();
	}

	public void determineNonIsomorphs() {
		ArrayList<String> possible = generatePossibleList();
		ArrayList<String> patternsToRemove = new ArrayList<String>();
		for (String pattern : looseIsomorphs.keySet()) {
			ArrayList<String> currentPatternWords = looseIsomorphs.get(pattern);
			if (currentPatternWords.size() < 2) {
				if (possible.contains(currentPatternWords.get(0))) {
					nonIsomorphs.add(currentPatternWords.get(0));
				}
				patternsToRemove.add(pattern);
			}
		}
		removePatternsFromLoose(patternsToRemove);
	}

	private ArrayList<String> generatePossibleList() {
		ArrayList<String> possible = new ArrayList<String>();
		ArrayList<String> patternsToRemove = new ArrayList<String>();
		for (String pattern : exactIsomorphs.keySet()) {
			ArrayList<String> currentPatternWords = exactIsomorphs.get(pattern);
			if (currentPatternWords.size() < 2) {
				possible.add(currentPatternWords.get(0));
				patternsToRemove.add(pattern);
			}
		}
		removePatternsFromExact(patternsToRemove);
		return possible;
	}

	private void removePatternsFromExact(ArrayList<String> patternsToRemove) {
		for (String pattern : patternsToRemove)
			exactIsomorphs.remove(pattern);
	}
	private void removePatternsFromLoose(ArrayList<String> patternsToRemove){
		for (String pattern : patternsToRemove)
			looseIsomorphs.remove(pattern);
	}

	public void addExactPattern(String pattern, String word) {
		exactIsomorphs.put(pattern, getNewArrayList(word));
	}

	public void addLoosePattern(String pattern, String word) {
		looseIsomorphs.put(pattern, getNewArrayList(word));
	}

	public void addWordToExistingExactPattern(String pattern, String word) {
		exactIsomorphs.get(pattern).add(word);
	}

	public void addWordToExistingLoosePattern(String pattern, String word) {
		looseIsomorphs.get(pattern).add(word);
	}

	public boolean exactPatternExists(String pattern) {
		return exactIsomorphs.containsKey(pattern);
	}

	public boolean loosePatternExists(String pattern) {
		return looseIsomorphs.containsKey(pattern);
	}

	private ArrayList<String> getNewArrayList(String word) {
		ArrayList<String> wordList = new ArrayList<String>();
		wordList.add(word);
		return wordList;
	}

	public LinkedHashMap<String, ArrayList<String>> getExactIsomorphs() {
		return exactIsomorphs;
	}

	public LinkedHashMap<String, ArrayList<String>> getLooseIsomorphs() {
		return looseIsomorphs;
	}

	public ArrayList<String> getNonIsomorphs() {
		return nonIsomorphs;
	}
	
}
