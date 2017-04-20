package gu1;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * HashtableCH is an re-sizeable array implementation of the Map interface.
 * HashtableCH maps keys to values using Bucket-objects, that also keep track of 
 * the following states: EMPTY, OCCUPIED or REMOVED.
 * This map uses closed hashing and cannot contain duplicate keys,
 * each key can map to at most one value.
 * This map also uses inner class implementations of the Iterable interface to traverse values and keys.
 * 
 * @author Jesper Anderberg, Filip Nilsson, Aron Polner, Ali Hassan, Szilveszter Dezsi
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of values maintained by this map
 */

public class HashtableCH<K,V> implements Map<K,V> {
    private Bucket<K,V>[] table;
    private int size;
    private int threshold;
    private double loadfactor = 0.7;
    
    /**
     * Constructs an empty map with an inital size of 11.
     */
    public HashtableCH( ) { 
    	this(11);
    }
    
    /**
     * Constructs an empty map with specified inital size.
     * @param size initial size
     */
    public HashtableCH( int size ) { 
    	table = (Bucket<K, V>[]) new Bucket[size];
		threshold = (int) (loadfactor * table.length);
		for (int i = 0; i < table.length; i++) {
			table[i] = new Bucket<K, V>();
		}
	}
    
	/**
	 * Grows (doubles in size) if this map is at capacity.
	 */
	private void grow() {
		Bucket<K, V>[] oldTable = table;
		table = (Bucket<K, V>[]) new Bucket[table.length * 2];
		size = 0;
		threshold = (int) (loadfactor * table.length);
		for (int i = 0; i < table.length; i++) {
			table[i] = new Bucket<K, V>();
		}
		for (int index = 0; index < oldTable.length; index++) {
			if (oldTable[index].state == Bucket.OCCUPIED)
				put(oldTable[index].key, oldTable[index].value);
		}
	}
    
    /**
     * Returns the hashcode (index) for the value associated with the specified key.
     * @param key specified key
     * @return hashcode (index) for value
     */
	public int hashIndex(K key) {
		int hashCode = key.hashCode();
		hashCode = hashCode % table.length;
		return (hashCode < 0) ? -hashCode : hashCode;
	}
    
    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value
     * is replaced by the specified value.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with key, or
     *         {@code null} if there was no mapping for key.
     */
	public V put(K key, V value) {
		V res = null;
		if (size >= threshold) {
			grow();
		}
		int hashIndex = hashIndex(key), removed = -1;
		while (table[hashIndex].state != Bucket.EMPTY && !key.equals(table[hashIndex].key)) {
			if (removed == -1 && table[hashIndex].state == Bucket.REMOVED)
				removed = hashIndex;
			hashIndex++;
			if (hashIndex == table.length)
				hashIndex = 0;
		}
		if (table[hashIndex].state == Bucket.OCCUPIED) {
			res = table[hashIndex].value;
			table[hashIndex].value = value;
		} else {
			if (removed != -1)
				hashIndex = removed;
			table[hashIndex].key = key;
			table[hashIndex].value = value;
			table[hashIndex].state = Bucket.OCCUPIED;
			size++;
		}
		return res;
	}
	
	/**
	 * Prints each key-value mapping in this map.
	 */
    public void list() {
		System.out.println("Tabellen innehåller " + size() + " element:");
		for (int i = 0; i < table.length; i++)
			System.out.println(i + ": key=" + table[i].key + " value=" + table[i].value + " state=" + table[i].state);
	}   
    
    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this map contains no mapping for the key
     */
	public V get(K key) {
		int hashIndex = hashIndex(key);
		while ((table[hashIndex].state != Bucket.EMPTY) && (!key.equals(table[hashIndex].key))) {
			hashIndex++;
			if (hashIndex == table.length)
				hashIndex = 0;
		}
		if (table[hashIndex].state == Bucket.OCCUPIED) {
			return table[hashIndex].value;
		}
		return null;
	}
    
    /**
     * Removes the mapping for a key from this map if it is present.
     * Returns the value to which this map previously associated the key, 
	 * or {@code null} if the map contained no mapping for the key.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with key, or
     *         {@code null} if there was no mapping for key.
     */
	public V remove(K key) {
		int hashIndex = hashIndex(key);
		while ((table[hashIndex].state != Bucket.EMPTY) && (!key.equals(table[hashIndex].key))) {
			hashIndex++;
			if (hashIndex == table.length) {
				hashIndex = 0;
			}
		}
		if (table[hashIndex].state == Bucket.OCCUPIED) {
			V tempValue = table[hashIndex].value;
			table[hashIndex].key = null;
			table[hashIndex].value = null;
			table[hashIndex].state = Bucket.REMOVED;
			size--;
			return tempValue;
		}
		return null;
	}
    
    /**
     * Returns the number of key-value mappings in this map.
     * @return the number of key-value mappings in this map
     */
    public int size() {
        return size;
    }
    
    /**
     * Returns {@code true} if this map contains no key-value mappings.
     * @return {@code true} if this map contains no key-value mappings
     */
	public boolean isEmpty() {
		return size()==0;
	}

    /**
     * Returns {@code true} if this map contains a mapping for the specified key. 
     *
     * @param key key whose presence in this map is to be tested
     * @return {@code true} if this map contains a mapping for the specified key
     */
	public boolean contains(K key) {
		return get(key)!=null;
	}

    /**
     * Removes all of the mappings from this map. The map will be empty
     * after this call returns.
     */
	public void clear() {
		for (int i = 0; i < table.length; i++) {
			if (table[i].state != Bucket.EMPTY) {
				table[i].key = null;
				table[i].value = null;
				table[i].state = Bucket.EMPTY;
			}
		}
		size = 0;
	}

    /**
     * Returns an Iterator over all the keys contained in this map.
     * @return an Iterator over all the keys contained in this map
     */
	public Iterator<K> keys() {
		return new KeyIterator();
	}
	
    /**
     * Inner class implementation of Iterator over keys
     */
	private class KeyIterator implements Iterator<K> {
		private ArrayList<K> keys = new ArrayList<K>();
		private int listIndex = 0;

		/**
		 * Constructs a new KeyIterator
		 */
		public KeyIterator() {
			for (int i = 0; i < table.length; i++)
				if (table[i].state == Bucket.OCCUPIED)
					keys.add(table[i].key);
		}

		/**
		 * Returns true if the iteration has more keys. 
		 * @return boolean
		 */
		public boolean hasNext() {
			return listIndex < keys.size();
		}

		/**
		 * Returns the next key in the iteration.
		 * @return the next key in the iteration
		 */
		public K next() {
			return keys.get(listIndex++);
		}

		/**
		 * Remove operation is not supported by this iterator.
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
    /**
     * Returns an Iterator over all the values contained in this map.
     * @return an Iterator over all the values contained in this map
     */
	public Iterator<V> values() {
		return new ValueIterator();
	}
	
    /**
     * Inner class implementation of Iterator over values
     */
	private class ValueIterator implements Iterator<V> {
		private ArrayList<V> values = new ArrayList<V>();
		private int listIndex = 0;

		/**
		 * Constructs a new ValueIterator
		 */
		public ValueIterator() {
			for (int i = 0; i < table.length; i++)
				if (table[i].state == Bucket.OCCUPIED)
					values.add(table[i].value);
		}

		/**
		 * Returns true if the iteration has more values. 
		 * @return boolean
		 */
		public boolean hasNext() {
			return listIndex < values.size();
		}

		/**
		 * Returns the next value in the iteration.
		 * @return the next value in the iteration
		 */
		public V next() {
			return values.get(listIndex++);
		}

		/**
		 * Remove operation is not supported by this iterator.
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}

