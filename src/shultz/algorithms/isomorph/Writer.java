package shultz.algorithms.isomorph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class Writer {
	Storage isomorphStorage;
	private final String FILE_NAME = "Output.txt";
	private final String EXACT_TITLE = "Exact Isomorphs";
	private final String LOOSE_TITLE = "Loose Isomorphs";
	private final String NON_TITLE = "Non-isomorphs";
	LinkedHashMap<String, ArrayList<String>> exactIsomorphs;
	LinkedHashMap<String, ArrayList<String>> looseIsomorphs;
	ArrayList<String> nonIsomorphs;

	File output;
	BufferedWriter results = null;
	FileWriter innerWriter = null;

	public Writer(Storage isomorphStorage) {
		generateFile();
		initializeWriter();
		this.isomorphStorage = isomorphStorage;
	}

	private void initializeWriter() {
		try {
			innerWriter = new FileWriter(output);
			results = new BufferedWriter(innerWriter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setLists() {
		exactIsomorphs = isomorphStorage.getExactIsomorphs();
		looseIsomorphs = isomorphStorage.getLooseIsomorphs();
		nonIsomorphs = isomorphStorage.getNonIsomorphs();
	}

	private void generateFile() {
		try {
			output = new File(FILE_NAME);
			output.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeToFile() {
		try {
			setLists();
			results.write(EXACT_TITLE);
			results.newLine();
			writeExactIsomorphList(exactIsomorphs.keySet());
			results.write(LOOSE_TITLE);
			results.newLine();
			writeLooseIsomorphList(looseIsomorphs.keySet());
			writeNonIsomorphicList();
		} catch (IOException e) {
			System.out.println("Error: Unable to write to output file");
		}
	}

	public void writeExactIsomorphList(Set<String> allKeys) throws IOException {
		ArrayList<String> keyList = new ArrayList<String>(allKeys);
		Collections.sort(keyList);
		Iterator<String> keys = keyList.iterator();
		while (keys.hasNext()) {
			String currentKey = keys.next();
			String output = currentKey + ":";
			for (String word : alphabetizeCurrentExactList(currentKey)) {
				output += " " + word;
			}
			results.write(output);
			results.newLine();
		}
		results.newLine();
		results.flush();
	}

	private ArrayList<String> alphabetizeCurrentExactList(String currentKey) {
		ArrayList<String> currentList = exactIsomorphs.get(currentKey);
		Collections.sort(currentList);
		return currentList;
	}

	public void writeLooseIsomorphList(Set<String> allKeys) throws IOException {
		ArrayList<String> keyList = new ArrayList<String>(allKeys);
		Collections.sort(keyList);
		Iterator<String> keys = keyList.iterator();
		while (keys.hasNext()) {
			String currentKey = keys.next();
			String output = currentKey + ":";
			for (String word : alphabetizeCurrentLooseList(currentKey)) {
				output += " " + word;
			}
			results.write(output);
			results.newLine();
		}
		results.newLine();
		results.flush();
	}

	private ArrayList<String> alphabetizeCurrentLooseList(String currentKey) {
		ArrayList<String> currentList = looseIsomorphs.get(currentKey);
		Collections.sort(currentList);
		return currentList;
	}

	public void writeNonIsomorphicList() throws IOException {
		results.write(NON_TITLE);
		results.newLine();
		Collections.sort(nonIsomorphs);
		String output = "";
		for (String word : nonIsomorphs) {
			output += " " + word;
		}
		results.write(output.trim());
		results.flush();
	}

	public void closeFile() {
		try {
			results.close();
		} catch (IOException e) {
			System.out.println("Error: Unable to properly close output file");
		}
	}
}
