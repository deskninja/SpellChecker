package assignment07;

import static org.junit.Assert.*;

import org.junit.Test;

import components.list.List;

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
	public void emptyValidWords() {
		SpellChecker sc = new BSTSpellChecker();
		// check to see if it throws an error
		sc.misspelledWords("src/assignment07/a07-input2.txt");
	}

	public void emptyFile() {
		SpellChecker sc = new BSTSpellChecker();
		// check to see if it throws an error
		sc.misspelledWords("src/assignment07/empty.txt");
	}

	public void empty() {
		SpellChecker sc = new BSTSpellChecker();
		sc.misspelledWords("src/assignment07/empty.txt");
	}

	@Test
	public void testAllValidWords() {
		SpellChecker sc = new BSTSpellChecker();
		sc.loadValidWords("src/assignment07/a07-valid-words1.txt");
		List<String> result = sc.misspelledWords("src/assignment07/a07-valid-words1.txt");
		assertEquals("[]", result.toString());
	}
}
