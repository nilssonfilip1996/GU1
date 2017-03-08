package gu1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


import javax.swing.JComboBox.KeySelectionManager;

import collections.*;



/**
 * The class BinarySearchTree uses the Tree implementation.
 * It has all the necessary methods in order to function as a BinarySearchTree.
 * 
 * The class uses BSTNode instances as nodes in the tree. 
 * A nodes key determines it's corresponding place within the BinarySearchTree.
 * 
 * @author Filip Nilsson
 *
 * @param <K> , The nodes key
 * @param <V> , The nodes value
 */

public class BinaryUserTree<K, V> implements SearchTree<K, V> {
	private Comparator<K> comparator;
	private BSTNode<K, V> tree;
	private int size;

	/**
	 * Default constructor. 
	 * If used, the Inner class Comp is used as a comparator.
	 */
	public BinaryUserTree() {
		comparator = new Comp();
	}

	/**
	 * Constructor to be used when the user wants the BinarySearchTree to be structured using a specified implementation of a Comparator.
	 * @param comp , Comparator instance
	 */
	public BinaryUserTree(Comparator<K> comp) {
		comparator = comp;
	}

	/**
	 * Returns the root of the tree. 
	 * @return , root of the tree.
	 */
	public BSTNode<K, V> root() {
		return tree;
	}

	/**
	 * Returns the value of a certain node in the tree.
	 * If the tree doesn't contain a node with the specified key then null is returned.
	 * @param key , a certain keyt to be searched for in the tree
	 * @return value corresponding to the parameter key.
	 */
	public V get(K key) {
		BSTNode<K, V> node = find(key);
		if (node != null)
			return node.value;
		return null;
	}

	/**
	 * Adds a new node to the tree.
	 * @param key , the new nodes key
	 * @param value , the new nodes value
	 */
	public void put(K key, V value) {
		tree = put(tree, key, value);
	}

	/**
	 * Removes a certain node in the tree corresponding to the parameter key.
	 * If no node is found containing the specified key then null is returned.
	 * 
	 * @param key , the node corresponding to the key to be removed
	 * @return the value of the removed node.
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
	 * @param key , node to be searched for.
	 * @return true or false depending if the tree contains a node with the certain key.
	 */
	public boolean contains(K key) {
		return find(key) != null;
	}

	/**
	 * @return an int value corresponding to the height of the tree.
	 */
	public int height() {
		return height(tree);
	}

	/**
	 * @return an Iterator instance containing the values of all the nodes in the tree.
	 */
	public Iterator<V> iterator() {
		return new Iter();
	}

	private BSTNode<K, V> find(K key) {
		int res;
		BSTNode<K, V> node = tree;
		while ((node != null) && ((res = comparator.compare(key, node.key)) != 0)) {
			if (res < 0)
				node = node.left;
			else
				node = node.right;
		}
		return node;
	}

	private BSTNode<K, V> put(BSTNode<K, V> node, K key, V value) {
		if (node == null) {
			node = new BSTNode<K, V>(key, value, null, null);
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

	private BSTNode<K, V> remove(BSTNode<K, V> node, K key) {
		int compare = comparator.compare(key, node.key);
		if (compare == 0) {
			if (node.left == null && node.right == null)
				node = null;
			else if (node.left != null && node.right == null)
				node = node.left;
			else if (node.left == null && node.right != null)
				node = node.right;
			else {
				BSTNode<K, V> min = getMin(node.right);
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

	private BSTNode<K, V> getMin(BSTNode<K, V> node) {
		while (node.left != null)
			node = node.left;
		return node;
	}

	private int height(BSTNode<K, V> node) {
		if (node == null)
			return -1;
		return 1 + Math.max(height(node.left), height(node.right));
	}

	/**
	 * @return the number of nodes in the tree.
	 */
	public int size1() {
		if (tree != null) {
			return tree.size();
		} else {
			return 0;
		}

	}

	/**
	 * @return the number of nodes in the tree.
	 */
	public int size2() {
		return size2(tree);
	}

	public int size2(BSTNode<K, V> node) {
		if (node != null) {
			return (1 + size2(node.left) + size2(node.right));
		} else {
			return 0;
		}
	}

	/**
	 * @return the number of nodes in the tree.
	 */
	public int size() {
		return size;
	}

	// Uppgift 9
	public V first() {
		BSTNode<K, V> firstNode = tree;
		if (firstNode == null) {
			return null;
		}
		while (firstNode.left != null) {
			firstNode = firstNode.left;
		}
		return firstNode.value;
	}

	// Uppgift 10
	public V last() {
		BSTNode<K, V> lastNode = tree;
		if (lastNode == null) {
			return null;
		}
		while (lastNode.right != null) {
			lastNode = lastNode.right;
		}
		return lastNode.value;
	}

	// Uppgift 14
	public void print() {
		print(tree);
	}

	private void print(BSTNode<K, V> node) {
		if (node.left != null) {
			print(node.left);
		}
		System.out.println("Key: " + node.key + ", Value: " + node.value);
		if (node.left != null) {
			print(node.left);
		}
	}

	// Uppgift 15
	public void printPreOrder() {
		printPreorder(tree);
	}

	private void printPreorder(BSTNode<K, V> node) {
		System.out.println("Key: " + node.key + ", Value: " + node.value);
		if (node.left != null) {
			printPreorder(node.left);
		}
		if (node.right != null) {
			printPreorder(node.right);
		}
	}

	// Uppgift 16
	public void printPostOrder() {
		printPostOrder(tree);
	}

	private void printPostOrder(BSTNode<K, V> node) {
		if (node.left != null) {
			printPostOrder(node.left);
		}
		if (node.right != null) {
			printPostOrder(node.right);
		}
		System.out.println("Key: " + node.key + ", Value: " + node.value);
	}

	// Uppgift 17
	public void printLevelOrder() {
		printLevelOrder(tree);
	}

	private void printLevelOrder(BSTNode<K, V> node) {
		LinkedQueue<BSTNode<K, V>> queue = new LinkedQueue<BSTNode<K, V>>();
		queue.enqueue(tree);
		while(!queue.isEmpty()){
			BSTNode<K, V> temp = queue.dequeue();
			System.out.println("Key: " + temp.key + ", Value: " + temp.value);
			if(temp.left!=null){
				queue.enqueue(temp.left);
			}
			if(temp.right!=null){
				queue.enqueue(temp.right);
			}
		}
	}

	/**
	 * @return a list instance containing the keys in the tree.
	 */
	public List<K> keys() {
		ArrayList<K> list = new ArrayList<K>();
		keys(tree, list);
		return list;
	}

	private void keys(BSTNode<K, V> node, ArrayList<K> list) {
		if(node.left!=null){
			keys(node.left, list);
		}
		list.add(node.key);
		if(node.right!=null){
			keys(node.right, list);
		}
	}

	/**
	 * @return a list instance containing the values in the tree.
	 */
	public List<V> values() {
		LinkedList<V> list = new LinkedList<V>();
		values(tree, list);
		return list;
	}

	private void values(BSTNode<K, V> node, LinkedList<V> list) {
		if(node.left!=null){
			values(node.left, list);
		}
		list.add(node.value);
		if(node.right!=null){
			values(node.right, list);
		}
		
	}

	private class Comp implements Comparator<K> {
		public int compare(K key1, K key2) {
			Comparable<K> k1 = (Comparable<K>) key1;
			return k1.compareTo(key2);
		}
	}

	private class Iter implements Iterator<V> {
		ArrayList<V> list = new ArrayList<V>();
		int index = -1;

		public Iter() {
			inOrder(tree);
		}

		private void inOrder(BSTNode<K, V> node) {
			if (node != null) {
				inOrder(node.left);
				list.add(node.value);
				inOrder(node.right);
			}
		}

		public boolean hasNext() {
			return index < list.size() - 1;
		}

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

	public static void main(String[] args) {
		BinaryUserTree<String, String> tree = new BinaryUserTree<String, String>();
		tree.put("karta", "map");
		tree.put("vacker", "beautiful");
		tree.put("svart", "black");
		tree.put("lärare", "teacher");
		tree.put("boll", "ball");
		tree.put("vit", "white");
		tree.put("hus", "house");
		tree.put("vänster", "left");
		tree.put("höger", "right");
		System.out.println(tree.first());
		System.out.println(tree.last());
		System.out.println();
		tree.printLevelOrder();
		//tree.root().showTree();
		List<String> list = tree.values();
		Iterator<String> iter = list.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		// tree.root().showTree();
		// String res = (String)tree.get("lärare");
		// System.out.println(res);
		// System.out.println(tree.get("LÄRARE"));
		// System.out.println("---------------------");
		// Iterator<String> elements = tree.iterator();
		// while(elements.hasNext()) {
		// System.out.println(elements.next());
		// }
	}
}
