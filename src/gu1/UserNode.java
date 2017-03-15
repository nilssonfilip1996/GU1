package gu1;

/**
 * UserNode is a container class used with classes User and UserDatabase.
 * User objects are mapped with key-value pair parameters within a UserDatabase tree map structure. 
 * 
 * @author Jesper Anderberg, Filip Nilsson, Aron Polner, Ali Hassan, Szilveszter Dezsi
 *
 * @param <K> specified key data reference type
 * @param <V> specified key value reference type
 */
public class UserNode<K,V> {
    K key;
    V value;
    UserNode<K,V> left;
    UserNode<K,V> right;
    
    /**
     * Constructs new UserNode.
     * @param key - specified key
     * @param value - specified value
     * @param left - left child
     * @param right - right child
     */
    public UserNode( K key, V value, UserNode<K,V> left, UserNode<K,V> right ) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }
    
    /**
     * Returns the number of levels of this node.
     * @return number of levels of this node
     */
    public int height() {
        int leftH = -1, rightH = -1;
        if( left != null )
            leftH = left.height();
        if( right != null )
            rightH = right.height();
        return 1 + Math.max( leftH, rightH );
    }
    
    /**
     * Returns the number of nodes contained in this node.
     * @return the number of nodes contained in this node
     */
    public int size() {
        int leftS = 0, rightS = 0;
        if( left != null )
            leftS = left.size();
        if( right != null )
            rightS = right.size();
        return 1 + leftS + rightS;
    }
    
    /**
     * Prints keys and values of the nodes in this node.
     * Format: <tt>key : value</tt>
     */
    public void print() {
        if( left != null)
            left.print();
        System.out.println(key + " : " + value);
        if( right != null )
            right.print();
    }
}
