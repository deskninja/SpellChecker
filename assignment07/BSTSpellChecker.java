package assignment07;

import components.list.List;
import components.list.ListOnArrays;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

/**
 * 
 * @author Joshua Wells, Jonathan Oliveros
 *
 */
public class BSTSpellChecker implements SpellChecker {

	// Instance variables
	private BinarySearchTreeOfStrings validWords;
	private List<String> misspelledWords;

	/**
	 * No argument constructor.
	 */
	public BSTSpellChecker() {
		clear();
	}

	/**
	 * Reads the file given and separates each line into words to insert.
	 */
	@Override
	public void loadValidWords(String filename) {
		SimpleReader s = new SimpleReader1L(filename);
		while (!s.atEOS()) {
			String line = s.nextLine();
			//separate lines into words to insert using regex
			for (String str : line.split(" ")) {
				validWords.insert(str);
			}
		}
		s.close();
	}

	/**
	 * Checks against the valid words and if invalid, adds it to
	 * the misspelled list.
	 */
	@Override
	public List<String> misspelledWords(String filename) {
		SimpleReader s = new SimpleReader1L(filename);
		while (!s.atEOS()) {
			String line = s.nextLine();
			//separate lines into words to find using regex
			for (String str : line.split(" ")) {
				if (!validWords.contains(str)) {
					misspelledWords.add(str);
				}
			}
		}
		s.close();
		return misspelledWords;
	}


	@Override
	public void clear() {
		misspelledWords = new ListOnArrays<String>();
		validWords = new BinarySearchTreeOfStrings();
	}
}