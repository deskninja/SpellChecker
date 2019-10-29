package assignment07;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
	
	private void balanceTree(Node thisRoot) {
		String[] thisData = new String[this.size()];
		String[] nullData = new String[this.size() + 1];
		String nextItem = inOrder(this.root);
		nullData = nextItem.split(",");
		int index = 0;
		for(String s: nullData) {
			System.out.println(s.length());
			if(!s.equals(null)) {
				thisData[index] = s;
				index++;
			}
		}
		System.out.println(thisData.toString());
		clear();
		insertBalancedData(thisData);
		
	}
	
	private void insertBalancedData(String[] thisData) {
		if(thisData.length <= 2) {
			if(thisData.length == 2) {
				this.insert(thisData[0]);
				this.insert(thisData[1]);
			}
			if(thisData.length == 1) {
				this.insert(thisData[0]);
				return;
			}
			return;
		}
		
		this.insert(thisData[thisData.length/2]);
		String[] leftData = partOfArray(thisData, thisData.length / 2 - 1, 0);
		insertBalancedData(leftData);
		String[] rightData = partOfArray(thisData, thisData.length - (thisData.length / 2 + 1), thisData.length / 2 + 1);
		insertBalancedData(rightData);
	}
	
	private String[] partOfArray(String[] thisData, int length, int start) {
		String[] newData = new String[length];
		for(int i = start; i < length; i++) {
			newData[i] = thisData[i];
		}
		return newData;
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
	
	private void insert(Node n, Node thisRoot) {
		// find leaf/ base case
		boolean right = false; //if it is left of the node false, if right, true
		if(thisRoot.data.compareTo(n.data) < 0)
			right= true;
		if(!right && thisRoot.left == null) {
			thisRoot.left = n;
			this.size++;
		}
		
		else if(right && thisRoot.right == null) {
			thisRoot.right = n;
			this.size++;
		}
			
		else {
			if(!right) 
				insert(n, thisRoot.left);
			if(right)
				insert(n, thisRoot.right);	
		}
	}
	
	private void leftRight(String x, Node thisRoot, boolean right, boolean leftNull, boolean rightNull) {
		if(!right && !leftNull) {
			if(thisRoot.left.data.compareTo(x) == 0) {
				Node hold = thisRoot.left;
				
				if(hold.left != null){
					hold = farRightNode(hold.left);
				}
				else if(hold.right != null) {
					Node newRoot = new Node();
					newRoot = thisRoot.right;
					thisRoot = newRoot;
				}
				
				else {
					thisRoot.left = null;
				}
				this.size--;
				//this.balanceTree(this.root);
				return;
			}
			remove(x, thisRoot.left);
		}
		
		if(right && !rightNull) {
			if(thisRoot.right.data.compareTo(x) == 0) {
				Node hold = thisRoot.right;
				
				if(hold.left != null){
					hold = farRightNode(hold.left);
				}
				else if(hold.right != null) {
					Node newRoot = new Node();
					newRoot = thisRoot.right;
					thisRoot = newRoot;
				}
				
				else {
					thisRoot.right = null;
				}
				this.size--;
				//this.balanceTree(this.root);
				return;
			}
			remove(x, thisRoot.right);
		}
		
		throw  new  NoSuchElementException ();
		
		
	}

	private void remove(String x, Node thisRoot) {
		boolean right = false; //if it is left of the node false, if right, true
		boolean leftNull = thisRoot.left == null;
		boolean rightNull = thisRoot.right == null;
		if(thisRoot.data.compareTo(x) <= 0) {
			right= true;
			if(thisRoot.data.compareTo(x) == 0) {
				if(!leftNull) {
					thisRoot.data = farRightNode(thisRoot.left).data;
					this.size--;
					this.balanceTree(this.root);
					return;
				}
				else {
					thisRoot = thisRoot.right;
					this.size--;
					return;
				}
			}
		}
		leftRight(x, thisRoot, right, leftNull, rightNull);
	}
	
	private Node farRightNode(Node thisRoot) {
		//TODO: create a follower node so you can set this node to null (first do the left node in a separate method)
		if(thisRoot.right == null) {
			Node hold = new Node();
			hold.data = thisRoot.data;
			thisRoot.data = null;
			return hold;
		}
		return farRightNode(thisRoot.right);
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
	}

	/**
	 * Removes the element {@code x} from this tree.
	 * 
	 * @param x element to be removed
	 * @modifies this tree
	 * @requires {@code x} is in {@code this}
	 */
	public void remove(String x) {
		if(this.size() == 0) {
			throw  new  NoSuchElementException ();
		}
		if(this.size() == 1) {
			this.clear();
			return;
		}
		remove(x, this.root);
		//balanceTree(this.root);
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
		if(this.size() == 0)
			throw  new  NoSuchElementException ();
		
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