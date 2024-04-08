package AB2;

/**
 * A queue of bodies. A collection designed for holding bodies prior to processing.
 * The bodies of the queue can be accessed in a FIFO (first-in-first-out) manner,
 * i.e., the body that was first inserted by 'add' is retrieved first by 'poll'.
 * The number of elements of the queue is not limited.
 */
public class BodyQueue {

    //TODO: declare variables.
    private int size;
    private Body[] queue;
    /**
     * Initializes this queue with an initial capacity.
     * @param initialCapacity the length of the internal array in the beginning,
     *                        initialCapacity > 0.
     */
    public BodyQueue(int initialCapacity) {
        //TODO: define constructor.
        queue = new Body[initialCapacity];
        size = 0;
    }

    /**
     * Initializes this queue as an independent copy of the specified queue.
     * Calling methods of this queue will not affect the specified queue
     * and vice versa.
     * @param q the queue from which elements are copied to the new queue, q != null.
     */
    public BodyQueue(BodyQueue q) {
        //TODO: define constructor.
        this.size = q.size;
        this.queue = new Body[q.size];
        for (int i = 0; i < q.size; i++) {
            this.queue[i] = q.queue[i];
        }
    }

    /**
     * Adds the specified body 'b' to this queue.
     * @param b the element that is added to the queue.
     */
    public void add(Body b) {
        //TODO: implement method.
        if (size == queue.length) {
            Body[] newArray = new Body[2* queue.length];
            for (int i = 0; i < size; i++) {
                newArray[i] = queue[i];
            }
            queue = newArray;
        }
        queue[size++] = b;
    }

    /**
     * Retrieves and removes the head of this queue,
     * or returns 'null' if this queue is empty.
     * @return the head of this queue (or 'null' if this queue is empty).
     */
    public Body poll() {

        //TODO: implement method.
        if (size==0) {
            return null;
        }
        Body result = queue[0];
        for (int i = 0; i < size - 1; i++) {
            queue[i] = queue[i + 1];
        }
        queue[--size] = null;
        return result;
    }

    /**
     * Returns the number of bodies in this queue.
     * @return the number of bodies in this queue.
     */
    public int size() {

        //TODO: implement method.
        return size;    }
}
