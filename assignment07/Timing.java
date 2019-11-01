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
	 * This method times a BinarySearchTreeOfStrings object and how long it takes to determine if it contains the words in check
	 * 
	 * @param iterate int number of times this test is repeated
	 * @param st BinarySearchTreeOfStrings object
	 * @param dictionary List<String> of valid words
	 * @param check List<String> list to check against the valid words in dictionary
	 * @return long time of all the contains opperations / iterate
	 * @modifes st
	 */
	private long randomTimeContains(int iterate, BinarySearchTreeOfStrings st, List<String> dictionary,
			List<String> check) {
		long time = 0;
		for (int i = 0; i < iterate; i++) {
			//create a new random tree
			dictionary = randomOrder("src/assignment07/dictionary.txt");
			//add the time it took to go through check
			time += contains(dictionary, check, st);
			st.clear();
		}
		//return the average time
		return time / iterate;
	}

	/**
	 * This method times a BalancedBST1<String> object and how long it takes to determine if it contains a the words in check
	 * 
	 * @param iterate int number of times this test is repeated
	 * @param st BalancedBST1<String> object
	 * @param dictionary List<String> of valid words
	 * @param check List<String> list to check against the valid words in dictionary
	 * @return long time of all the contains opperations / iterate
	 * @modifies st
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
	 * This method takes st and inserts a randomly sorted List of Strings into it
	 * and then returns the time it took to insert all of them / the number of iterations 
	 * of inserts
	 * 
	 * @param iterate int number of times this test is repeated
	 * @param st BalancedBST1<String> object
	 * @param dictionary List<String> of valid words
	 * @return long time of all the contains opperations / iterate
	 * @modifies st
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
	 * This method takes st and inserts a randomly sorted List of Strings into it
	 * and then returns the time it took to insert all of them / the number of iterations 
	 * of inserts
	 * 
	 * @param iterate int number of times this test is repeated
	 * @param st BinarySearchTreeOfStrings object
	 * @param dictionary List<String> of valid words
	 * @return long time of all the contains opperations / iterate
	 * @modifies st
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
	 * This method times how long it takes to insert all elements in dictionary into st
	 * 
	 * @param st BinarySearchTreeOfStrings object
	 * @param dictionary List<String> of valid words
	 * @return long time it took to insert
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
	 * this method times how long it takes to run contains on all items in check aganst a tree made 
	 * from dictionary
	 * 
	 * @param st BinarySearchTreeOfStrings object
	 * @param dictionary List<String> of valid words
	 * @return long time it took to insert
	 * @return long the time it took
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
	 * This method times how long it takes to insert all elements in dictionary into st
	 * 
	 * @param st BalancedBST1<String> object
	 * @param dictionary List<String> of valid words
	 * @return long time it took to insert
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
	 * this method times how long it takes to run contains on all items in check aganst a tree made 
	 * from dictionary
	 * 
	 * @param st BalancedBST1<String> object
	 * @param dictionary List<String> of valid words
	 * @return long time it took to insert
	 * @return long the time it took
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
	 * Reads the file from filename and returns a List<String> of all the separate words
	 * separated by " "
	 * 
	 * @param filename the file to be read
	 * @return List<String> words in the file in order
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
	 * Reads the file from filename and returns a List<String> of all the separate words
	 * separated by " "
	 * 
	 * @param filename the file to be read
	 * @return List<String> words in the file in random order
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
		//randomly choose which element to insert until validWords is empty
		while (validWords.size() > 0) {
			String nextItem = validWords.remove(r.nextInt(validWords.size()));
			randomWords.add(nextItem);
		}
		return randomWords;
	}
}