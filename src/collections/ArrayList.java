
package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents a rezisable-array implementation of the List interface.
 * Using the methods of the class the user can insert and remove elements from
 * the list in whatever order desired. Each individual element can be accessed
 * by their Integer index. Index corresponding to position in the list.
 * 
 * 
 * @author Filip Nilsson
 *
 */

public class ArrayList<E> implements List<E> {
	private E[] elements;
	private int size;

	/**
	 * Default constructor, could be used when not knowing the initial capacity
	 * of the Array. The array is set to an initial capacity of 20 elements if
	 * this Constructor is used.
	 */
	public ArrayList() {
		this(20);
	}

	/**
	 * This constructor should be used when the user wants to specify the
	 * initial capacity.
	 * 
	 * @param initialCapacity
	 *            , specify the initial size of the array.
	 */
	public ArrayList(int initialCapacity) {
		initialCapacity = Math.max(1, initialCapacity);
		elements = (E[]) new Object[initialCapacity];
	}

	/**
	 * Resizes the array to it's current size*2. Ex: previous size=2--->new
	 * size=4
	 */
	private void grow() {
		E[] temp = (E[]) new Object[size * 2];
		for (int i = 0; i < elements.length; i++) {
			temp[i] = elements[i];
		}
		this.elements = temp;
	}

	/**
	 * Inserts the specified element at the specified position in this list.
	 * Shifts the element currently at that position (if any) and any subsequent
	 * elements to the right (adds one to their indexes). If the parameter index
	 * is less then 0 or bigger the size, an exception is thrown.
	 * 
	 * @param index
	 *            index at which the specified element is to be inserted
	 * @param element
	 *            element to be inserted
	 */
	public void add(int index, E element) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		if (size == elements.length)
			grow();
		for (int i = size; i > index; i--) {
			elements[i] = elements[i - 1];
		}
		elements[index] = element;
		size++;
	}

	/**
	 * Appends the specified element to the end of this list
	 * 
	 * @param element
	 *            element to be appended to this list
	 */
	public void add(E element) {
		add(size, element);
	}

	/**
	 * Inserts the specified element at the beginning of this list
	 * 
	 * @param element
	 *            element to be inserted at the beginning of this list
	 */
	public void addFirst(E element) {
		add(0, element);
	}

	/**
	 * Appends the specified element at the end of this list
	 * 
	 * @param element
	 *            element to be appended at the end of this list
	 */
	public void addLast(E element) {
		add(size, element);
	}

	/**
	 * Removes the element at the specified position in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices).
	 * Returns the element that was removed from the list.
	 * 
	 * @param index
	 *            the index of the element to be removed
	 * @return the element previously at the specified position
	 */
	public E remove(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		E element = elements[index];
		size--;
		for (int i = index; i < size; i++) {
			elements[i] = elements[i + 1];
		}
		return element;
	}

	/**
	 * Removes and returns the first element from this list.
	 * 
	 * @return the first element from this list
	 */
	public E removeFirst() {
		E element = elements[0];
		remove(0);
		return element;
	}

	/**
	 * Removes and returns the last element from this list.
	 * 
	 * @return the removed element.
	 */
	public E removeLast() {
		E element = elements[size - 1];
		remove(size - 1);
		return element;
	}

	/**
	 * Removes all of the elements from this list. The list will be empty after
	 * this call returns.
	 */
	public void clear() {
		for (int i = 0; i < elements.length; i++) {
			elements[i] = null;
		}
		size = 0;
	}

	/**
	 * Returns the element at the specified position in this list.
	 * 
	 * @param index
	 *            index of the element to return
	 * @return the element at the specified position in this list
	 */
	public E get(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		return elements[index];
	}

	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element
	 * 
	 * @param index
	 *            index of the element to replace
	 * @param element
	 *            element to be stored at the specified position
	 * @return the element previously at the specified position
	 */
	public E set(int index, E element) {
		if (index < 0 || index > size) {

			throw new IndexOutOfBoundsException();
		}
		E prevElem = remove(index);
		add(index, element);
		return prevElem;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in
	 * this list, or -1 if this list does not contain the element.
	 * 
	 * @param element
	 *            element to search for
	 * @return the index of the first occurrence of the specified element in
	 *         this list, or -1 if this list does not contain the element
	 */
	public int indexOf(E element) {
		return indexOf(0, element);

	}

	/**
	 * Returns the index of the first occurrence of the specified element in
	 * this list, or -1 if this list does not contain the element. The search
	 * begins at startIndex in the list.
	 * 
	 * @param startIndex
	 *            the search starts at position startIndex in the list
	 * @param element
	 *            element to search for
	 * @return the index of the first occurrence of the specified element in
	 *         this list, or -1 if this list does not contain the element
	 */
	public int indexOf(int startIndex, E element) {
		if (startIndex < 0 || startIndex > size) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = startIndex; i < size; i++) {
			if (get(i).equals(element)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Returns the number of elements in this list.
	 * 
	 * @return the number of elements in this list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Cycles through the elements of the list and appends each elements toString method to the variable res.
	 * Ex: [element0.toString; element1.toString; element2.toString]
	 * 
	 * @return res , the appended String
	 */
	public String toString() {
		StringBuilder res = new StringBuilder("[ ");
		for (int i = 0; i < size; i++) {
			res.append(elements[i]);
			if (i < size - 1)
				res.append("; ");
		}
		res.append(" ]");
		return res.toString();
	}

	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 * 
	 * @return an iterator over the elements in this list in proper sequence
	 */
	public Iterator<E> iterator() {
		return new Iter();
	}

	/**
	 * Inner Class, lets the user iterate through the elements in this list in
	 * proper sequence.
	 * 
	 * @author Filip Nilsson
	 *
	 */
	private class Iter implements Iterator<E> {
		private int index = 0;

		/**
		 * Checks if the index is less then the size. If so, then the method
		 * returns true. Tells the user if there are more elements remaining in
		 * the list.
		 */
		public boolean hasNext() {
			return index < size;
		}

		/**
		 * First the method checks if the index corresponds to the size, if so
		 * an exception is thrown. Returns the element with the current Index.
		 * Then increments index with one.
		 */
		public E next() {
			if (index == size)
				throw new NoSuchElementException();
			return elements[index++];
		}

		/**
		 * Not implemented.
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>(2);
		for (int i = 10; i < 100; i += 10) {
			list.add(i);
		}
		System.out.println(list);
		// list.remove(0);
		// list.remove(0);
		// System.out.println(list.get(5));
		// list.clear();
		// System.out.println(list.size);
		// list.removeLast();
		// System.out.println(list.size);
		list.remove(4);
		Iterator<Integer> numbers = list.iterator();
		while (numbers.hasNext())
			System.out.print(numbers.next() + " ");
	}
}
