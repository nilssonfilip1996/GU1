package gu1;
import java.util.ArrayList;
import java.util.Iterator;

public class HashtableCH<K,V> implements Map<K,V> {
    private Bucket<K,V>[] table;
    private int size;
    private int threshold;
    private double loadfactor = 0.7;
    
    public HashtableCH( ) { 
    	this(11);
    }
    
    public HashtableCH( int size ) { 
        table = (Bucket<K,V>[])new Bucket[ size ];
        threshold = (int)(loadfactor*table.length);
        for( int i = 0; i < table.length; i++ ) {
            table[ i ] = new Bucket<K,V>();
        }
    }
    
    private void grow() {
    	Bucket<K,V>[] oldTable = table;
        table = (Bucket<K,V>[])new Bucket[ table.length*2 ];
    	size = 0;
    	threshold = (int)(loadfactor*table.length);
        for( int i = 0; i < table.length; i++ ) {
            table[ i ] = new Bucket<K,V>();
        }
    	for(int index=0; index<oldTable.length; index++) {
    		if( oldTable[index].state == Bucket.OCCUPIED )
    			put( oldTable[index].key, oldTable[ index ].value);
    	}
    }
    
    private int hashIndex( K key ) {
        int hashCode = key.hashCode();
        hashCode = hashCode % table.length;
        return ( hashCode < 0 ) ? -hashCode : hashCode;
    }
    
    public V put( K key, V value ) {
    	V res = null;
    	if( size >= threshold ) {
    		grow();
    	}
    	int hashIndex = hashIndex( key ), removed = -1, counter = 0;
    	while( (counter<table.length) && (table[ hashIndex ].state != Bucket.EMPTY) && !key.equals(table[ hashIndex ].key) ) {
    		if( removed == -1 && table[ hashIndex ].state == Bucket.REMOVED )
    			removed = hashIndex;
    		counter++;
    		hashIndex++;
    		if(hashIndex==table.length)
    			hashIndex=0;
    	}
    	if( key.equals(table[ hashIndex ].key) ) {
    		res = table[ hashIndex ].value;
    		table[ hashIndex ].value = value;
    	} else {
    		if( removed != -1 ) 
    			hashIndex = removed;
            table[ hashIndex ].key = key;
            table[ hashIndex ].value = value;
            table[ hashIndex ].state = Bucket.OCCUPIED;
            size++;
    	}
    	return res;
    }

	public Iterator<K> keys() {
		ArrayList<K> keys = new ArrayList<K>();
		for(int i=0; i<table.length; i++)
		    if( table[ i ].state == Bucket.OCCUPIED )
			    keys.add(table[ i ].key);
		return keys.iterator();
	}
	
    public void list() {
        System.out.println( "Tabellen innehåller " + size() + " element:" );
        for(int i=0; i<table.length; i++)
            System.out.println(i+": key=" + table[ i ].key +" value=" + table[ i ].value + " state=" + table[ i ].state );
    }    
    
    public V get( K key ) {
    	int hashIndex = hashIndex( key ), counter = 0;
    	while( ( counter < table.length ) && ( table[ hashIndex ].state !=
    	Bucket.EMPTY ) && !key.equals( table[ hashIndex ].key ) ) {
    	counter++;
    	hashIndex++;
    	if(hashIndex==table.length)
    	hashIndex=0;
    	}
    	if( key.equals( table[ hashIndex ].key ) )
    	return table[ hashIndex ].value;
        return null;
    }
    
    public V remove( K key ) {
    	V res = null;
    	int hashIndex = hashIndex( key ), counter = 0;
    	while( ( counter < table.length ) && ( table[ hashIndex ].state !=
    	Bucket.EMPTY ) && !key.equals( table[ hashIndex ].key ) ) {
    	counter++;
    	hashIndex++;
    	if(hashIndex==table.length)
    	hashIndex=0;
    	}
    	if( key.equals( table[ hashIndex ].key ) ) {
    	res = table[ hashIndex ].value;
    	table[ hashIndex ].key = null;
    	table[ hashIndex ].value = null;
    	table[ hashIndex ].state = Bucket.REMOVED;
    	size--;
    	}
    	return res;
    }
    
    public int size() {
        return size;
    }
    
	public boolean isEmpty() {
		return size()==0;
	}

	public boolean contains(K key) {
		return get(key)!=null;
	}

	public void clear() {
		for(int index=0; index<table.length; index++) {
			if(table[ index ].state != Bucket.EMPTY) {
			table[ index ].key = null;
			table[ index ].value = null;
			table[ index ].state = Bucket.EMPTY;
			}
			}
			size = 0;
	}

	public Iterator<V> values() {
		ArrayList<V> values = new ArrayList<V>();
		for(int i=0; i<table.length; i++)
		if( table[ i ].state == Bucket.OCCUPIED )
		values.add(table[ i ].value);
		return values.iterator();
		
	}
}

