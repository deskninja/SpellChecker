package assignment07;

import static org.junit.Assert.*;

import org.junit.Test;

import components.list.List;

/**
 * @author Joshua Wells, Jonathan Oliveros
 *
 */
public class SpellCheckerTest {
	@Test
	public void testInput1() {
		SpellChecker sc = new BSTSpellChecker();
		sc.loadValidWords("src/assignment07/a07-valid-words1.txt");
		List<String> result = sc.misspelledWords("src/assignment07/a07-input1.txt");
		assertEquals("[Hello,there,,world.,,Nice,you.]", result.toString());
	}

	@Test
	public void testInput2() {
		SpellChecker sc = new BSTSpellChecker();
		sc.loadValidWords("src/assignment07/a07-valid-words1.txt");
		List<String> result = sc.misspelledWords("src/assignment07/a07-input2.txt");
		assertEquals("[Good,Assignment,8!,,Binary,structures.,,Searches,(if,BST,bad).,,Be,careful,,tricky.]",
				result.toString());
	}

	@Test
	public void emptyValidWordsTest() {
		SpellChecker sc = new BSTSpellChecker();
		// check to see if it throws an error
		sc.misspelledWords("src/assignment07/a07-input2.txt");
	}

	@Test
	public void emptyFileTest() {
		SpellChecker sc = new BSTSpellChecker();
		// check to see if it throws an error
		sc.misspelledWords("src/assignment07/empty.txt");
	}

	@Test
	public void emptyBSTSpellCheckerTest() {
		SpellChecker sc = new BSTSpellChecker();
		sc.misspelledWords("src/assignment07/empty.txt");
	}

	@Test
	public void testAllValidWordsAgainstAllValidWords() {
		SpellChecker sc = new BSTSpellChecker();
		sc.loadValidWords("src/assignment07/a07-valid-words1.txt");
		List<String> result = sc.misspelledWords("src/assignment07/a07-valid-words1.txt");
		//ensure that all words are considered valid
		assertEquals("[]", result.toString());
	}
}