package cp213;

/**
 * Implements a Popularity Tree. Extends BST.
 *
 * @author Nausher Rao
 * @version 2021-03-18
 */
public class PopularityTree<T extends Comparable<T>> extends BST<T> {

	/**
	 * Auxiliary method for {@code valid}. May force node rotation if the retrieval
	 * count of the located node value is incremented.
	 *
	 * @param node The node to examine for key.
	 * @param key  The value to search for. Count is updated to count in matching
	 *             node value if key is found.
	 * @return the updated node.
	 */
	private TreeNode<T> retrieveAux(TreeNode<T> node, final CountedData<T> key) {
		if (node != null) {
			comparisons++;
			int a = node.getValue().compareTo(key);
			if (a == 0) {
				node.getValue().incrementCount();
				key.setCount(node.getValue().getCount());

			} else if (a < 0) {
				node.setRight(retrieveAux(node.getRight(), key));
				node.updateHeight();
				int parent = node.getValue().getCount();
				if (node.getRight() != null && parent < node.getRight().getValue().getCount())
					node = rotateLeft(node);

			} else {
				node.setLeft(retrieveAux(node.getLeft(), key));
				node.updateHeight();
				int parent = node.getValue().getCount();
				if (node.getLeft() != null && parent < node.getLeft().getValue().getCount())
					node = rotateRight(node);

			}
		}

		return node;
	}

	/**
	 * Performs a left rotation around node.
	 *
	 * @param parent The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateLeft(final TreeNode<T> parent) {
		TreeNode<T> right = parent.getRight();
		TreeNode<T> rightLeft = right.getLeft();

		right.setLeft(parent);
		parent.setRight(rightLeft);
		parent.updateHeight();
		right.updateHeight();

		return right;
	}

	/**
	 * Performs a right rotation around {@code node}.
	 *
	 * @param parent The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateRight(final TreeNode<T> parent) {
		TreeNode<T> left = parent.getLeft();
		TreeNode<T> leftRight = left.getRight();

		left.setRight(parent);
		parent.setLeft(leftRight);
		parent.updateHeight();
		left.updateHeight();

		return left;
	}

	/**
	 * Replaces BST {@code insertAux} - does not increment count on repeated
	 * insertion. Counts are incremented only on retrieve.
	 */
	@Override
	protected TreeNode<T> insertAux(TreeNode<T> node, final CountedData<T> data) {
		if (node == null) {
			TreeNode<T> temp = new TreeNode<>(data);
			node = temp;
			size++;

		} else {
			int curr = node.getValue().compareTo(data);
			if (curr > 0)
				node.setLeft(insertAux(node.getLeft(), data));

			else
				node.setRight(insertAux(node.getRight(), data));

		}

		node.updateHeight();
		return node;
	}

	/**
	 * Auxiliary method for {@code valid}. Determines if a subtree based on node is
	 * a valid subtree. An Popularity Tree must meet the BST validation conditions,
	 * and additionally the counts of any node data must be greater than or equal to
	 * the counts of its children.
	 *
	 * @param node The root of the subtree to test for validity.
	 * @return true if the subtree base on node is valid, false otherwise.
	 */
	@Override
	protected boolean isValidAux(final TreeNode<T> node) {
		boolean bst = super.isValidAux(node);
		boolean valid = false;

		if (bst) {
			if (node == null)
				valid = true;

			else {
				if (node.getLeft() != null && node.getValue().getCount() < node.getLeft().getValue().getCount())
					valid = false;

				if (node.getRight() != null && node.getValue().getCount() < node.getRight().getValue().getCount())
					valid = false;

				else
					valid = isValidAux(node.getLeft()) && isValidAux(node.getRight());
			}
		}

		return valid;
	}

	/**
	 * Very similar to the BST retrieve, but increments the data count here instead
	 * of in the insertion.
	 *
	 * @param key The key to search for.
	 */
	@Override
	public CountedData<T> retrieve(CountedData<T> key) {
		root = retrieveAux(root, key);
		if (key.getCount() != 0)
			key = new CountedData<T>(key);

		return key;
	}

	/**
	 * Determines whether two PopularityTrees are identical.
	 *
	 * @param target The PopularityTree to compare this PopularityTree against.
	 * @return true if this PopularityTree and target contain nodes that match in
	 *         position, value, count, and height, false otherwise.
	 */
	public boolean equals(final PopularityTree<T> target) {
		return super.equals(target);

	}

}