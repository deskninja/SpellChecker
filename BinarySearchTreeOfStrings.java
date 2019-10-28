package assignment07;

import java.util.Comparator;

/**
 * A binary search tree implementation from scratch.
 * 
 * @author Joshua Wells, Jonathan Oliveros
 *
 */
public class BinarySearchTreeOfStrings {
	// TODO declare Node, private fields, etc.

	/**
	 *  Node class to be used as roots and leaves
	 *  
	 *  @author Joshua Wells, Jonathan Oliveros 
	 *  
	 */
	private class Node {
		private String data;
		private Node left, right;
	}

	
	/**
	 * outputs the data of the tree in-order
	 * @param n
	 */
	private String inOrder(Node n) {
		if(n == null) {
			return "";
		}
		String s = new String();
		s += inOrder(n.left);
		s += n.data;
		s += inOrder(n.right);
		return s;
	}
	
	// Instance variables
	private Node root;
	private int size;

	/**
	 * No argument constructor.
	 */
	public BinarySearchTreeOfStrings() {
		clear();
	}

	/**
	 * Removes all the nodes in this tree.
	 * 
	 * @modifies this tree
	 */
	public void clear() {
		root = new Node();
		root.data = "";
		this.size = 0;
	}

	/**
	 * Inserts the element {@code x} at the appropriate position in this tree.
	 * 
	 * @param x element to be inserted
	 * @modifies this tree
	 */
	public void insert(String x) {
		
		Comparator<String> order = (s1, s2) -> {
			return s1.compareTo(s2);
		}; // create the comparator (lambda)
		
		// find leaf/ base case
		if (root.left == null && root.right == null) {
			if (order.compare(root.data, x) < 0) {
				root.left.data = x;
			} else {
				root.right.data = x;
			}
		}
		// iterative step
		else {

		}
		// TODO implement this method
	}

	/**
	 * Removes the element {@code x} from this tree.
	 * 
	 * @param x element to be removed
	 * @modifies this tree
	 * @requires {@code x} is in {@code this}
	 */
	public void remove(String x) {
		// TODO implement this method
	}

	/**
	 * Reports the number of nodes in this tree.
	 * 
	 * @return size of {@code this}
	 */
	public int size() {
		return this.size; // TODO implement this method
	}

	/**
	 * Reports whether this tree contains {@code x}.
	 * 
	 * @param x element to search
	 * @return true iff this contains x
	 */
	public boolean contains(String x) {
		return false; // TODO implement this method
	}

	/**
	 * Returns the root of this tree.
	 * 
	 * @return root of this tree
	 * @requires this is not empty
	 */
	public String root() {
		return root.data; 
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("[");
		result.append(inOrder(root));
		result.append("]");
		return result.toString(); 
	}

	/*
	 * Helper methods
	 */
	// TODO create private helper methods if required
}
