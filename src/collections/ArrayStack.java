package collections;

import java.util.EmptyStackException;



/**
 * The class ArrayStack uses an implementation of the interface Stack. The class
 * has several methods corresponding to a stack representation.
 * 
 * Instance variable elements is the Array containing some kind of object.
 * Instance variable size contains an int value corresponding to the number of
 * elements placed on the stack(array). Note: size does not correspond to
 * Array.length.
 * 
 * @author Filip Nilsson
 *
 */
public class ArrayStack<T> implements Stack<T> {
	private T[] elements;
	private int size = 0;

	/**
	 * The only constructor in the class. Allows the user to initiate the
	 * capacity of the stack(Array).
	 * 
	 * @param capacity
	 *            , specify the maximum allowed number of elements to be placed
	 *            on the stack(Array).
	 */
	public ArrayStack(int capacity) {
		elements = (T[]) (new Object[capacity]);
	}

	/**
	 * When pushing(adding) an element to the stack the element is always placed
	 * after the previously pushed item.
	 * 
	 * The method first checks to see if there is room for more elements, if not
	 * an exception is thrown. If there is room, index "size" is given the value
	 * of parameter element. Instance variable size is then incremented by one.
	 * 
	 * @param element
	 *            , element to be pushed to the stack
	 */
	public void push(T element) {
		if (size >= elements.length)
			throw new StackOverflowException("Stack is full!");
		elements[size] = element;
		size++;
	}

	/**
	 * Returns the element that was last placed on the stack. The last placed
	 * element is removed from the stack.
	 * 
	 * @return the element that was last placed on the stack.
	 */
	public T pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		T elem = elements[--size];
		elements[size] = null;
		return elem;
	}

	/**
	 * Returns the element that was last placed on the stack. The last placed
	 * element is not removed.
	 * 
	 * @return the element that was last placed on the stack.
	 */
	public T peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return elements[size - 1];
	}

	/**
	 * Returns true if there are no elements placed on the stack.
	 * 
	 * @return true if stack is empty, otherwise false
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * Returns the number of stored objects on the stack
	 * 
	 * @return the number of stored objects on the stack
	 */
	public int size() {
		return size;
	}

	// public static void main(String[] args) {
	// ArrayStack<Integer> stack = new ArrayStack<Integer>(20);
	// Integer elem;
	// for(int i=0; i<10; i++) {
	// stack.push(new Integer(i));
	// }
	//// stack.push("HEJ"); // kompileringsfel
	// while(!stack.isEmpty()) {
	// elem = stack.pop();
	// System.out.print(elem + " ");
	// }
	// }
}
