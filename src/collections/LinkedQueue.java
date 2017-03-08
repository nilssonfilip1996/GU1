package collections;

/**
 * 
 * The class LinkedQueue represents an implementation of the interface Queue.
 * The class uses an instance of LinkedList to hold data.
 * 
 * @author Filip Nilsson
 *
 */
public class LinkedQueue<E> implements Queue<E> {
	private LinkedList<E> queue = new LinkedList<E>();

	/**
	 * Inserts the specified element into this queue.
	 * 
	 * @param data
	 *            the object to add
	 */
	@Override
	public void enqueue(E data) {
		queue.addLast(data);
	}

	/**
	 * Retrieves and removes the head of this queue.
	 * 
	 * @return the head of this queue
	 * @throws QueueException
	 *             if this queue is empty
	 */
	@Override
	public E dequeue() {
		if (isEmpty()) {
			throw new QueueException("Kön är tom!");
		}
		E removedFromQueue = queue.remove(0);
		return removedFromQueue;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue.
	 * 
	 * @return the head of this queue
	 * @throws QueueException
	 *             if this queue is empty
	 */
	@Override
	public E peek() {
		if (isEmpty()) {
			throw new QueueException("Kön är tom!");
		}
		E lastInLine = queue.get(0);
		return lastInLine;
	}

	/**
	 * Returns true if the queue contains no elements.
	 * 
	 * @return true if the queue contains no elements
	 */
	@Override
	public boolean isEmpty() {
		return (queue.size() == 0);
	}

	/**
	 * Returns the number of elements in this stack.
	 * 
	 * @return the number of elements in this stack
	 */
	@Override
	public int size() {
		return queue.size();
	}

}
