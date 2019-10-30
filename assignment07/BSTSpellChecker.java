package assignment07;

import components.list.List;
import components.list.ListOnArrays;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

/**
 * @author Joshua Wells, Jonathan Oliveros
 *
 */
public class BSTSpellChecker implements SpellChecker {

	private BinarySearchTreeOfStrings validWords;
	private List<String> misspelledWords;

	public BSTSpellChecker() {
		clear();
	}

	@Override
	public void loadValidWords(String filename) {
		SimpleReader s = new SimpleReader1L(filename);
		while (!s.atEOS()) {
			String line = s.nextLine();
			for (String str : line.split(" ")) {
				validWords.insert(str);
			}
		}
		s.close();
	}

	@Override
	public List<String> misspelledWords(String filename) {
		SimpleReader s = new SimpleReader1L(filename);
		while (!s.atEOS()) {
			String line = s.nextLine();
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
