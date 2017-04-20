package gu1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * BinarySearchTree is a binary implemntation of the SearchTree interface.
 * BinarySearchTree uses an inner class implementation of the Comparator interface to sort elements.
 * BinarySearchTree uses an inner class implementation of the Iterable interface to traverse elements.
 * 
 * @author Jesper Anderberg, Filip Nilsson, Aron Polner, Ali Hassan, Szilveszter Dezsi
 *
 * @param <K> specified key data reference type
 * @param <V> specified value data reference type
 */
public class BinarySearchTree<K,V> implements SearchTree<K,V> {
    private Comparator<K> comparator;
    private BSTNode<K,V> tree;
    private int size;
    
    /**
     * Constructs a new, empty tree set, sorted according to the natural ordering of its elements.
     */
    public BinarySearchTree() {
        comparator = new Comp();
    }
    
    /**
     * Constructs a new, empty tree set, sorted according to the specified comparator.
     * @param comp - Comparator
     */
    public BinarySearchTree( Comparator<K> comp ) {
        comparator = comp;
    }
    
    /**
     * Associates the specified value with the specified key in this tree.
     * @param key - key with which the specified value is to be associated
     * @param value - value to be associated with the specified key
     */
    public void put(K key, V value) {
        tree = put(tree,key,value);        
    } 
    
    /**
     * Associates the specified value with the specified key in this tree.
     * Tree sorts and balances according to the natural ordering of its elements.
     * @param node - node to be sorted and balanced
     * @param key - key with which the specified value is to be associated
     * @param value - value to be associated with the specified key
     * @return sorted and balanced tree
     */
    private BSTNode<K,V> put(BSTNode<K,V> node, K key, V value) {
        if( node == null ) {
            node = new BSTNode<K,V>( key, value, null, null );
            size++;
        } else {
            if(comparator.compare(key,node.key)<0) {
                node.left = put(node.left,key,value);
            } else if(comparator.compare(key,node.key)>0) {
                node.right = put(node.right,key,value);
            }
        }
        return node;
    }
    
    /**
     * Removes the value associated with the specified key in this tree, if present.
     * @param key - specified key
     * @return removed value, or <tt>null</tt> if not present in this tree
     */
    public V remove(K key) {
        V value = get( key );
        if(value!=null) {
            tree = remove(tree,key);
            size--;
        }
        return value;
    }
    
    /**
     * Removes the value associated with the specified key in this tree, if present.
     * Tree sorts and balances according to the natural ordering of its elements.
     * @param node - node to be sorted and balanced
     * @param key - specified key
     * @return sorted and balanced tree
     */
    private BSTNode<K,V> remove(BSTNode<K,V> node, K key) {
        int compare = comparator.compare(key,node.key);
        if(compare==0) {
            if(node.left==null && node.right==null)
                node = null;
            else if(node.left!=null && node.right==null)
                node = node.left;
            else if(node.left==null && node.right!=null)
                node = node.right;
            else {
                BSTNode<K,V> min = getMin(node.right);
                min.right = remove(node.right,min.key);
                min.left = node.left;
                node = min;
            }
        } else if(compare<0) {
            node.left = remove(node.left,key);
        } else {
            node.right = remove(node.right,key);
        }
        return node;
    }

    /**
     * Returns the first (lowest) node contained in this tree.
     * @return the first (lowest) node contained in this tree
     */
    private BSTNode<K,V> getMin(BSTNode<K,V> node) {
        while(node.left!=null)
            node = node.left;
        return node;
    }
    
    /**
     * Returns the value associated with the specified key in this tree, if present.
     * @param key - specified key
     * @return value associated with the specified key, or <tt>null</tt> if not present in this tree
     */
    public V get(K key) {
        BSTNode<K,V> node = find( key );
        if(node!=null)
            return node.value;
        return null;
    }
    
    /**
     * Returns <tt>true</tt> if this tree contains specified key, otherwise <tt>false</tt>. 
     * @param key - specified key
     * @return <tt>true</tt> if this tree contains specified key, otherwise <tt>false</tt>
     */
    public boolean contains( K key ) {
        return find( key ) != null;
    }
    
    /**
     * Returns node containing specified key. 
     * @param key - specified key
     * @return node containing specified key
     */
    private BSTNode<K,V> find(K key) {
        int res;
        BSTNode<K,V> node=tree;
        while( ( node != null ) && ( ( res = comparator.compare( key, node.key ) ) != 0 ) ) {
            if( res < 0 )
                node = node.left;
            else
                node = node.right;
        }
        return node;
    }
    
    /**
     * Returns the number of levels of this tree.
     * @return number of levels of this tree
     */
    public int height() {
        return height( tree );
    }
    
    /**
     * Returns the number of levels of this node.
     * @param node - node to be measured
     * @return number of levels of this node
     */
	private int height(BSTNode<K, V> node) {
		if (node == null)
			return -1;
		return 1 + Math.max(height(node.left), height(node.right));
	}
    
    
    /**
     * Returns an iterator over the values contained in this tree.
     * @return an iterator over the values contained in this tree
     */
    public Iterator<V> iterator() {
        return new Iter();
    }
    
    /**
     * Inner class implementation of Iterator.
     */
    private class Iter implements Iterator<V> {
        ArrayList<V> list = new ArrayList<V>();
        int index = -1;
        
        /**
         * Constructs new ordered iterator
         */
        public Iter() {
            inOrder(tree);
        }
        
        /**
         * Constructs new ordered iterator
         * @param node - target tree
         */
        private void inOrder(BSTNode<K,V> node) {
            if(node!=null) {
                inOrder(node.left);
                list.add(node.value);
                inOrder(node.right);
            }
        }
        
		/**
		 * Returns true if the iteration has more elements. 
		 * @return boolean
		 */
        public boolean hasNext() {
            return index<list.size()-1;
        }
        
		/**
		 * Returns the next element in the iteration.
		 * @return the next element in the iteration
		 * @throws NoSuchElementException - if the iteration has no more elements
		 */
        public V next() {
            if(!hasNext())
                throw new NoSuchElementException();
            index++;
            return list.get(index);
        }
        
		/**
		 * Remove operation is not supported by this iterator.
		 * @throws UnsupportedOperationException
		 */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    /**
     * Returns the number of key-value nodes contained in this tree.
     * @return the number of key-value nodes contained in this tree
     */
    public int size() {
    	if( tree == null )
    		return 0;
    	return tree.size();
    }
    
    /**
     * Returns a list of the keys contained in this tree.
     * @return list of the keys contained in this tree
     */
    public List<K> keys(){
    	ArrayList<K> list = new ArrayList<K>();
    	keys(tree, list);
        return list;
    }
    
    /**
     * Recursive algorithm that builds list.
     * @param node - target tree
     * @param list - target list
     */
    private void keys(BSTNode<K,V> node, ArrayList<K> list){
    	if( node != null ) {
    		keys( node.left, list );
    		list.add( list.size(), node.key );
    		keys( node.right, list );
    	}
    }
    
    /**
     * Returns a list of the values contained in this tree.
     * @return list of the values contained in this tree
     */
	public List<V> values() {
		ArrayList<V> list = new ArrayList<V>();
		values(tree, list);
		return list;
	}
    
    /**
     * Recursive algorithm that builds list.
     * @param node - target tree
     * @param list - target list
	 */
	private void values(BSTNode<K, V> node, ArrayList<V> list) {
		if (node != null) {
			values(node.left, list);
			list.add(list.size(), node.value);
			values(node.right, list);
		}
	}
	
    /**
     * Returns the first (lowest) value contained in this tree.
     * @return the first (lowest) value contained in this tree
     */
    public V first(){
    	BSTNode<K, V> node = tree;
    	if (node == null)
    		return null;
    	while (node.left != null)
    		node = node.left;
        return node.value;
    }

    /**
     * Returns the last (greatest) value contained in this tree.
     * @return the last (greatest) value contained in this tree
     */
    public V last(){
    	BSTNode<K, V> node = tree;
    	if (node == null)
    		return null;
    	while (node.right != null)
    		node = node.right;
        return node.value;
    }
    
    /**
     * Returns root node for this tree.
     * @return root node for this tree
     */
    public BSTNode<K,V> root() {
        return tree;
    }

	/**
	 * Inner class impletation of Comparator.
	 */
    private class Comp implements Comparator<K> {
        public int compare( K key1, K key2 ) {
            Comparable<K> k1 = ( Comparable<K> )key1;
            return k1.compareTo( key2 );
        }
    }
    
    /**
     * Prints keys and values of the nodes in this node.
     * Format: <tt>key : value</tt>
     */
    public void print() {
    	print(tree);
    }
    
    /**
     * Prints keys and values of the nodes in this tree in order.
     * Format: <tt>key:value</tt>
     * @param node - node to be printed
     */
    private void print(BSTNode<K,V> node) {
		if (node != null) {
			print(node.left);
			System.out.println(node.key + ":" + node.value);
			print(node.right);
		}
    }
}
