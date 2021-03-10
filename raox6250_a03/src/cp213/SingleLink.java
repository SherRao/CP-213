package cp213;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The abstract base class for singly-linked data structures. Provides
 * attributes and implementations for getLength, peek, isEmpty, toArray, and
 * iterator methods. The <code>head</code> attribute is the first node in any
 * singly-linked list.
 *
 * @author David Brown
 * @version 2021-02-05
 * @param <T> data type for base data structure.
 */
public abstract class SingleLink<T> implements Iterable<T> {

	/**
	 * Creates an Iterator for the outer class. An iterator allows a program to walk
	 * through the data in a data structure by using the hasNext and next methods.
	 * Typical code:
	 *
	 * <pre>
	Iterator&lt;T&gt; iter = dataStructureObject.iterator();
	
	while(iter.hasNext()){
	    T data = iter.next();
	    ...
	}
	 * </pre>
	 *
	 * It also allows the user of the enhanced for loop:
	 *
	 * <pre>
	for(T data : dataStructureObject){
	    ...
	}
	 * </pre>
	 *
	 * (Replace T with a concrete class such as String or Integer.)
	 */
	private class SingleLinkIterator implements Iterator<T> {
		// current is initialized to beginning of linked list.
		private SingleNode<T> current = SingleLink.this.front;

		/*
		 * (non-Javadoc)
		 *
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return this.current != null;
			
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.util.Iterator#next()
		 */
		@Override
		public T next() {
			T result = null;
			if (this.current == null) {
				throw new NoSuchElementException();
			} else {
				result = this.current.getData();
				this.current = this.current.getNext();
			}
			return result;
		}
	}

	// First node of linked list.
	protected SingleNode<T> front = null;
	// Last node of linked list.
	protected SingleNode<T> rear = null;
	// Number of elements currently stored in linked list.
	protected int length = 0;

	/**
	 * Returns the current number of elements in the linked structure.
	 *
	 * @return the value of length.
	 */
	public final int getLength() {
		return this.length;

	}

	/**
	 * Determines whether the linked data structure is empty or not.
	 *
	 * @return true if data structure is empty, false otherwise.
	 */
	public final boolean isEmpty() {
		return this.length == 0;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public final Iterator<T> iterator() {
		return new SingleLinkIterator();
	
    }

	/**
	 * Returns a reference to the first data of the linked structure without
	 * removing that data from the structure.
	 *
	 * @return The data in the head of the structure.
	 */
	public final T peek() {
		return this.front.getData();
	
    }

}
