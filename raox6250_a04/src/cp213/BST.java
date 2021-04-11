package cp213;

import java.util.ArrayList;

/**
 * Implements a Binary Search Tree.
 *
 * @author Nausher Rao
 * @version 2021-03-18
 */
public class BST<T extends Comparable<T>> {

	protected int comparisons = 0;
	protected TreeNode<T> root = null;
	protected int size = 0;

	/**
	 * Auxiliary method for {@code equals}. Determines whether two subtrees are
	 * identical in data and height.
	 *
	 * @param source Node of this BST.
	 * @param target Node of that BST.
	 * @return true if source and target are identical in data and height.
	 */
	protected boolean equalsAux(final TreeNode<T> source, final TreeNode<T> target) {
		boolean equal = false;
		if (target == null && source == null)
			equal = true;

		else if (target != null && source != null && target.getValue().compareTo(source.getValue()) == 0
				&& target.getValue().getCount() == source.getValue().getCount()
				&& target.getHeight() == source.getHeight())
			equal = this.equalsAux(source.getLeft(), target.getLeft())
					&& this.equalsAux(source.getRight(), target.getRight());

		else
			equal = false;
		return equal;

	}

	/**
	 * Auxiliary method for {@code insert}. Inserts data into this BST.
	 *
	 * @param node the current node (TreeNode)
	 * @param data data to be inserted into the node
	 * @return the inserted node.
	 */
	protected TreeNode<T> insertAux(TreeNode<T> node, final CountedData<T> data) {
		if (node == null) {
			node = new TreeNode<>(data);
			size++;

		} else {
			int current = node.getValue().compareTo(data);
			if (current == 0)
				node.getValue().incrementCount();

			else if (current > 0)
				node.setLeft(this.insertAux(node.getLeft(), data));

			else
				node.setRight(this.insertAux(node.getRight(), data));
		}

		node.updateHeight();
		return node;
	}

	/**
	 * Auxiliary method for {@code valid}. Determines if a subtree based on node is
	 * a valid subtree.
	 *
	 * @param node The root of the subtree to test for validity.
	 * @return true if the subtree base on node is valid, false otherwise.
	 */
	protected boolean isValidAux(final TreeNode<T> node) {
		boolean valid = false;
		if (node == null)
			valid = true;

		else if (node.getLeft() != null && node.getLeft().getValue().compareTo(node.getValue()) >= 0
				|| node.getRight() != null && node.getRight().getValue().compareTo(node.getValue()) <= 0)
			valid = false;

		else if (Math.max(this.nodeHeight(node.getLeft()), this.nodeHeight(node.getRight())) != node.getHeight() - 1)
			valid = false;

		else
			valid = this.isValidAux(node.getLeft()) && this.isValidAux(node.getRight());

		return valid;

	}

	/**
	 * Returns the height of a given TreeNode.
	 *
	 * @param node The TreeNode to determine the height of.
	 * @return The value of the height attribute of node, 0 if node is null.
	 */
	protected int nodeHeight(final TreeNode<T> node) {
		return node == null ? 0 : node.getHeight();
	
	}

	/**
	 * Determines if this BST contains key.
	 *
	 * @param key The key to search for.
	 * @return true if this BST contains key, false otherwise.
	 */
	public boolean contains(final CountedData<T> key) {
		return retrieve(key) != null;

	}

	/**
	 * Determines whether two BSTs are identical.
	 *
	 * @param target The BST to compare this BST against.
	 * @return true if this BST and that BST contain nodes that match in position,
	 *         value, count, and height, false otherwise.
	 */
	public boolean equals(final BST<T> target) {
		return this.size == target.size ? equalsAux(this.root, target.root) : false;

	}

	/**
	 * Get number of comparisons executed by the {@code retrieve} method.
	 *
	 * @return comparisons
	 */
	public int getComparisons() {
		return this.comparisons;
	}

	/**
	 * Returns the height of the root node of this BST.
	 *
	 * @return height of root node, 0 if the root node is null.
	 */
	public int getHeight() {
		return this.root == null ? 0 : this.root.getHeight();

	}

	/**
	 * Returns the number of nodes in the BST.
	 *
	 * @return number of node in this BST.
	 */
	public int getSize() {
		return this.size;

	}

	/**
	 * Returns an array of copies of CountedData objects in a linked data structure.
	 * The array contents are in data order from smallest to largest.
	 *
	 * Not thread safe as it assumes contents of data structure are not changed by
	 * an external thread during the copy loop. If data elements are added or
	 * removed by an external thread while the data is being copied to the array,
	 * then the declared array size may no longer be valid.
	 *
	 * @return this tree data as an array of data.
	 */
	public ArrayList<CountedData<T>> inOrder() {
		return this.root.inOrder();

	}

	/**
	 * Inserts data into this BST.
	 *
	 * @param data Data to store.
	 */
	public void insert(final CountedData<T> data) {
		this.root = this.insertAux(this.root, data);

	}

	/**
	 * Determines if this BST is empty.
	 *
	 * @return true if this BST is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return this.root == null;

	}

	/**
	 * Determines if this BST is a valid BST; i.e. a node's left child data is
	 * smaller than its data, and its right child data is greater than its data, and
	 * a node's height is equal to the maximum of the heights of its two children
	 * (empty child nodes have a height of 0), plus 1.
	 *
	 * @return true if this BST is a valid BST, false otherwise.
	 */
	public boolean isValid() {
		return this.isValidAux(this.root);

	}

	/**
	 * Returns an array of copies of CountedData objects int a linked data
	 * structure. The array contents are in level order starting from the root
	 * (this) node. Helps determine the structure of the tree.
	 *
	 * Not thread safe as it assumes contents of data structure are not changed by
	 * an external thread during the copy loop. If data elements are added or
	 * removed by an external thread while the data is being copied to the array,
	 * then the declared array size may no longer be valid.
	 *
	 * @return this tree data as an array of data.
	 */
	public ArrayList<CountedData<T>> levelOrder() {
		return this.root.levelOrder();

	}

	/**
	 * Resets the comparison count to 0.
	 */
	public void resetComparisons() {
		this.comparisons = 0;

	}

	/**
	 * Retrieves a copy of data matching key data (key should have data count of 0).
	 * Returning a complete CountedData gives access to the data and count.
	 *
	 * @param key The key to look for.
	 * @return data The complete CountedData that matches key, null otherwise.
	 */
	public CountedData<T> retrieve(final CountedData<T> key) {
		TreeNode<T> curr_node = this.root;
		CountedData<T> result = null;
		int found;

		while (result == null && curr_node != null) {
			found = curr_node.getValue().compareTo(key);
			this.comparisons++;
			if (found == 0)
				result = curr_node.getValue();

			else if (found > 0)
				curr_node = curr_node.getLeft();

			else
				curr_node = curr_node.getRight();
		}

		return result;
	}
}