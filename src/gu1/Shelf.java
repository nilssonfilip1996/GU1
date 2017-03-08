package gu1;

class Shelf<K,V> {
    static final int EMPTY = 0, OCCUPIED = 1, REMOVED = 2, BORROWED = 3;
    int state = EMPTY;
    K key;
    V value;
}
