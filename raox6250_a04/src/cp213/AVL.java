package cp213;

/**
 * Implements an AVL (Adelson-Velsky Landis) tree. Extends BST.
 *
 * @author Nausher Rao
 * @version 2021-03-18
 * @param <T>
 */
public class AVL<T extends Comparable<T>> extends BST<T> {

	/**
	 * Returns the balance value of node. If greater than 1, then left heavy, if
	 * less than -1, then right heavy. If in the range -1 to 1 inclusive, the node
	 * is balanced. Used to determine whether to rotate a node upon insertion.
	 *
	 * @param node The TreeNode to analyze for balance.
	 * @return A balance number.
	 */
	private int balance(final TreeNode<T> node) {
		return node == null ? 0 : nodeHeight(node.getLeft()) - nodeHeight(node.getRight());

	}

	/**
	 * Performs a left rotation around node.
	 *
	 * @param node The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateLeft(final TreeNode<T> node) {
		TreeNode<T> right = node.getRight();
		TreeNode<T> rightLeft = right.getLeft();

		right.setLeft(node);
		node.setRight(rightLeft);
		node.updateHeight();
		right.updateHeight();

		return right;
	}

	/**
	 * Performs a right rotation around {@code node}.
	 *
	 * @param node The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateRight(final TreeNode<T> node) {
		TreeNode<T> left = node.getLeft();
		TreeNode<T> leftRight = left.getRight();

		left.setRight(node);
		node.setLeft(leftRight);
		node.updateHeight();
		left.updateHeight();

		return left;
	}

	/**
	 * Auxiliary method for {@code insert}. Inserts data into this AVL.
	 *
	 * @param node the current node (TreeNode)
	 * @param data Data to be inserted into the node
	 * @return The inserted node.
	 */
	@Override
	protected TreeNode<T> insertAux(TreeNode<T> node, final CountedData<T> data) {
		if (node == null) {
			TreeNode<T> temp = new TreeNode<>(data);
			node = temp;
			this.size++;

		} else {
			int a = node.getValue().compareTo(data);
			if (a == 0)
				node.getValue().incrementCount();

			else if (a > 0)
				node.setLeft(insertAux(node.getLeft(), data));

			else
				node.setRight(insertAux(node.getRight(), data));
		}

		node.updateHeight();
		int factor = balance(node);
		if (factor == 2 && balance(node.getLeft()) >= 0)
			node = rotateRight(node);

		else if (factor == 2 && balance(node.getLeft()) < 0) {
			node.setLeft(rotateLeft(node.getLeft()));
			node = rotateRight(node);

		} else if (factor == -2 && balance(node.getRight()) <= 0)
			node = rotateLeft(node);

		else if (factor == -2 && balance(node.getRight()) > 0) {
			node.setRight(rotateRight(node.getRight()));
			node = rotateLeft(node);

		}

		return node;
	}

	/**
	 * Auxiliary method for {@code valid}. Determines if a subtree based on node is
	 * a valid subtree. An AVL must meet the BST validation conditions, and
	 * additionally be balanced in all its subtrees - i.e. the difference in height
	 * between any two children must be no greater than 1.
	 *
	 * @param node The root of the subtree to test for validity.
	 * @return true if the subtree base on node is valid, false otherwise.
	 */
	@Override
	protected boolean isValidAux(final TreeNode<T> node) {
		boolean valid = false;
		if (node == null)
			valid = true;

		else {
			if (balance(node) < 2 && balance(node) > -2 && isValidAux(node.getLeft()) && isValidAux(node.getRight()))
				valid = true;

			else
				valid = false;
		}

		return valid;
	}

	/**
	 * Determines whether two AVLs are identical.
	 *
	 * @param target The AVL to compare this AVL against.
	 * @return true if this AVL and target contain nodes that match in position,
	 *         value, count, and height, false otherwise.
	 */
	public boolean equals(final AVL<T> target) {
		return super.equals(target);

	}

}