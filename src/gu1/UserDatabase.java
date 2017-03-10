package gu1;

import java.util.*;
import java.util.NoSuchElementException;

public class UserDatabase<K, V> implements SearchTree<K, V> {
	private Comparator<K> comparator;
	private UserNode<K, V> tree;
	private int size;

	public UserDatabase() {
		comparator = new Comp();
	}

	public UserDatabase(Comparator<K> comp) {
		comparator = comp;
	}

	public UserNode<K, V> root() {
		return tree;
	}

	public V get(K key) {
		UserNode<K, V> node = find(key);
		if (node != null)
			return node.value;
		return null;
	}

	public void put(K key, V value) {
		tree = put(tree, key, value);
	}

	public V remove(K key) {
		V value = get(key);
		if (value != null) {
			tree = remove(tree, key);
			size--;
		}
		return value;
	}

	public boolean contains(K key) {
		return find(key) != null;
	}

	public int height() {
		return height(tree);
	}

	public Iterator<V> iterator() {
		return new Iter();
	}

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

	private UserNode<K, V> getMin(UserNode<K, V> node) {
		while (node.left != null)
			node = node.left;
		return node;
	}

	private int height(UserNode<K, V> node) {
		if (node == null)
			return -1;
		return 1 + Math.max(height(node.left), height(node.right));
	}

	public int size() {
		return size;
	}
	
	public int sizeOfTree() {
		if (tree != null) {
			return tree.size();
		} else {
			return 0;
		}
	}

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

	public void print() {
		print(tree);
	}

	private void print(UserNode<K, V> node) {
		if (node.left != null) {
			print(node.left);
		}
		System.out.println("Key: " + node.key + ", Value: " + node.value);
		if (node.right != null) {
			print(node.right);
		}
	}

	public List<K> keys() {
		ArrayList<K> list = new ArrayList<K>();
		keys(tree, list);
		return list;
	}

	private void keys(UserNode<K, V> node, ArrayList<K> list) {
		if(node.left!=null){
			keys(node.left, list);
		}
		list.add(node.key);
		if(node.right!=null){
			keys(node.right, list);
		}
	}

	public List<V> values() {
		LinkedList<V> list = new LinkedList<V>();
		values(tree, list);
		return list;
	}

	private void values(UserNode<K, V> node, LinkedList<V> list) {
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

		private void inOrder(UserNode<K, V> node) {
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
}
