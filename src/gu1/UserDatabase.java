package gu1;

import java.util.*;
import java.util.NoSuchElementException;
/**
* A class that creats  binary searchable user database
* @author Jesper Anderberg, Filip Nilsson, Aron Polner, Ali Hassan, Szilveszter Dezsi
*
*/
public class UserDatabase<K, V> implements SearchTree<K, V> {
	private Comparator<K> comparator;
	private UserNode<K, V> tree;
	private int size;
	
	/**
	    * Constructs an empty  capacity.
	    *
	    * instanstiates compparator
	    */
	public UserDatabase() {
		comparator = new Comp();
	}
	/**
	    * Constructor som compares a user tree.
	    * @param comp compares  users tree
	    */
	public UserDatabase(Comparator<K> comp) {
		comparator = comp;
	}
	/**
	 * a method that returns the root of a user tree
	 * @param root the root of the user tree
    */
	public UserNode<K, V> root() {
		return tree;
	}
	/**
    * a method that returns a user  with specified key
    *in the library borrowers list
    * @param key  the personal number of the user
    * @return	the user details such as ID, name and phone number
    */
	public V get(K key) {
		UserNode<K, V> node = find(key);
		if (node != null)
			return node.value;
		return null;
	}
	/**
     * puts a borrower  with specified key in the
     * library user list
     * @param key  th personal number  of the user 
     * @param value the user details such as ID, name and phone number 
     */
	public void put(K key, V value) {
		tree = put(tree, key, value);
	}
	/**
	    * a method that removes a user  with specified key
	    *in the library borrowers list
	    * @param key  the personal number of the user
	    * @return the user details such as ID, name and phone number
	    */
	public V remove(K key) {
		V value = get(key);
		if (value != null) {
			tree = remove(tree, key);
			size--;
		}
		return value;
	}
	/**
     *a method that  checks  if the user list contains
     *a user with specified key
     * @param key  th personal number  of the user
     */
	public boolean contains(K key) {
		return find(key) != null;
	}
	/**
     * a method that retunrs the heigth of a user treee
     *@return the heigth of a tree.
     */
	public int height() {
		return height(tree);
	}
	/**
     * a method that creats an iterator object
     * @return the an iterator object
     */
	public Iterator<V> iterator() {
		return new Iter();
	}
	/**
     * a method that finds an a user node  with specified 
     * personal number and retuns the user object.
     * @param key  th personal number of the user 
      @return the user object.
     */
	private UserNode<K, V> find(K key) {
		int res;
		UserNode<K, V> node = tree;
		while ((node != null) && ((res = comparator.compare(key, node.key)) != 0)) {
			if (res < 0)
				node = node.left;
			else
				node = node.right;
		}
		return node;
	}
	/**
     * puts a borrower  with specified key and value in the
     * library user list
     * @param node the user object tu put the list
     * @param key  th personal number  of the user 
     * @param value the user details such as ID, name and phone number
     *  @return the user object.
     */
	private UserNode<K, V> put(UserNode<K, V> node, K key, V value) {
		if (node == null) {
			node = new UserNode<K, V>(key, value, null, null);
			size++;
		} else {
			if (comparator.compare(key, node.key) < 0) {
				node.left = put(node.left, key, value);
			} else if (comparator.compare(key, node.key) > 0) {
				node.right = put(node.right, key, value);
			}
		}
		return node;
	}
	/**
     *removes a borrower  with specified key and value in the
     * library user list
     * @param node the user object tu put the list
     * @param key  th personal number  of the user 
     * @param value the user details such as ID, name and phone number
     *  @return the user object.
     */
	private UserNode<K, V> remove(UserNode<K, V> node, K key) {
		int compare = comparator.compare(key, node.key);
		if (compare == 0) {
			if (node.left == null && node.right == null)
				node = null;
			else if (node.left != null && node.right == null)
				node = node.left;
			else if (node.left == null && node.right != null)
				node = node.right;
			else {
				UserNode<K, V> min = getMin(node.right);
				min.right = remove(node.right, min.key);
				min.left = node.left;
				node = min;
			}
		} else if (compare < 0) {
			node.left = remove(node.left, key);
		} else {
			node.right = remove(node.right, key);
		}
		return node;
	}
	/**
     *  a method that returns the user with the minimum key 
     * @return the user object.
     */
	private UserNode<K, V> getMin(UserNode<K, V> node) {
		while (node.left != null)
			node = node.left;
		return node;
	}
	/**
     * a method that retunrs the heigth of a user treee
     * @param node the the userNode tree 
     *@return the heigth of a tree.
     */
	private int height(UserNode<K, V> node) {
		if (node == null)
			return -1;
		return 1 + Math.max(height(node.left), height(node.right));
	}
	/**
	 * A method that returns the size of tree
	 * @return the size.
	 */
	public int size() {
		return size;
	}
	/**
	 * A method that returns the size of tree
	 * @return the size.
	 */
	public int sizeOfTree() {
		if (tree != null) {
			return tree.size();
		} else {
			return 0;
		}
	}
	/**
	 * A method that returns the first user object in the tree of tree
	 * @return the user details such as ID, name and phone number
	 */
	public V first() {
		UserNode<K, V> firstNode = tree;
		if (firstNode == null) {
			return null;
		}
		while (firstNode.left != null) {
			firstNode = firstNode.left;
		}
		return firstNode.value;
	}
	/**
	 * A method that returns the last user object in the tree of tree
	 * @return the user details such as ID, name and phone number
	 */
	public V last() {
		UserNode<K, V> lastNode = tree;
		if (lastNode == null) {
			return null;
		}
		while (lastNode.right != null) {
			lastNode = lastNode.right;
		}
		return lastNode.value;
	}
	/**
	 * A method that prints the eements of the user object 
	 * in the tree of tree
	 */
	public void print() {
		print(tree);
	}
	/**
	 * A method that prints the eements of the user object 
	 * in the tree of tree as inorder 
	 *@ param node the userBase object to be printed
	 */
	private void print(UserNode<K, V> node) {
		if (node.left != null) {
			print(node.left);
		}
		System.out.println("Key: " + node.key + ", Value: " + node.value);
		if (node.right != null) {
			print(node.right);
		}
	}
	/**
     * a method that lists the keys of the elements
     * in the user object and rtunrs these keys(personal numbers)
     *@return the personal numbers of the user objects
     */
	public List<K> keys() {
		ArrayList<K> list = new ArrayList<K>();
		keys(tree, list);
		return list;
	}
	/**
     * a method that lists the keys of the elements
     * in the user object 
     * @param node user object 
     *@param list of the  user objects
     */
	private void keys(UserNode<K, V> node, ArrayList<K> list) {
		if(node.left!=null){
			keys(node.left, list);
		}
		list.add(node.key);
		if(node.right!=null){
			keys(node.right, list);
		}
	}
	/**
     * a method that lists the values of the elements
     * in the user object and rtunrs these keys(personal numbers)
     *@return the personal number, name and phone number of the user. 
     */
	public List<V> values() {
		LinkedList<V> list = new LinkedList<V>();
		values(tree, list);
		return list;
	}
	/**
     * a method that lists the values of the elements
     * in the user object 
     * @param node user object 
     *@param list of the  user objects
     */
	private void values(UserNode<K, V> node, LinkedList<V> list) {
		if(node.left!=null){
			values(node.left, list);
		}
		list.add(node.value);
		if(node.right!=null){
			values(node.right, list);
		}
		
	}
	/**
	 * a local private class that implements
	 * a comparator and sorts the elements accordin to thier keys
	 * @return the personal number of a user in the list.
	 * */
	private class Comp implements Comparator<K> {
		public int compare(K key1, K key2) {
			Comparable<K> k1 = (Comparable<K>) key1;
			return k1.compareTo(key2);
		}
	}
	/**
	 * A locl class that creates an iterator and implements 
	 * iterator. 
	 */
	private class Iter implements Iterator<V> {
		ArrayList<V> list = new ArrayList<V>();
		int index = -1;
		/**
		 *a metod taht returns a sorts and returns an tree 
		 *list inOrder. 
		 */
		public Iter() {
			inOrder(tree);
		}
		/**
		 *a metod taht returns a sorts and returns an tree 
		 *list inOrder. 
		 *@pram a tree object to be sorted inOrder.
		 */
		private void inOrder(UserNode<K, V> node) {
			if (node != null) {
				inOrder(node.left);
				list.add(node.value);
				inOrder(node.right);
			}
		}
		/**
		 * a method that returns the last index of an elements in the
		 * tree . 
		 * @return last index of a tree
		 */
		public boolean hasNext() {
			return index < list.size() - 1;
		}
		/**
		 * a method that returns the  index of an elements in the
		 * tree . 
		 * @return  index of a tree
		 */
		public V next() {
			if (!hasNext())
				throw new NoSuchElementException();
			index++;
			return list.get(index);
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}