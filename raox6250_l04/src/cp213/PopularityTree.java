package cp213;

/**
 * Implements a Popularity Tree. Extends BST.
 *
 * @author your name here
 * @author David Brown
 * @version 2021-02-18
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
    	return null;

    }

    /**
     * Performs a left rotation around node.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> parent) {
    	return null;

    }

    /**
     * Performs a right rotation around {@code node}.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> parent) {
    	return null;


    }

    /**
     * Replaces BST {@code insertAux} - does not increment count on repeated
     * insertion. Counts are incremented only on retrieve.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedData<T> data) {
    	return null;

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
        return false;

    }

    /**
     * Very similar to the BST retrieve, but increments the data count here instead
     * of in the insertion.
     *
     * @param key The key to search for.
     */
    @Override
    public CountedData<T> retrieve(CountedData<T> key) {
    	return null;

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
