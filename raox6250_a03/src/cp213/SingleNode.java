package cp213;

/**
 * The individual node of a linked structure that stores <code>T</code> objects.
 * This is a singly linked node. The node link can be updated, but not the node
 * data, in order to avoid moving data between nodes. Data structures must be
 * reordered by moving nodes, not by copying or moving data.
 *
 * @author David Brown
 * @version 2021-02-05
 */
public final class SingleNode<T> {

    // The generic data.
    private T data = null;
    // Link to the next Node.
    private SingleNode<T> next = null;

    /**
     * Creates a new node with data and link to next node. Not copy safe as it
     * accepts a reference to the data rather than a copy of the data.
     *
     * @param data the data to store in the node.
     * @param next the next node to link to.
     */
    public SingleNode(final T data, final SingleNode<T> next) {
	this.data = data;
	this.next = next;
    }

    /**
     * Returns the node data. Not copy safe as it returns a reference to the data,
     * not a copy of the data.
     *
     * @return The data portion of the node.
     */
    public T getData() {
	return this.data;
    }

    /**
     * Returns the next node in the linked structure.
     *
     * @return The node that follows this node.
     */
    public SingleNode<T> getNext() {
	return this.next;
    }

    /**
     * Links this node to the next node.
     *
     * @param next The new node to link to.
     */
    public void setNext(final SingleNode<T> next) {
	this.next = next;
    }
}
