package gu1;
/**
 * Every Media object is kept on a shelf and labeled as
 * either EMPTY, OCCUPIED, REMOVED or BORROWERED. Each object
 * also holds a unique key which is its ID and a value which 
 * represents the entirety of the information infide the Media object.
 * @author Jesper Anderberg, Filip Nilsson, Aron Polner, Ali Hassan, Szilveszter Dezsi
 *
 * @param <K>
 * @param <V>
 */
class Shelf<K,V> {
    static final int EMPTY = 0, OCCUPIED = 1, REMOVED = 2, BORROWED = 3;
    int state = EMPTY;
    K key;
    V value;
}
