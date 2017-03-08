package collections;

import java.util.Iterator;

import javax.swing.JOptionPane;

import collections.ArrayList;
import collections.LinkedList;

/**
 * The Class HashTableOH represents a HashTable implementation with open
 * hashing. Elements in the hashtable are placed after their hashIndex value.
 * Each index of the hashtable is an instance of LinkedList meaning that the
 * hashtable has no max amount of elements to be stored. It grows dynamically.
 * 
 * @author Rolf Axelsson
 * @edit Filip Nilsson
 */
public class HashtableOH<K, V> implements Map<K, V> {
	private LinkedList<Entry<K, V>>[] table;
	private int size;

	/**
	 * Creates a new instance of HashtableOH
	 * 
	 * @param the
	 *            size of the hashtable.
	 **/
	public HashtableOH(int size) {
		table = (LinkedList<Entry<K, V>>[]) new LinkedList[size];
		for (int i = 0; i < size; i++) {
			table[i] = new LinkedList<Entry<K, V>>();
		}
	}

	/**
	 * Calculates the hashIndex of a given key
	 * 
	 * @param key
	 * @return the index of the hashtable to store key
	 */
	private int hashIndex(K key) {
		int hashCode = key.hashCode();
		hashCode = hashCode % table.length;
		return (hashCode < 0) ? -hashCode : hashCode;
	}

	/**
	 * Adds a element to the hashtable. Element is stored within a key instance.
	 * If the hashtable already contains a entry instance with the same key, no
	 * entry instance is created, except the Entry is given a new value.
	 * 
	 * @param key
	 *            , key of the Entry object.
	 * @param value
	 *            , value of the Entry object.
	 * @return if there was a previous entry with the same key, that entries
	 *         previous value is returned. Otherwise null is returned.
	 */
	public V put(K key, V value) {
		V res = null;
		int hashIndex = hashIndex(key);
		Entry<K, V> entry = new Entry<K, V>(key, value);
		int index = table[hashIndex].indexOf(entry);
		if (index == -1) {
			table[hashIndex].addFirst(entry);
			size++;
		} else {
			res = table[hashIndex].set(index, entry).value;
		}
		return res;
	}

	/**
	 * Prints the key and value of all the Entry instances within the hashtable.
	 */
	public void list() {
		Entry<K, V> entry;
		for (int i = 0; i < table.length; i++) {
			System.out.print(i + ":");
			for (int j = 0; j < table[i].size(); j++) {
				entry = table[i].get(j);
				System.out.print(" <" + entry.key + "," + entry.value + ">");
			}
			System.out.println();
		}
	}

	/**
	 * If there is an Entry instance with a specified key, then it's value is
	 * returned. Otherwise null is returned.
	 * 
	 * @param key
	 *            , key to be searched for.
	 * @return the given key's corresponding value. If none is found, then null
	 *         is returned.
	 */
	public V get(K key) {
		int hashIndex = hashIndex(key);
		for (int i = 0; i < table[hashIndex].size(); i++) {
			if (table[hashIndex].get(i).key.equals(key)) {
				return table[hashIndex].get(i).value;
			}
		}
		return null;
	}

	/**
	 * If there is an Entry instance with a specified key, then that Entry
	 * instance is removed and it's value is returned. Otherwise null is
	 * returned.
	 * 
	 * @param key
	 *            , key to be searched for.
	 * @return the given key's corresponding value. If none is found, then null
	 *         is returned.
	 */
	public V remove(K key) {
		V removedValue;
		int hashIndex = hashIndex(key);
		for (int i = 0; i < table[hashIndex].size(); i++) {
			if (table[hashIndex].get(i).key.equals(key)) {
				removedValue = table[hashIndex].get(i).value;
				table[hashIndex].get(i).key = null; // n�dv�ndigt??
				table[hashIndex].get(i).value = null; // n�dv�ndigt??
				table[hashIndex].remove(i);
				size--;
				return removedValue;
			}
		}
		return null;
	}

	/**
	 * @return the total number of Entry instances within the hashtable.
	 */
	public int size() {
		int count = 0;
		for (int i = 0; i < table.length; i++) {
			count += table[i].size();
		}
		return count;
	}

	/**
	 * @return true if this map contains no Entry instances otherwise false.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Method checks if there is an Entry instance containing a specific key.
	 * @param key , key to be looked for
	 * @return true if key is found, false otherwise.
	 */
	public boolean containsKey(K key) {
		return get(key) != null;
	}

	/**
	 * Removes all of the Entry instances in the hashtable. The hashtable will be empty after this call returns.
	 */
	public void clear() {
		for (int i = 0; i < table.length; i++) {
			table[i].clear();
		}
	}

	/**
	 * Returns an Iterator view of the keys contained in this hashtable.
	 *	@return an Iterator-view of the keys contained in this hashtable.
	 */
	public Iterator<K> keys() {
		ArrayList<K> list = new ArrayList<K>();
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].size(); j++) {
				list.add(table[i].get(j).key);
			}
		}
		return list.iterator();
	}

	/**
	 * Returns an Iterator view of the values contained in this map.
	 * @return an Iterator-view of the values contained in this map
	 */
	public Iterator<V> values() {
		ArrayList<V> list = new ArrayList();
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].size(); j++) {
				list.add(table[i].get(j).value);
			}
		}
		return list.iterator();
	}

	public static void main(String[] args) {
		HashtableOH<String, String> table = new HashtableOH<String, String>(4);
		table.put("hej", "hello");
		table.put("r�d", "red");
		table.put("vit", "white");
		table.put("s�ng", "bed");
		table.put("svart", "black");
		table.put("gul", "yellow");
		table.put("dator", "computer");
		table.put("sn�", "snow");
		table.put("bl�", "blue");
		table.put("gr�n", "green");
		table.put("hus", "house");
		table.list();
		String key = JOptionPane.showInputDialog("Mata in nyckel: ");
		System.out.println("nyckel:" + key + " finns eller ej: " + table.containsKey(key));
		table.list();
		System.out.println("Size: " + table.size());
		Iterator<String> keys = table.keys();
		System.out.println("-----------KEYS------------");
		while (keys.hasNext()) {
			System.out.println("Iterator key values: " + keys.next());
		}
		System.out.println("-----------VALUES------------");
		Iterator<String> values = table.values();
		while (values.hasNext()) {
			System.out.println("Iterator key values: " + values.next());
		}
		// table.clear();
		// table.list();
		// System.out.println("nyckel:" + key + " ger: " + table.get(key));
	}
}
