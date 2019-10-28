package assignment07;

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
		StringBuilder s = new StringBuilder();
		if(s.length() != 0)
			s.append(",");
		
		s.append(inOrder(n.left));
		
		if(n.left != null)
			s.append(",");
		
		s.append(n.data);
		if(n.right != null)
			s.append(",");
		
		s.append(inOrder(n.right));
		
		return s.toString();
	}
	
	public String preOrder(Node n) {
		if(n == null) {
			return "";
		}
		StringBuilder s = new StringBuilder();
		s.append(n.data);
		s.append(preOrder(n.left));
		s.append(preOrder(n.right));
		return s.toString();	
	}
	
	private void insert(Node n, Node thisRoot) {
		// find leaf/ base case
		boolean right = false; //if it is left of the node false, if right, true
		if(thisRoot.data.compareTo(n.data) > 0)
			right= true;
		if(!right && thisRoot.left == null) {
			thisRoot.right = n;
			this.size++;
		}
		
		else if(right && thisRoot.right == null) {
			thisRoot.left = n;
			this.size++;
		}
			
		else {
			if(!right) 
				insert(n, thisRoot.left);
			if(right)
				insert(n, thisRoot.right);	
		}
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
		Node n = new Node();
		n.data = x;
		if(this.size() == 0) {
			root = n;
			this.size++;
		}

		// find leaf/ base case
		else {
			insert(n, root);
		}
//			if (root.left.data == null && root.data.compareTo(x) < 0) {
//			root.left.data = x;		
//		}
//		else if (root.right.data == null && root.data.compareTo(x) > 0)
//			root.right.data = x;
//		else {
//			
//		}
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
		System.out.println(result);
		return result.toString(); 
	}

	/*
	 * Helper methods
	 */
	// TODO create private helper methods if required
}