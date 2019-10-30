package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import components.list.List;
import components.list.ListOnArrays;

/**
 * @author Joshua Wells, Jonathan Oliveros
 *
 */
public class BSTSpellChecker implements SpellChecker {

	private BinarySearchTreeOfStrings validWords;
	private List<String> misspelledWords;

	public BSTSpellChecker() {
		clear();
		validWords = new BinarySearchTreeOfStrings();
	}

	@Override
	public void loadValidWords(String filename) {
		File file = new File(filename);
		Scanner s = new Scanner(file);
		while (s.hasNext()) {
			validWords.insert(s.next());
		}
		s.close();
	}

	@Override
	public List<String> misspelledWords(String filename) {
		File file = new File(filename);
		Scanner s = new Scanner(file);
		String word = "";
		misspelledWords.add("[");
		if (!validWords.contains(word)) {
			misspelledWords.add(word);
			while (s.hasNext()) {
				word = s.next();
				if (!validWords.contains(word)) {
					misspelledWords.add(word);
				}
				misspelledWords.add("]");
			}
		}
		s.close();
		System.out.println(misspelledWords.toString());
		return misspelledWords;
	}

	@Override
	public void clear() {
		misspelledWords = new ListOnArrays<String>();
	}
	// TODO create any field or helper methods

	// TODO constructor

	// TODO implement methods from the interface

}
