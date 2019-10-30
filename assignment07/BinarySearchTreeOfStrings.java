package assignment07;

import java.util.ArrayList;
import java.util.Currency;
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
	
	private boolean contains(String x, Node head) {
		if(head.data.compareTo(x) == 0)
			return true;
		if(head.data.compareTo(x) > 0 && head.left != null)
			return contains(x, head.left);
		if(head.data.compareTo(x) < 0 && head.right != null)
			return contains(x, head.right);
		return false;
	}
	
	/**
	 * outputs the data of the tree in-order
	 * @param n
	 */
	private String inOrder(Node head) {
		if(head == null)
			return "";
		StringBuilder treeData = new StringBuilder();
		if(treeData.length() != 0)
			treeData.append(" ");
		treeData.append(inOrder(head.left));
		if(head.left != null)
			treeData.append(" ");
		treeData.append(head.data);
		if(head.right != null)
			treeData.append(" ");
		treeData.append(inOrder(head.right));
		return treeData.toString();
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

	private void remove(String x, Node head) {
		if(head.left != null && head.left.data.compareTo(x) <= 0) {
			if(head.left.data.equals(x)) {
				removeElement(head.left, head, false);
				return;
			}
			remove(x, head.left);
			return;
		}
		if(head.right != null && head.right.data.compareTo(x) >= 0) {
			if(head.right.data.equals(x)) {
				removeElement(head.right, head, true);
				return;
			}
			remove(x, head.right);
			return;
		}
		throw new NoSuchElementException();
	}
	
	private void removeRoot() {
		if(this.root.left != null) {
			if(this.root.right != null) {
				Node hold = this.root.left;
				if(hold.right != null) {
					Node tail = this.root.left;
					hold = hold.right;
					while(hold.right != null) {
						hold = hold.right;
						tail = tail.right;
					}
					this.root.data = hold.data;
					tail.right = null;
				}
				else {
					this.root.data = hold.data;
					this.root.left = null;
				}
			}
		}
		else {
			if(this.root.right != null) {
				this.root = this.root.right;
			}
			else {
				this.root.data = null;
			}
		}
		this.size--;
	}
	
	private void removeElement(Node removeNode, Node head, boolean right) {
		if(removeNode.left != null) {
			if(removeNode.right != null) {
				Node hold = removeNode.left;
				if(hold.right != null) {
					Node tail = removeNode.left;
					hold = hold.right;
					while(hold.right != null) {
						hold = hold.right;
						tail = tail.right;
					}
					removeNode.data = hold.data;
					tail.right = null;
				}
				else {
					removeNode.data = hold.data;
					removeNode.left = null;
				}
			}
			if(right) {
				head.right = removeNode.left;
			}
			else {
				head.left = removeNode.left;
			}
		}
		else {
			if(removeNode.right != null) {
				if(right) {
					head.right = removeNode.right;
				}
				else {
					head.left = removeNode.right;
				}
			}
			else {
				if(right) {
					head.right = null;
				}
				else {
					head.left = null;
				}
			}
		}
		this.size--;
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
				if(this.root.data.equals(x)) {
					this.clear();
					return;
				}
				throw new NoSuchElementException();
		}
		if(this.root.data.equals(x)) {
			removeRoot();
			return;
		}
		remove(x, this.root);
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
		if(this.root.data.compareTo(x) == 0)
			return true;
		return contains(x, this.root);
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
		Scanner s = new Scanner(this.inOrder(this.root));
		if(s.hasNext())
			result.append(s.next());
		while(s.hasNext()) {
			result.append(",");
			result.append(s.next());
		}
		s.close();
		result.append("]");
		return result.toString(); 
	}
}