package assignment07;

import static org.junit.Assert.*;

import org.junit.Test;

import components.bintree.BalancedBST1;
import components.list.List;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

public class Timing {

	@Test
	public void heIsGoingForDistanceHeIsGoingForSpeed() {
		SpellChecker sc = new BSTSpellChecker();
		long start = System.nanoTime();
		sc.loadValidWords("src/assignment07/dictionary.txt");
		long stop = System.nanoTime();
		System.out.println(stop - start);
	}
	
	@Test
	public void removeBSTSpellCheckerTime() {
		SpellChecker sc = new BSTSpellChecker();
		long start = System.nanoTime();
		sc.misspelledWords("src/assignment07/bible.txt");
		long stop = System.nanoTime();
		System.out.println(stop - start);
	}

	@Test
	public void insertOtherSpellChecker() {
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
	}
}
