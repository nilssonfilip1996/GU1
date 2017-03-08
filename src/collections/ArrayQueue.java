package collections;

/**
 * 
 * The class ArrayQueue represents an implementation of the interface Queue.
 * The class uses an array to hold data meaning it can only hold a certain amount of elements.
 * 
 * @author Filip Nilsson
 *
 */

public class ArrayQueue<E> implements Queue<E> {
	private E[] queue;
	private int size;
	private int first;

	public ArrayQueue(int initialCapacity) {
		queue = (E[]) new Object[initialCapacity];
	}

	/**
     * Inserts the specified element into this queue.
     * @param data the object to add
     * @throws QueueException if the element cannot be added at this
     *         time due to capacity restrictions
     */
	@Override
	public void enqueue(E data) {
		if (size == queue.length) {
			throw new QueueException("Queue is full!");
		}
		queue[(first + size) % queue.length] = data;
		size++;
	}
	
	/**
     * Retrieves and removes the head of this queue.
     * @return the head of this queue
     * @throws QueueException if this queue is empty
     */
	@Override
	public E dequeue() {
		if (isEmpty()) {
			throw new QueueException("dequeue: Queue is empty!");
		}
		E removed = queue[first];
		queue[first] = null;
		if (first == queue.length-1) {
			first = 0;
		} else {
			first++;
		}
		size--;
		return removed;
	}

	/**
     * Retrieves, but does not remove, the head of this queue.  
     * @return the head of this queue
     * @throws QueueException if this queue is empty
     */
	@Override
	public E peek() {
		if (isEmpty()) {
			throw new QueueException("dequeue: Queue is empty!");
		}
		return queue[first];
	}

	/**
     * Returns true if this queue contains no elements.
     * @return true if this queue contains no elements
     */
	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	 /**
     * Returns the number of elements in this queue.
     * @return the number of elements in this queue
     */
	@Override
	public int size() {
		return size;
	}

}
