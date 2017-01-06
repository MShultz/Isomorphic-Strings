package shultz.algorithms.isomorph;

import java.io.FileNotFoundException;

public class Identifier {
	Reader fileReader;

	public Identifier(String fileName) throws FileNotFoundException {
		fileReader = new Reader(fileName);
	}

	public void identify() {
		String currentWord = null;
		do{
			currentWord = fileReader.getNextWord();
			if(currentWord != null){
				System.out.println("Word: " + currentWord);
				System.out.println("Pattern: " + identifyExactIsomorphicPattern(currentWord));
			}
		}while(currentWord != null);
	}

	public String identifyExactIsomorphicPattern(String currentWord) {
		String pattern = "";
		int count = 0;
		for (int i = 0; i < currentWord.length(); ++i) {
			String currentChar = currentWord.charAt(i) + "";
			int firstIndexOfChar = currentWord.indexOf(currentChar);
			if(firstIndexOfChar < i){
				pattern += firstIndexOfChar;
			}else{
				pattern += count;
				++count;
			}
		}
		return pattern;
	}
}
