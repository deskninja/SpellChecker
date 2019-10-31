package assignment07;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import components.bintree.BalancedBST1;
import components.list.List;
import components.list.ListOnArrays;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

public class Timing {

	@Test
	public void heIsGoingForDistanceHeIsGoingForSpeed() {
		SpellChecker sc = new BSTSpellChecker();
		long start = System.nanoTime();
		sc.loadValidWords("src/assignment07/dictionary.txt");
		long stop = System.nanoTime();
		System.out.println("BSTInsert " + (stop - start));
	}
	
	@Test
	public void removeBSTSpellCheckerTimeOrderedInsert() {
		SpellChecker sc = new BSTSpellChecker();
		sc.loadValidWords("src/assignment07/dictionary.txt");
		long start = System.nanoTime();
		sc.misspelledWords("src/assignment07/bible.txt");
		long stop = System.nanoTime();
		System.out.println("BSTContains " + (stop - start));
	}
	
	@Test
	public void removeBSTSpellCheckerTimeRandomInsert() {
		SpellChecker sc = new BSTSpellChecker();
		sc.loadValidWords("src/assignment07/dictionary.txt");
		long start = System.nanoTime();
		sc.misspelledWords("src/assignment07/bible.txt");
		long stop = System.nanoTime();
		System.out.println("BSTContainsRandomInsert " + (stop - start));
	}

	@Test
	public void insertOtherSpellChecker() {
		BalancedBST1<String> bc = new BalancedBST1<>();
		SimpleReader s = new SimpleReader1L("src/assignment07/dictionary.txt");
		//insert the valid words to bc
		long start = System.nanoTime();
		while (!s.atEOS()) {
			String line = s.nextLine();
			for (String str : line.split(" ")) {
				bc.insert(str);
			}
		}	
		long stop = System.nanoTime();
		System.out.println("OtherInsert " + (stop - start));
		s.close();
	}
	
	@Test
	public void containsOtherSpellChecker() {
		BalancedBST1<String> bc = new BalancedBST1<>();
		SimpleReader s = new SimpleReader1L("src/assignment07/dictionary.txt");
		//insert the valid words to bc
		while (!s.atEOS()) {
			String line = s.nextLine();
			for (String str : line.split(" ")) {
				bc.insert(str);
			}
		}	
		s.close();
		
		long start = System.nanoTime();
		List<String> misspelledWords = new ListOnArrays<String>();
		SimpleReader bible = new SimpleReader1L("src/assignment07/bible.txt");
		while (!bible.atEOS()) {
			String line = bible.nextLine();
			for (String str : line.split(" ")) {
				if (!bc.contains(str)) {
					misspelledWords.add(str);
				}
			}
		}
		long stop = System.nanoTime();
		System.out.println("OtherContains " + (stop - start));
		bible.close();
	}
	
	@Test
	public void randomOrder() {
		SimpleReader s = new SimpleReader1L("src/assignment07/dictionary.txt");
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
		while(validWords.size() > 0) {
			String nextItem = validWords.remove(r.nextInt(validWords.size()));
			randomWords.add(nextItem);
		}
		for(String line: randomWords) {
			System.out.println(line);
		}
	}
}
