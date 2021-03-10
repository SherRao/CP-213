package cp213;

import java.util.HashSet;

/**
 * A simple linked list structure of <code>Node T</code> objects. Only the
 * <code>T</code> data contained in the stack is visible through the standard
 * list methods. Extends the <code>SingleLink</code> class, which already
 * defines the head node, length, iterator, and toArray.
 *
 * @author your name here
 * @version 2021-02-05
 * @param <T> this SingleList data type.
 */
public class SingleList<T extends Comparable<T>> extends SingleLink<T> {

    /**
     * Searches for the first occurrence of key in this SingleList. Private helper
     * methods - used only by other ADT methods.
     *
     * @param key The value to look for.
     * @return A pointer to the node previous to the node containing key.
     */
    private SingleNode<T> linearSearch(final T key) {
    	SingleNode<T> node = super.front;
    	while(node != null && node.getData().compareTo(key) != 0)
    		node = node.getNext();
    	    
        if(node == null || node.getData().compareTo(key) != 0)
            return null;
            
    	return node;
    }

    /**
     * Appends data to the end of this SingleList.
     *
     * @param data The data to append.
     */
    public void append(final T data) {
    	if(super.getLength() == 0) 
            super.front = super.rear = new SingleNode<T>(data, null);

        else if(super.getLength() == 1){
            super.rear = new SingleNode<T>(data, null);
            super.front.setNext(super.rear);

        } else {
            SingleNode<T> node = super.front;
            while(node.getNext() != null)
                node = node.getNext();

            node.setNext(new SingleNode<T>(data, null));
            super.rear = node.getNext();
        }

        super.length += 1;
    }

    /**
     * Removes duplicates from this SingleList. The list contains one and only one
     * of each value formerly present in this SingleList. The first occurrence of
     * each value is preserved.
     */
    public void clean() {
        HashSet<T> set = new HashSet<T>();
        SingleNode<T> previous = null;
        SingleNode<T> current = super.front;
        if(super.length <= 1)
            return;

        while(current != null) {
            if( !set.contains(current.getData()) ) {
                set.add(current.getData());

            } else {
                previous.setNext(current.getNext());
                super.length -= 1;
            
            }

            previous = current;
            current = current.getNext();
        }
    }

    /**
     * Combines contents of two lists into a third. Values are alternated from the
     * origin lists into this SingleList. The origin lists are empty when finished.
     * NOTE: data must not be moved, only nodes.
     *
     * @param left  The first list to combine with this SingleList.
     * @param right The second list to combine with this SingleList.
     */
    public void combine(final SingleList<T> left, final SingleList<T> right) {  
        SingleNode<T> tempA  = null;
        SingleNode<T> currA = left.front;
        SingleNode<T> tempB = null;
        SingleNode<T> currB = right.front;
        while(currA != null || currB != null) {
            tempA = currA;
            tempB = currB;
            currA = currA.getNext();
            currB = currB.getNext();
            
            tempB.setNext(null);
            tempA.setNext(tempB);
            super.rear.setNext(tempA);   
            
        }

        //a.length = b.length = 0;

    }

    /**
     * Determines if this SingleList contains key.
     *
     * @param key The key value to look for.
     * @return true if key is in this SingleList, false otherwise.
     */
    public boolean contains(final T key) {
        return this.linearSearch(key) != null;

    }

    /**
     * Finds the number of times key appears in list.
     *
     * @param key The value to look for.
     * @return The number of times key appears in this SingleList.
     */
    public int count(final T key) {
        SingleNode<T> node = super.front;
        int count = 0;
        while(node != null) 
            if(node.getData().equals(key))
                count += 1;

        return count;
    }

    /**
     * Finds and returns the value in list that matches key.
     *
     * @param key The value to search for.
     * @return The value that matches key, null otherwise.
     */
    public T find(final T key) {
        return this.linearSearch(key).getData();

    }

    /**
     * Get the nth item in this SingleList.
     *
     * @param n The index of the item to return.
     * @return The nth item in this SingleList.
     * @throws ArrayIndexOutOfBoundsException if n is not a valid index.
     */
    public T get(final int n) 
        throws ArrayIndexOutOfBoundsException {
        if(super.length < n)
            throw new ArrayIndexOutOfBoundsException(n);

        SingleNode<T> node = super.front;
        int i = 0;
        while(i < n) {
            node = node.getNext();
            i += 1;
        }

        return node.getData();
    }

    /**
     * Determines whether two lists are identical.
     *
     * @param source The list to compare against this SingleList.
     * @return true if this SingleList contains the same values in the same order as
     *         source, false otherwise.
     */
    public boolean identical(final SingleList<T> source) {
        if(source.length != super.length)
            return false;

        SingleNode<T> a = super.front;
        SingleNode<T> b = source.front;        
        while(a != null) {
            if(a.getData().equals(b.getData())) {
                a = a.getNext();
                b = b.getNext();

            } else 
                return false;
        }

        return true;
    }

    /**
     * Finds the first location of a value by key in this SingleList.
     *
     * @param key The value to search for.
     * @return The index of key in this SingleList, -1 otherwise.
     */
    public int index(final T key) {
        SingleNode<T> node = super.front;
        int index = -1;
        int i = 0;
        while(node != null && index == -1) {
            if(node.getData().compareTo(key) != 0) {
                node = node.getNext();
                i += 1;

            } else 
                index = i; 
        }

        return index;
    }

    /**
     * Inserts data into this SingleList at index i. If i greater than the length of
     * this SingleList, append value to the end of this SingleList.
     *
     * @param i    The index to insert the new value at.
     * @param data The new value to insert into this SingleList.
     */
    public void insert(int i, final T data) {
        if(i == 0)
            this.append(data);

        else if(i >= super.length) 
            this.append(data);

        else {
            SingleNode<T> prev = null;
            SingleNode<T> curr = super.front;
            for(int j = 0; j < i; j++) {
                prev = curr;
                curr = curr.getNext();

            }

            prev.setNext(new SingleNode<T>(data, curr));
            this.length += 1;
        }
    }

    /**
     * Inserts data into the front of this SingleList.
     *
     * @param data The value to insert into the front of this SingleList.
     */
    public void prepend(final T data) {
        if(super.length == 0)
            super.front = super.rear = new SingleNode<T>(data, null);

        else if(super.length == 1) {
            super.rear = new SingleNode<T>(data, null);
            super.front.setNext(super.rear);

        } else {
            SingleNode<T> node = super.front;
            super.front = new SingleNode<T>(data, node);

        }

        super.length += 1;
    }

    /**
     * Finds the maximum value in this SingleList.
     *
     * @return The maximum value.
     */
    public T max() {
        if(super.length == 0)
            return null;

        SingleNode<T> node = super.front;
        T max = node.getData();
        while(node != null) {
            if(max.compareTo(node.getData()) == -1)
                max = node.getData();

            node = node.getNext();
        }

        return max;
    }

    /**
     * Finds the minimum value in this SingleList.
     *
     * @return The minimum value.
     */
    public T min() {
        if(super.length == 0)
            return null;

        SingleNode<T> node = super.front;
        T min = node.getData();
        while(node != null) {
            if(min.compareTo(node.getData()) == 1)
                min = node.getData();

            node = node.getNext();
        }

        return min;
    }

    /**
     * Finds, removes, and returns the value in this SingleList that matches key.
     *
     * @param key The value to search for.
     * @return The value matching key, null otherwise.
     */
    public T remove(final T key) {
        if(super.length == 0)
            return null;

        SingleNode<T> prev = null;
        SingleNode<T> curr = super.front;
        while(curr != null) {
            if(curr.getData().compareTo(key) == 0) {
                T data = curr.getData();
                if(prev != null)
                    prev.setNext(curr.getNext());

                else 
                    super.front = super.front.getNext();

                super.length -= 1;
                return data;

            } else {
                prev = curr;
                curr = curr.getNext();
            }
        }

        return null;
    }

    /**
     * Removes the value at the front of this SingleList.
     *
     * @return The value at the front of this SingleList.
     */
    public T removeFront() {
        if(super.length == 0)
            return null;

        else {
            if(super.length == 1) {
                T data = super.front.getData();
                super.front = super.rear = null;
                super.length -= 1;
                return data;

            } else {
                T data = super.front.getData();
                super.front = super.front.getNext();
                SingleNode<T> node = super.front;
                while(node.getNext() != null)
                    node = node.getNext();

                super.rear = node;
                super.length -= 1;
                return data;
            }
        }
    }

    /**
     * Reverses the order of the values in this SingleList.
     */
    public void reverse() {
        SingleNode<T> prev = null;
        SingleNode<T> curr = super.front;
        while(curr != null) {
            SingleNode<T> temp = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = temp;

        }
    }

    /**
     * Splits the contents of this SingleList into the left and right SingleLists.
     * Moves nodes only - does not move data or call the high-level methods insert
     * or remove. this SingleList is empty when done. The first half of this
     * SingleList is moved to left, and the last half of this SingleList is moved to
     * right. If the resulting lengths are not the same, left should have one more
     * item than right. Order is preserved.
     *
     * @param left  The first SingleList to move nodes to.
     * @param right The second SingleList to move nodes to.
     */
    public void split(final SingleList<T> left, final SingleList<T> right) {
        SingleNode<T> prev = null;
        SingleNode<T> center = super.front;
        int centerIndex = super.length % 2 + super.length / 2;
        for(int i = 0; i < centerIndex; i++ ) {
            prev = center;    
            center = center.getNext();
        
        }

        if(prev != null) {
            left.front = super.front;
            prev.setNext(null);

            right.front = center;
            super.front = super.rear = null;

            left.length = centerIndex;
            right.length = super.length / 2;
            super.length = 0;

            SingleNode<T> lrear = left.front;
            while(lrear.getNext() != null)
                lrear = lrear.getNext();

            SingleNode<T> rrear = right.front;
            while(rrear.getNext() != null)
                rrear = rrear.getNext();

            left.rear = lrear;
            right.rear = rrear;

        }
    }

    /**
     * Splits the contents of this SingleList into the left and right SingleLists.
     * Moves nodes only - does not move data or call the high-level methods insert
     * or remove. this SingleList is empty when done. Nodes are moved alternately
     * from this SingleList to left and right. Order is preserved.
     *
     * @param left  The first SingleList to move nodes to.
     * @param right The second SingleList to move nodes to.
     */
    public void splitAlternate(final SingleList<T> left, final SingleList<T> right) {
        if(super.length != 1) {   
            left.front = super.front;
            super.front = super.front.getNext();
            right.front = super.front;
            super.front = super.front.getNext();
            
            SingleNode<T> l = left.front;
            SingleNode<T> r = right.front;
            while(super.front != null) {
                l.setNext(super.front);
                l = l.getNext();
                super.front = super.front.getNext();

                if(super.front != null) {
                    r.setNext(this.front);
                    r = r.getNext();
                    super.front = super.front.getNext();

                }
            }

            SingleNode<T> lrear = left.front;
            while(lrear.getNext() != null)
                lrear = lrear.getNext();

            SingleNode<T> rrear = right.front;
            while(rrear.getNext() != null)
                rrear = rrear.getNext();

            left.rear = lrear;
            right.rear = rrear;

        } else
            left.front = super.front;

        super.front = super.rear = null;
        super.length = 0;
    }

    /**
     * Creates a union of two other SingleLists into this SingleList. Copies data to
     * this list. left and right SingleLists are unchanged.
     *
     * @param left  The first SingleList to create a union from.
     * @param right The second SingleList to create a union from.
     */
    public void union(final SingleList<T> left, final SingleList<T> right) {
        SingleNode<T> node = left.front;
        while(node != null) {
            if(this.contains(node.getData()) == false)
                this.append(node.getData());

            node = node.getNext();
        }

        node = right.front;
        while(node != null) {
            if(this.contains(node.getData()) == false)
                this.append(node.getData());

            node = node.getNext();
        }
    }

}
