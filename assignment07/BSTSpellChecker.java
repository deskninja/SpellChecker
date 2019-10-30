package assignment07;

import java.util.ArrayList;

import components.list.List;
import components.list.ListOnArrays;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

public class BSTSpellChecker implements SpellChecker {

	private BinarySearchTreeOfStrings dictionary;
	ArrayList<String> validWords;
	List<String> misspelled;
	ArrayList<String> wordsToCheck;
	
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
		list.add(line);
		file.close();
		return list;
	}
	
	private static ArrayList<String> wordsInFile(String filename){
		ArrayList<String> words = new ArrayList<>();
		ArrayList<String> file = new ArrayList<>();
		file = readFile(filename);
		StringBuilder word = new StringBuilder(); //each number to add to the array goes here
		int index = 0;
		//for each line in the file
		for(String s: file) {
			index = 0;
			//go through each character
			//s = s.toLowerCase();
			while(index < s.length()) {
				//ignore a space and use it as a call to put a new word in words
				if(s.charAt(index) != ' ' && s.charAt(index) != '\n') {
					word.append(s.charAt(index));
				}
				
				if(s.charAt(index) == ' ' || s.charAt(index) == '\n') {
					words.add(word.toString());
					word = new StringBuilder();
				}
				index++;
			}
			if(!word.toString().equals("") && !word.toString().equals("")) {
				words.add(word.toString());
				word = new StringBuilder();
			}
		}
		System.out.println(words.toString());
		return words;
	}
	
	public BSTSpellChecker() {
		clear();
	}
	
	@Override
	public void loadValidWords(String filename) {
		this.validWords = readFile(filename);
		for(String s: validWords)
			dictionary.insert(s);
	}
	
	@Override
	public List<String> misspelledWords(String filename) {
		this.misspelled = new ListOnArrays<>();
		ArrayList<String> words = wordsInFile(filename);
		for(String word: words) {
			if(!this.dictionary.contains(word)) {
				this.misspelled.add(word);
			}
		}
		return this.misspelled;
	}
	
	@Override
	public void clear() {
		this.dictionary = new BinarySearchTreeOfStrings();
		this.validWords = new ArrayList<>();
		this.misspelled = new ListOnArrays<>();
		this.wordsToCheck = new ArrayList<>();
	}

}
