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
    assertEquals(
        "[Good,Assignment,8!,,Binary,structures.,,Searches,(if,BST,bad).,,Be,careful,,tricky.]",
        result.toString());
  }

}
