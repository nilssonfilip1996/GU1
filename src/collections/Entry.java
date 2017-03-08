package collections;

/**
 * The class Entry represents an element in a Map implementation.
 * It holds a key and a value.
 * 
 * @author Filip Nilsson
 *
 */
class Entry<K,V> {
    K key;
    V value;
    
    /**
     * Constructor. Instantiates the variables key and value.
     * @param key , value to be assigned the key variable
     * @param value , value to be assigned the value variable
     */
    public Entry( K key, V value ) {
        this.key = key;
        this.value = value;
    }
    
    /**
     * Compares two keys and returns true if they are equal, otherwise false.
     */
    public boolean equals( Object obj ) {
        if( !(obj instanceof Entry) )
            return false;
        Entry<K,V> entry = ( Entry<K,V> )obj;
        return key.equals( entry.key );
    }
}
