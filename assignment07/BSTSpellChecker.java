package assignment07;

import java.util.ArrayList;

import components.list.List;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

public class BSTSpellChecker implements SpellChecker {

	private BinarySearchTreeOfStrings dictionary;
	
	/**
	 * Pulls the words from the file and puts them into an arrayList.
	 * @param filename being read.
	 * @return list of words.
	 */
	private static ArrayList<String> readFile(String filename){
		SimpleReader file = new SimpleReader1L(filename); 
		ArrayList<String> list = new ArrayList<>();
		String line = file.nextLine();
		while (!file.atEOS()) {  
			list.add(line);
			line = file.nextLine();
		}
		file.close();
		return list;
	}
	
	public BSTSpellChecker() {
		clear();
	}
	
	@Override
	public void loadValidWords(String filename) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<String> misspelledWords(String filename) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void clear() {
		dictionary = new BinarySearchTreeOfStrings();
	}

}
