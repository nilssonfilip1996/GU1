package gu1;

import java.util.ArrayList;
import java.util.Iterator;

public class MediaLibrary<K, V> implements Map<K, V> {
	private Shelf<K, V>[] table;
	private int size;
	private int threshold;
	private double loadfactor = 0.7;

	public MediaLibrary() {
		this(11);
	}

	public MediaLibrary(int size) {
		table = (Shelf<K, V>[]) new Shelf[size];
		threshold = (int) (loadfactor * table.length);
		for (int i = 0; i < table.length; i++) {
			table[i] = new Shelf<K, V>();
		}
	}

	private void grow() {
		Shelf<K, V>[] oldTable = table;
		table = (Shelf<K, V>[]) new Shelf[table.length * 2];
		size = 0;
		threshold = (int) (loadfactor * table.length);
		for (int i = 0; i < table.length; i++) {
			table[i] = new Shelf<K, V>();
		}
		for (int index = 0; index < oldTable.length; index++) {
			if (oldTable[index].state == Shelf.OCCUPIED)
				put(oldTable[index].key, oldTable[index].value);
		}
	}

	private int hashIndex(K key) {
		int hashCode = key.hashCode();
		hashCode = hashCode % table.length;
		return (hashCode < 0) ? -hashCode : hashCode;
	}

	public V put(K key, V value) {
		V res = null;
		if (size >= threshold) {
			grow();
		}
		int hashIndex = hashIndex(key), removed = -1;
		while (table[hashIndex].state != Shelf.EMPTY && !key.equals(table[hashIndex].key)) {
			if (removed == -1 && table[hashIndex].state == Shelf.REMOVED)
				removed = hashIndex;
			hashIndex++;
			if (hashIndex == table.length)
				hashIndex = 0;
		}
		if (table[hashIndex].state == Shelf.OCCUPIED) {
			res = table[hashIndex].value;
			table[hashIndex].value = value;
		} else {
			if (removed != -1)
				hashIndex = removed;
			table[hashIndex].key = key;
			table[hashIndex].value = value;
			table[hashIndex].state = Shelf.OCCUPIED;
			size++;
		}
		return res;
	}
	
	public boolean returnMedia(K key) {
		int hashIndex = hashIndex(key);
		while ((table[hashIndex].state != Shelf.EMPTY) && (!key.equals(table[hashIndex].key))) {
			hashIndex++;
			if (hashIndex == table.length) {
				hashIndex = 0;
			}
		}
		if (contains(key) && table[hashIndex].state==Shelf.BORROWED){
			table[hashIndex].state=Shelf.OCCUPIED;
			size++;
			return true;
		}
		return false;
	}

	public void print() {
		System.out.println("Tabellen innehåller " + size() + " element:");
		for (int i = 0; i < table.length; i++)
			System.out.println(i + ": key=" + table[i].key + " value=" + table[i].value + " state=" + table[i].state);
	}

	public V get(K key) {
		int hashIndex = hashIndex(key);
		while ((table[hashIndex].state != Shelf.EMPTY) && (!key.equals(table[hashIndex].key))) {
			hashIndex++;
			if (hashIndex == table.length)
				hashIndex = 0;
		}
		if ((table[hashIndex].state == Shelf.OCCUPIED) || (table[hashIndex].state == Shelf.BORROWED) ) {
			return table[hashIndex].value;
		}
		return null;
	}
	
	public V remove(K key) {
		int hashIndex = hashIndex(key);
		while ((table[hashIndex].state != Shelf.EMPTY) && (!key.equals(table[hashIndex].key))) {
			hashIndex++;
			if (hashIndex == table.length) {
				hashIndex = 0;
			}
		}
		if (table[hashIndex].state == Shelf.OCCUPIED) {
			V tempValue = table[hashIndex].value;
			table[hashIndex].key = null;
			table[hashIndex].value = null;
			table[hashIndex].state = Shelf.REMOVED;
			size--;
			return tempValue;
		}
		return null;
	}
	
	public V borrowMedia(K key) {
		int hashIndex = hashIndex(key);
		while ((table[hashIndex].state != Shelf.EMPTY) && (!key.equals(table[hashIndex].key))) {
			hashIndex++;
			if (hashIndex == table.length) {
				hashIndex = 0;
			}
		}
		if (table[hashIndex].state == Shelf.OCCUPIED) {
			V tempValue = table[hashIndex].value;
			table[hashIndex].state = Shelf.BORROWED;
			size--;
			return tempValue;
		}
		return null;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean contains(K key) {
		return get(key) != null;

	}

	public void clear() {
		for (int i = 0; i < table.length; i++) {
			if (table[i].state != Shelf.EMPTY) {
				table[i].key = null;
				table[i].value = null;
				table[i].state = Shelf.EMPTY;
			}
		}
		size = 0;
	}

	public Iterator<K> keys() {
		return new KeyIterator();
	}
	
	private class KeyIterator implements Iterator<K> {
		private ArrayList<K> keys = new ArrayList<K>();
		private int listIndex = 0;

		public KeyIterator() {
			for (int i = 0; i < table.length; i++)
				if (table[i].state == Shelf.OCCUPIED /* || table[i].state == Shelf.BORROWED kanske behövs sen */)
					keys.add(table[i].key);
		}

		public boolean hasNext() {
			return listIndex < keys.size();
		}

		public K next() {
			return keys.get(listIndex++);
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	public Iterator<V> values() {
		return new ValueIterator();
	}
	
	private class ValueIterator implements Iterator<V> {
		private ArrayList<V> values = new ArrayList<V>();
		private int listIndex = 0;

		public ValueIterator() {
			for (int i = 0; i < table.length; i++)
				if (table[i].state == Shelf.OCCUPIED /* || table[i].state == Shelf.BORROWED kanske behövs sen */)
					values.add(table[i].value);
		}

		public boolean hasNext() {
			return listIndex < values.size();
		}

		public V next() {
			return values.get(listIndex++);
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
