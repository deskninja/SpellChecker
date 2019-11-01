package assignment07;

import java.util.Random;

import org.junit.Test;

import components.bintree.BalancedBST1;
import components.list.List;
import components.list.ListOnArrays;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

/**
 * @author Jonathan Oliveros, Joshua Wells
 *
 */
public class Timing {

	@Test
	public void timeTest() {
		List<String> dictionary = ordered("src/assignment07/dictionary.txt");
		List<String> check = ordered("src/assignment07/bible.txt");
		BinarySearchTreeOfStrings st = new BinarySearchTreeOfStrings();
		BalancedBST1<String> bst = new BalancedBST1<>();

		// test insert ordered dictionary
		System.out.println(insert(dictionary, st));
		st.clear();
		// test insert random dictionary
		System.out.println(randomTimeInsert(50, st, dictionary));
		// test contains
		System.out.println(contains(dictionary, check, st));
		// test random contains
		System.out.println(randomTimeContains(50, st, dictionary, check));
		// BalancedBST1 tests
		// test BalancedBST1 insert
		System.out.println(insert(dictionary, bst));
		// test BalancedBST1 contains
		System.out.println(contains(dictionary, check, bst));
		// test BalancedBST1 insertRandom
		System.out.println(randomTimeInsert(50, bst, dictionary));
		// test BalancedBST1 containsRandom
		System.out.println(randomTimeContains(50, bst, dictionary, check));
	}

	/**
	 * This method times a BinarySearchTreeOfStrings object and how long it takes to determine if it contains a word
	 * 
	 * @param iterate int number of times this test is repeated
	 * @param st BinarySearchTreeOfStrings
	 * @param dictionary
	 * @param check
	 * @return
	 */
	private long randomTimeContains(int iterate, BinarySearchTreeOfStrings st, List<String> dictionary,
			List<String> check) {
		long time = 0;
		for (int i = 0; i < iterate; i++) {
			dictionary = randomOrder("src/assignment07/dictionary.txt");
			time += contains(dictionary, check, st);
			st.clear();
		}
		return time / iterate;
	}

	/**
	 * @param iterate
	 * @param st
	 * @param dictionary
	 * @param check
	 * @return
	 */
	private long randomTimeContains(int iterate, BalancedBST1<String> st, List<String> dictionary, List<String> check) {
		long time = 0;
		for (int i = 0; i < iterate; i++) {
			dictionary = randomOrder("src/assignment07/dictionary.txt");
			time += contains(dictionary, check, st);
			st.clear();
		}
		return time / iterate;
	}

	/**
	 * @param iterate
	 * @param st
	 * @param dictionary
	 * @return
	 */
	private long randomTimeInsert(int iterate, BalancedBST1<String> st, List<String> dictionary) {
		long time = 0;
		for (int i = 0; i < iterate; i++) {
			dictionary = randomOrder("src/assignment07/dictionary.txt");
			time += insert(dictionary, st);
			st.clear();
		}
		return time / iterate;
	}

	/**
	 * @param iterate
	 * @param st
	 * @param dictionary
	 * @return
	 */
	private long randomTimeInsert(int iterate, BinarySearchTreeOfStrings st, List<String> dictionary) {
		long time = 0;
		for (int i = 0; i < iterate; i++) {
			dictionary = randomOrder("src/assignment07/dictionary.txt");
			time += insert(dictionary, st);
			st.clear();
		}
		return time / iterate;
	}

	/**
	 * @param dictionary
	 * @param st
	 * @return
	 */
	private long insert(List<String> dictionary, BinarySearchTreeOfStrings st) {
		// insert the valid words to sc
		long start = System.nanoTime();
		for (String s : dictionary) {
			st.insert(s);
		}
		long stop = System.nanoTime();
		return stop - start;
	}

	/**
	 * @param dictionary
	 * @param check
	 * @param st
	 * @return
	 */
	private long contains(List<String> dictionary, List<String> check, BinarySearchTreeOfStrings st) {
		insert(dictionary, st);
		long start = System.nanoTime();
		for (String s : check) {
			st.contains(s);
		}
		long stop = System.nanoTime();
		return stop - start;
	}

	/**
	 * @param dictionary
	 * @param st
	 * @return
	 */
	private long insert(List<String> dictionary, BalancedBST1<String> st) {
		// insert the valid words to sc
		long start = System.nanoTime();
		for (String s : dictionary) {
			st.insert(s);
		}
		long stop = System.nanoTime();
		return stop - start;
	}

	/**
	 * @param dictionary
	 * @param check
	 * @param st
	 * @return
	 */
	private long contains(List<String> dictionary, List<String> check, BalancedBST1<String> st) {
		insert(dictionary, st);
		long start = System.nanoTime();
		for (String s : check) {
			st.contains(s);
		}
		long stop = System.nanoTime();
		return stop - start;
	}

	/**
	 * @param filename
	 * @return
	 */
	private List<String> ordered(String filename) {
		SimpleReader file = new SimpleReader1L(filename);
		List<String> words = new ListOnArrays<String>();
		while (!file.atEOS()) {
			String line = file.nextLine();
			for (String str : line.split(" ")) {
				words.add(str);
			}
		}
		file.close();
		return words;
	}

	/**
	 * @param filename
	 * @return
	 */
	private List<String> randomOrder(String filename) {
		SimpleReader s = new SimpleReader1L(filename);
		List<String> validWords = new ListOnArrays<String>();
		while (!s.atEOS()) {
			String line = s.nextLine();
			for (String str : line.split(" ")) {
				validWords.add(str);
			}
		}
		s.close();
		List<String> randomWords = new ListOnArrays<String>();
		Random r = new Random();
		while (validWords.size() > 0) {
			String nextItem = validWords.remove(r.nextInt(validWords.size()));
			randomWords.add(nextItem);
		}
		return randomWords;
	}
}