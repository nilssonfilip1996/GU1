package collections;

/**
 * The class ListNode is used to build some kind of list or chain of objects.
 * The class has two instance variables, data and next.
 * 
 * Instance variable data holds the specific nodes data. It is assigned to be of
 * type generics <E> meaning it can hold any type of object.
 * 
 * Instance variable next refers to the next ListNode in the chain of ListNodes.
 * 
 * Note: All ListNodes in the chain of ListNodes must be of the same type of
 * object.
 * 
 * @author Filip Nilsson
 *
 * @param <E>
 */
public class ListNode<E> {
	private E data;
	private ListNode<E> next;

	/**
	 * Constructor. Instantiates the instance Variables data and next.
	 * 
	 * @param data
	 *            , data of generic type <E> to be given the ListNode Object.
	 * @param next
	 *            , a reference to the next-in-line node.
	 */
	public ListNode(E data, ListNode<E> next) {
		this.data = data;
		this.next = next;
	}

	/**
	 * Returns the nodes data
	 * @return the nodes data
	 */
	public E getData() {
		return this.data;
	}

	/**
	 * Sets a new value to the nodes instance variable data.
	 * @param data , new data to be given the node
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * Returns the reference to the next in-line-node.
	 * 
	 * @return , the reference to the next -in-line node
	 */
	public ListNode<E> getNext() {
		return this.next;
	}

	/**
	 * Gives the node a new value to it's instance variable next. next now refers to a new ListNode object.
	 * @param next , new value to be given next.
	 */
	public void setNext(ListNode<E> next) {
		this.next = next;
	}

	/**
	 * Cycles through the nodes of the list and appends each nodes toString method to the variable str.
	 * Ex: [node0.toString; node1.toString; node2.toString]
	 * 
	 * @return str.toString , the appended String
	 */
	public String toString() {
		StringBuilder str = new StringBuilder("[ ");
		str.append(data.toString());
		ListNode<E> node = next;
		while (node != null) {
			str.append("; ");
			str.append(node.getData().toString());
			node = node.getNext();
		}
		str.append(" ]");
		return str.toString();
	}
}