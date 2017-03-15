package gu1;
import java.util.Iterator;
import java.util.List;

/**
 * An object that maps keys to values in a tree structure.
 *
 * @param <K> specified key data reference type
 * @param <V> specified value data reference type
 */
public interface SearchTree<K,V> {
	
    /**
     * Associates the specified value with the specified key in this tree.
     * @param key - key with which the specified value is to be associated
     * @param value - value to be associated with the specified key
     */
    public void put(K key, V value);
    
    /**
     * Removes the value associated with the specified key in this tree, if present.
     * @param key - specified key
     * @return removed value, or <tt>null</tt> if not present in this tree
     */
    public V remove(K key);
    
    /**
     * Returns the value associated with the specified key in this tree, if present.
     * @param key - specified key
     * @return value associated with the specified key, or <tt>null</tt> if not present in this tree
     */
    public V get(K key);
    
    /**
     * Returns <tt>true</tt> if this tree contains specified key, otherwise <tt>false</tt>. 
     * @param key - specified key
     * @return <tt>true</tt> if this tree contains specified key, otherwise <tt>false</tt>
     */
    public boolean contains(K key);
    
    /**
     * Returns the number of levels of this tree.
     * @return number of levels of this tree
     */
    public int height();
    
    /**
     * Returns an iterator over the values contained in this tree.
     * @return an iterator over the values contained in this tree
     */
    public Iterator<V> iterator();
    
    /**
     * Returns the number of key-value nodes contained in this tree.
     * @return the number of key-value nodes contained in this tree
     */
    public int size();
    
    /**
     * Returns a list of the keys contained in this tree.
     * @return list of the keys contained in this tree
     */
    public List<K> keys();
    
    /**
     * Returns a list of the values contained in this tree.
     * @return list of the values contained in this tree
     */
    public List<V> values();
    
    /**
     * Returns the first (lowest) value contained in this tree.
     * @return the first (lowest) value contained in this tree
     */
    public V first();
    
    /**
     * Returns the last (highest) value contained in this tree.
     * @return the last (highest) value contained in this tree
     */
    public V last();
}
