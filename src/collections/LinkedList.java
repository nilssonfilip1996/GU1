package collections;

import java.util.Iterator;

import javax.swing.text.StyledEditorKit.ForegroundAction;



/**
 * The class LinkedList uses ListNode objects and links them together to create
 * a list. This is done by giving each ListNode in the list a reference to the
 * next in line. Instance variable list refers to the first ListNode object in
 * the chain of ListNode objects. Using the methods of the class the user can
 * insert and remove elements from the list in whatever order desired. Each
 * individual element can be accessed by their Integer index. Index
 * corresponding to position in the list.
 * 
 * @author Filip Nilsson
 *
 */
public class LinkedList<E> implements List<E>, Iterable<E> {
	private ListNode<E> list = null;

	/**
	 * Returns the node at the specified position in this list of nodes.
	 * 
	 * @param index
	 *            index of the element to return
	 * @return the element at the specified position in this list
	 */
	private ListNode<E> locate(int index) {
		ListNode<E> node = list;
		for (int i = 0; i < index; i++)
			node = node.getNext();
		return node;
	}

	/**
	 * Returns the number of nodes in the list.
	 * 
	 * @return , number of nodes in the list.
	 */
	public int size() {
		int n = 0;
		ListNode<E> node = list;
		while (node != null) {
			node = node.getNext();
			n++;
		}
		return n;
	}

	/**
	 * Locates the node at the specific index and returns it
	 * 
	 * @return , node at specific index
	 */
	public E get(int index) {
		if ((index < 0) || (index >= size()))
			throw new IndexOutOfBoundsException("size=" + size() + ", index=" + index);

		ListNode<E> node = locate(index);
		return node.getData();
	}

	/**
	 * Changes the value of a specific node and then returns the old value
	 * 
	 * @return , old value of the node at a specific index
	 */
	public E set(int index, E data) {
		ListNode<E> changedNode = locate(index);
		E oldValue = changedNode.getData();
		changedNode.setData(data);
		return oldValue;
	}

	/**
	 * Appends the specified data to a new node at the end of this list
	 * 
	 * @param data
	 *            data to be appended to this list
	 */
	public void add(E data) {
		add(size(), data);
	}

	/**
	 * Inserts the specified data to a new node at the beginning of this list
	 * 
	 * @param data
	 *            data to be inserted at the beginning of this list
	 */
	public void addFirst(E data) {
		add(0, data);
	}

	/**
	 * Appends the specified element to a new node at the end of this list
	 * 
	 * @param data
	 *            data to be appended at the end of this list
	 */
	public void addLast(E data) {
		add(size(), data);
	}

	/**
	 * Adds a new node to a certain index of the list. Shifts the element
	 * currently at that position (if any) and any subsequent elements to the
	 * right (adds one to their indices).
	 * 
	 * @param index
	 *            index at which the specified data is to be inserted
	 * @param data
	 *            data to be inserted
	 */
	public void add(int index, E data) {
		if ((index < 0) || (index > size())) {
			throw new IndexOutOfBoundsException("size=" + size() + ", index=" + index);
		}
		if (index == 0) {
			list = new ListNode<E>(data, locate(0));
		} else {
			ListNode<E> prevNode = locate(index - 1);
			ListNode<E> nextNode = locate(index);
			ListNode<E> newNode = new ListNode<E>(data, nextNode);
			prevNode.setNext(newNode);
		}
	}

	/**
	 * Removes the node at index 0 and then returns the data of the removed node
	 * 
	 * @return the removed nodes data.
	 */
	public E removeFirst() {
		E removed = remove(0);
		return removed;
	}

	/**
	 * Removes the node at index (size()-1)(ie last node) and then returns the
	 * data of the removed node
	 * 
	 * @return the removed nodes data.
	 */
	public E removeLast() {
		E removed = remove(size() - 1);
		return removed;
	}

	/**
	 * Removes the node at the specified position in this list. Shifts any
	 * subsequent nodes to the left (subtracts one from their indexes). Returns
	 * the data of the node that was removed from the list.
	 * 
	 * @param index
	 *            the index of the node to be removed
	 * @return the nodes data previously at the specified position
	 */
	public E remove(int index) {
		if ((index < 0) || (index >= size()))
			throw new IndexOutOfBoundsException("size=" + size() + ", index=" + index);

		E res;
		if (index == 0) {
			res = list.getData();
			list = setNull(list);
			// list = list.getNext();
		} else {
			ListNode<E> node = locate(index - 1);
			res = node.getNext().getData();
			node.setNext(setNull(node.getNext()));
			// node.setNext( node.getNext().getNext() );
		}
		return res;
	}

	private ListNode<E> setNull(ListNode<E> toNull) {
		ListNode<E> res = toNull.getNext();
		toNull.setData(null);
		toNull.setNext(null);
		return res;
	}

	/**
	 * Removes all of the nodes from this list. The list will be empty after
	 * this call returns.
	 */
	public void clear() {
		ArrayStack<E> stack = new ArrayStack<E>(size());
		for (int i = 0; i < size(); i++) {
			stack.push((E) locate(i));
		}
		int size = size();
		for (int i = 0; i < size; i++) {
			ListNode<E> element = (ListNode<E>) stack.pop();
			element.setData(null);
			element.setNext(null);
		}
		list = null;
	}

	/**
	 * Returns the index of the first occurrence of the specified data in this
	 * list, or -1 if this list does not contain the data.
	 * 
	 * @param data
	 *            , data to be searched
	 * @return the index of the first occurrence of the specified data in this
	 *         list, or -1 if this list does not contain the data
	 */
	public int indexOf(E data) {
		int index = indexOf(0, data);
		return index;
	}

	/**
	 * 
	 * Returns the index of the first occurrence of the specified data in this
	 * list, or -1 if this list does not contain the data. The search starts at
	 * startIndex in the list
	 * 
	 * @param startIndex
	 *            the search starts at position startIndex in the list
	 * @param data
	 *            , data to be searched
	 * @return the index of the first occurrence of the specified data in this
	 *         list, or -1 if this list does not contain the data
	 */
	public int indexOf(int startIndex, E data) {
		if ((startIndex < 0) || (startIndex > size())) {
			throw new IndexOutOfBoundsException("size=" + size() + ", index=" + startIndex);
		}
		for (int i = startIndex; i < size(); i++) {
			if (locate(i).getData().equals(data)) {
				return i;
			}
		}
		return -1;
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
	 * Cycles through the elements of the list and appends each elements
	 * toString method to the variable res. Ex: [element0.toString;
	 * element1.toString; element2.toString] If the list is empty a String
	 * containing a start and an end bracket is returned, symbolizing that the
	 * list is empty
	 * 
	 * @return list.toString , the appended String
	 * @return "[]" , empty list
	 */
	public String toString() {
		if (list != null)
			return list.toString();
		else
			return "[]";
	}

	/**
	 * Inner Class, lets the user iterate through the elements in this list in
	 * proper sequence.
	 * 
	 * @author Filip Nilsson
	 *
	 */
	private class Iter implements Iterator<E> {

		public boolean hasNext() {
			if (list == null) {
				return false;
			}
			return true;
		}

		public E next() {
			ListNode<E> temp = list;
			list = list.getNext();
			return temp.getData();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		// private ListNode<E> node = list;
		//
		// public boolean hasNext() {
		// if (node == null) {
		// return false;
		// }
		// return true;
		// }
		//
		// public E next() {
		// ListNode<E> temp = node;
		// node = node.getNext();
		// return temp.getData();
		// }
		//
		// public void remove() {
		// throw new UnsupportedOperationException();
		// }
	}

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < 20; i++) {
			list.add(i);
		}
		Iterator<Integer> iter = list.iterator();

		System.out.println("-----------print iter---------------");
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}

		if (list.size() == 0) {
			System.out.println("List is Empty!");
		}

		System.out.println("-----------print list---------------");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

	}

}
