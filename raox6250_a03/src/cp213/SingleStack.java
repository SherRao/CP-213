package cp213;

/**
 * A simple linked stack structure of <code>Node T</code> objects. Only the
 * <code>T</code> data contained in the stack is visible through the standard
 * stack methods. Extends the <code>SingleLink</code> class, which already
 * defines the head node, length, peek, isEmpty, and iterator.
 *
 * @author Nausher Rao 
 * @version 2021-02-05
 * @param <T> the SingleStack data type.
 */
public class SingleStack<T> extends SingleLink<T> {

	/**
	 * Returns the top data of the stack and removes that data from the stack. The
	 * next node in the stack becomes the new top node. Decrements the stack length.
	 *
	 * @return The data at the top of the stack.
	 */
	public T pop() {
        T data = null;
        if(super.front != null) {
            data = super.front.getData();
            super.front = super.front.getNext();
            this.length -= 1;
        }

        return data;
	}

	/**
	 * Adds data to the top of the stack. Increments the stack length.
	 *
	 * @param data The data to add to the top of the stack.
	 */
	public void push(final T data) {
		if(super.getLength() == 0) 
			super.front = super.rear = new SingleNode<T>(data, null);
		
		else if(super.getLength() == 1) {
			super.front = new SingleNode<T>(data, super.front);
            super.rear = super.front.getNext();

		} else 
			super.front = new SingleNode<T>(data, super.front);
            
		super.length += 1;
	}
}