package cp213;

/**
 * A simple linked queue structure of <code>Node T</code> objects. Only the
 * <code>T</code> data contained in the queue is visible through the standard
 * queue methods. Extends the <code>SingleLink</code> class, which already
 * defines the head node, length, peek, isEmpty, and iterator.
 *
 * @author your name here
 * @version 2021-02-05
 * @param <T> the SingleQueue data type.
 */
public class SingleQueue<T> extends SingleLink<T> {

    /**
     * Combines the contents of the left and right SingleQueues into the current SingleQueue.
     * Moves nodes only - does not move data or call the high-level methods insert
     * or remove. left and right SingleQueues are empty when done. Nodes are moved
     * alternately from left and right to this SingleQueue.
     *
     * You have two queues called left and right queues. You remove nodes from these
     * two queues to create a new queue (current queue).
     *
     * If the current queue is empty or not, it should not make a difference to you.
     * you just get nodes from the right and left queues and add them to the current
     * queue. Do not use insert and remove methods. . Use moveFront method included
     * in the class.
     *
     * Remember to remove a node from the list you have to update the reference or
     * the pointer that is pointing to the current node.
     *
     * The getNext () and setNext() methods from SingleNode class can be used for
     * these purpose.
     *
     * Do not assume that both right and left queues are of the same length.
     *
     * @param left  The first SingleQueue to extract nodes from.
     * @param right The second SingleQueue to extract nodes from.
     */
    public void combine(final SingleQueue<T> left, final SingleQueue<T> right) {
        while(right.front != null || left.front != null) {
            if(left.front != null)
                this.move(left);

            if(right.front != null)
                this.move(right);

        }
    }

    private void move(SingleQueue<T> source) {
        SingleNode<T> node = source.front;
        source.length--;
        source.front = source.front.getNext();
        if(source.front == null) 
            source.rear = null;
        
        node.setNext(null); 
        if(super.front == null)
            super.front = node;

        else 
            super.rear.setNext(node);

        this.rear = node;
        this.length++;
    }

    /**
     * Adds data to the rear of the queue. Increments the queue length.
     *
     * @param data The data to added to the rear of the queue.
     */
    public void insert(final T data) {
        SingleNode<T> node = new SingleNode<>(data, null);
        if(this.length == 0) 
            this.front = node;

        else 
            this.rear.setNext(node);

        this.rear = node;
        this.length++;
    }

    /**
     * Returns the front data of the queue and removes that data from the queue. The
     * next node in the queue becomes the new first node. Decrements the queue
     * length.
     *
     * @return The data at the front of the queue.
     */
    public T remove() {
        if(super.length > 1) {
            SingleNode<T> node = super.front;
            super.front = super.front.getNext();
            super.length -= 1;
            return node.getData();

        } else if(super.length > 0) {
            SingleNode<T> node = super.front;
            super.front = super.front.getNext();
            super.rear = super.front;
            super.length -= 1;
            return node.getData();

        } else 
            return null;
    }

    /**
     * Splits the contents of the current SingleQueue into the left and right SingleQueues.
     * Moves nodes only - does not move data or call the high-level methods insert
     * or remove. this SingleQueue is empty when done. Nodes are moved alternately from
     * this SingleQueue to left and right.
     *
     * This is the opposite of the combine method.
     *
     * @param left  The first SingleQueue to move nodes to.
     * @param right The second SingleQueue to move nodes to.
     */
    public void split(final SingleQueue<T> left, final SingleQueue<T> right) {
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

}
