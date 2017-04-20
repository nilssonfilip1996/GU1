package gu1;
/**
 * A Media object is kept in a Bucket object and labeled as
 * either EMPTY, OCCUPIED or REMOVED. Each object
 * also holds a unique key which is its ID and a value which 
 * represents the entirety of the information inside the Media object.
 * @author Jesper Anderberg, Filip Nilsson, Aron Polner, Ali Hassan, Szilveszter Dezsi
 *
 * @param <K>
 * @param <V>
 */
class Bucket<K,V> {
    static final int EMPTY = 0, OCCUPIED = 1, REMOVED = 2;
    int state = EMPTY;
    K key;
    V value;
}
