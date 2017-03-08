package collections;

import java.util.Comparator;

/**
 * 
 * The class PriorityQueue represents an implementation of the interface Queue.
 * The class uses an instance of LinkedList to hold data.
 * 
 * @author Filip Nilsson
 *
 */

public class PriorityQueue<E> implements Queue<E> {
	private LinkedList<E> list = new LinkedList<E>();
	Comparator<E> comp;

	/**
	 * Default constructor. 
	 * If used, the Inner class Comp is used as a comparator.
	 */
	public PriorityQueue(){
		comp = new Comp();
	}
	
	/**
	 * Constructor to be used when the user wants the PriorityQueue to place elements after a certain order.
	 * @param comp , Comparator instance
	 */
	public PriorityQueue(Comparator<E> comp) {
		this.comp = comp;
	}

	/**
     * Inserts the specified element into this queue.
     * @param data the object to add
     */
	@Override
	public void enqueue(E data) {
		if(isEmpty()){
			list.add(data);
		}
		else{
			int size=list.size();
			int index=0;
			while(index<size && comp.compare(data, list.get(index))>=0){
				index++;
			}
			list.add(index, data);
		}
	}

	/**
     * Retrieves and removes the head of this queue.
     * @return the head of this queue
     * @throws QueueException if this queue is empty
     */
	@Override
	public E dequeue() {
		if(isEmpty()){
			throw new QueueException("Queue is empty");
		}
		return list.remove(0);
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
		if(isEmpty()){
			throw new QueueException("Queue is empty");
		}
		return list.get(0);
	}

	/**
	 * Returns true if the queue contains no elements.
	 * 
	 * @return true if the queue contains no elements
	 */
	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	/**
     * Returns the number of elements in this queue.
     * @return the number of elements in this queue
     */
	@Override
	public int size() {

		return list.size();
	}
	
	/**
	 * Inner Class. Implementation of Comparator.
	 * @author Filip Nilsson
	 *
	 */
	public class Comp implements Comparator<E>{

		/**
		 * Natural order is compared.
		 * @param o1 first element to compare
		 * @param o2 second element to compare
		 * @return -1, 0 or 1
		 */
		@Override
		public int compare(E o1, E o2) {
			return ((Comparable<E>)o1).compareTo(o2);
		}
		
	}

}
