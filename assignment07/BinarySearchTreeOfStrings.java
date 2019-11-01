package assignment07;


import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A binary search tree implementation from scratch.
 * 
 * @author Joshua Wells, Jonathan Oliveros
 *
 */
public class BinarySearchTreeOfStrings {

	/**
	 * Node class contains data leaves
	 * 
	 * @author Joshua Wells, Jonathan Oliveros
	 * 
	 */
	private class Node {
		private String data;
		private Node left, right;
	}

	private boolean contains(String x, Node head) {
		if (head.data.compareTo(x) == 0)
			return true;
		if (head.data.compareTo(x) > 0 && head.left != null)
			return contains(x, head.left);
		if (head.data.compareTo(x) < 0 && head.right != null)
			return contains(x, head.right);
		return false;
	}

	/**
	 * outputs the data of the tree in sorted order, lowest value to hightest value
	 * 
	 * @param head Node that is the head of the tree to be returned
	 */
	private String inOrder(Node head) {
		if (head == null)
			return "";
		StringBuilder treeData = new StringBuilder();
		if (treeData.length() != 0)
			treeData.append(" ");
		treeData.append(inOrder(head.left));
		if (head.left != null)
			treeData.append(" ");
		treeData.append(head.data);
		if (head.right != null)
			treeData.append(" ");
		treeData.append(inOrder(head.right));
		return treeData.toString();
	}

	/**
	 * recursive function that traverses through the tree to the correct leaf where node n is sorted
	 * then adds node n to that place
	 * 
	 * @param n Node to be inserted
	 * @param thisRoot Node root of the tree being inserted to
	 * @modifies this tree
	 */
	private void insert(Node n, Node thisRoot) {
		// find leaf/ base case
		boolean right = false; // if it is left of the node false, if right, true
		if (thisRoot.data.compareTo(n.data) < 0)
			right = true;
		if (!right && thisRoot.left == null) {
			thisRoot.left = n;
			this.size++;
		}

		else if (right && thisRoot.right == null) {
			thisRoot.right = n;
			this.size++;
		}

		else {
			if (!right)
				insert(n, thisRoot.left);
			if (right)
				insert(n, thisRoot.right);
		}
	}

	/**
	 * recursive function that traverses through the tree until it finds a Node with data x
	 * it deletes this node and takes the far right leaf of the left branch of that node (if ther is one)
	 * and places that node in place of the node to be removed
	 * 
	 * @param x String the data to be removed
	 * @param head Node the head of the tree where x is being searched for
	 * @modifies this tree
	 */
	private void remove(String x, Node head) {
		if (head.left != null && head.left.data.compareTo(x) <= 0) {
			if (head.left.data.equals(x)) {
				removeElement(head.left, head, false);
				return;
			}
			remove(x, head.left);
			return;
		}
		if (head.right != null && head.right.data.compareTo(x) >= 0) {
			if (head.right.data.equals(x)) {
				removeElement(head.right, head, true);
				return;
			}
			remove(x, head.right);
			return;
		}
		// Shouldn't ever reach this point, so throw an error in case it does
		throw new NoSuchElementException();
	}

	/**
	 * This method removes this.root and replaces it with the best Node in the tree to retain binary
	 * sorting and the smallest height
	 */
	private void removeRoot() {
		if (this.root.left != null) {
			if (this.root.right != null) {
				Node hold = this.root.left;
				if (hold.right != null) {
					Node tail = this.root.left;
					hold = hold.right;
					while (hold.right != null) {
						hold = hold.right;
						tail = tail.right;
					}
					this.root.data = hold.data;
					tail.right = null;
				} else {
					this.root.data = hold.data;
					this.root.left = null;
				}
			}
		} else {
			if (this.root.right != null) {
				this.root = this.root.right;
			} else {
				this.root.data = null;
			}
		}
		this.size--;
	}

	/**
	 * This method deciedes what to do when a node is being removed
	 * If the node has a right and left Node below it, it choses the left path
	 * and the far right node of that path to replace it. If there is no path below 
	 * it is replaced by the left Node, if there is no left Node then it is replaced by 
	 * the right node, and finally if there is no left or right node below it, it is replaced
	 * by nothing and simply removed.
	 * 
	 * @param removeNode Node to be removed
	 * @param head Node the parent Node of removeNode
	 * @param right boolean tells if removeNode is the left or right Node of head
	 * @modifies this tree
	 */
	private void removeElement(Node removeNode, Node head, boolean right) {
		if (removeNode.left != null) {
			if (removeNode.right != null) {
				Node hold = removeNode.left;
				if (hold.right != null) {
					Node tail = removeNode.left; //the parent Node of hold
					hold = hold.right;
					while (hold.right != null) {
						hold = hold.right;
						tail = tail.right;
					}
					removeNode.data = hold.data;
					tail.right = null;
				} else {
					removeNode.data = hold.data;
					removeNode.left = null;
				}
			}
			if (right) {
				head.right = removeNode.left;
			} else {
				head.left = removeNode.left;
			}
		} 
		//if there is no left child node of removeNode
		else {
			if (removeNode.right != null) {
				if (right) {
					head.right = removeNode.right;
				} else {
					head.left = removeNode.right;
				}
			} 
			//if there is no left or right child Node of removeNode
			else {
				if (right) {
					head.right = null;
				} else {
					head.left = null;
				}
			}
		}
		//decrement the size of this tree by one
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
	 * Removes all the nodes in this tree by creating 
	 * new instances of the root and data of the root
	 * size is also set to 0
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
		if (this.size() == 0) {
			root = n;
			this.size++;
		}

		// find leaf/ base case with a recursive insert
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
		if (this.size() == 0) {
			throw new NoSuchElementException();
		}
		//if we only have {@code root} in this tree
		if (this.size() == 1) {
			if (this.root.data.equals(x)) {
				this.clear();
				return;
			}
			throw new NoSuchElementException();
		}
		//check to see if {@code x} is in {@code root}
		if (this.root.data.equals(x)) {
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
		return this.size; 
	}

	/**
	 * Reports whether this tree contains {@code x}.
	 * 
	 * @param x element to search
	 * @return true iff this contains x
	 */
	public boolean contains(String x) {
		//check the root
		if (this.root.data.compareTo(x) == 0)
			return true;
		//recursively travel the tree
		return contains(x, this.root);
	}

	/**
	 * Returns the root of this tree.
	 * 
	 * @return root of this tree
	 * @requires this is not empty
	 */
	public String root() {
		if (this.size() == 0)
			throw new NoSuchElementException();
		//if there is data in root (size > 0)
		return root.data;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("[");
		Scanner s = new Scanner(this.inOrder(this.root)); //scanner for the data of this tree in order of smallest to larges
		if (s.hasNext())
			result.append(s.next());
		while (s.hasNext()) {
			result.append(",");
			result.append(s.next());
		}
		s.close();
		result.append("]");
		return result.toString();
	}
}