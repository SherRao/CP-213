package cp213;

import java.util.ArrayList;
public class BST<T extends Comparable<T>> {
	protected int comparisons = 0; // Count of comparisons performed by tree.

	// Attributes.
	protected TreeNode<T> root = null; // Root node of the tree.
	protected int size = 0; // Number of elements in the tree.

	/**
	 * Auxiliary method for {@code equals}. Determines whether two subtrees are
	 * identical in data and height.
	 *
	 * @param source Node of this BST.
	 * @param target Node of that BST.
	 * @return true if source and target are identical in data and height.
	 */
	protected boolean equalsAux(final TreeNode<T> source, final TreeNode<T> target) {

		// need to recurse through both of the trees at the same time to
		// compare nodes concurrently
		boolean equal = false;

		// end of the tree was met, therefore they must be equal
		if (target == null && source == null)
			equal = true;

		// recursive call if all of the values, count, and height is matched
		else if (target != null && source != null && target.getValue().compareTo(source.getValue()) == 0 && target.getValue().getCount() == source.getValue().getCount()
				&& target.getHeight() == source.getHeight())
			equal = this.equalsAux(source.getLeft(), target.getLeft()) && this.equalsAux(source.getRight(), target.getRight());

		// if the other two conditions are not met then we know that they are not equal.
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


		// if the node is null then this is where we insert
		if (node == null) {
			// insert the new node and update the size
			this.size++;
			TreeNode<T> temp = new TreeNode<>(data);
			node = temp;

		} else {


			// if curr node is greater than key then we want to go left, right otherwise
			int location = node.getValue().compareTo(data);

			// the data already exists
			if (location == 0)
				node.getValue().incrementCount(); // increment the count of that value

			// recurse through the tree to find the location to insert
			else if (location < 0) // data is bigger than current node
				node.setRight(this.insertAux(node.getRight(), data));
			else // data is smaller than current node
				node.setLeft(this.insertAux(node.getLeft(), data));

		}

		node.updateHeight(); // update the height of the tree once inserted
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

		// need to compare the height of each node
		// need to compare the values of each node
		// math max is equivalent to a < b ? x : y;
		boolean valid_tree = false;

		// base case, if the node is null that means its met the bottom of the subtrees
		if (node == null)
			valid_tree = true;

		// this will test against the values in the subtree
		else if (node.getRight() != null && node.getRight().getValue().compareTo(node.getValue()) <= 0 || node.getLeft() != null && node.getLeft().getValue().compareTo(node.getValue()) >= 0)
			valid_tree = false;

		// this will test against height
		else if (Math.max(this.nodeHeight(node.getRight()), this.nodeHeight(node.getLeft())) != node.getHeight() - 1)
			valid_tree = false;

		// recursive call to more nodes in the tree
		else
			valid_tree = this.isValidAux(node.getRight()) && this.isValidAux(node.getLeft());

		return valid_tree;
	}

	/**
	 * Returns the height of a given TreeNode.
	 *
	 * @param node The TreeNode to determine the height of.
	 * @return The value of the height attribute of node, 0 if node is null.
	 */
	protected int nodeHeight(final TreeNode<T> node) {
		int height = 0;

		if (node != null) {
			height = node.getHeight();
		}
		return height;
	}

	/**
	 * Determines if this BST contains key.
	 *
	 * @param key The key to search for.
	 * @return true if this BST contains key, false otherwise.
	 */
	public boolean contains(final CountedData<T> key) {
		boolean found = false;

		CountedData<T> temp = retrieve(key); // we already made a function to do the legwork

		if (temp != null) // if the node is retrieved then it is found
			found = true;

		return found;
	}

	/**
	 * Determines whether two BSTs are identical.
	 *
	 * @param target The BST to compare this BST against.
	 * @return true if this BST and that BST contain nodes that match in position,
	 *         value, count, and height, false otherwise.
	 */
	public boolean equals(final BST<T> target) {
		boolean isEqual = false;

		if (this.size == target.size) {
			isEqual = this.equalsAux(this.root, target.root);
		}
		return isEqual;
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
		int height = 0;

		if (this.root != null) {
			height = this.root.getHeight();
		}
		return height;
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
		// call the aux with the root and the data we want to insert
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
		return;
	}

	/**
	 * Retrieves a copy of data matching key data (key should have data count of 0).
	 * Returning a complete CountedData gives access to the data and count.
	 *
	 * @param key The key to look for.
	 * @return data The complete CountedData that matches key, null otherwise.
	 */
	public CountedData<T> retrieve(final CountedData<T> key) {

		CountedData<T> key_data = null; // the node we want to retrieve a copy of
		TreeNode<T> curr = this.root; // get the root to iterate through
		int found;

		while (key_data == null && curr != null) {

			// if curr is greater than key then we want to go left, right otherwise
			found = curr.getValue().compareTo(key);

			this.comparisons++; // each comparision

			if (found == 0)
				key_data = curr.getValue();

			// if the data isnt found then iterate through the tree
			else if (found < 0)
				curr = curr.getRight();
			else
				curr = curr.getLeft();

		}

		return key_data; // will return NULL if the tree doesnt have the value we want to retrieve

	}
}
