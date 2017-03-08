package gu1;

/**
 * 
 * @author Filip Nilsson
 *
 * The class BSTNode represents a node in a tree. Intended to be used together with a class with a tree implementation.
 * @param <K> the nodes key
 * @param <V> the nodes value
 */
public class BSTNode<K,V> {
    K key;
    V value;
    BSTNode<K,V> left;
    BSTNode<K,V> right;
    
    /**
     * 
     * @param key , the nodes key
     * @param value , the nodes value
     * @param left , the nodes left BSTNode instance
     * @param right , the nodes right BSTNode instance
     */
    public BSTNode( K key, V value, BSTNode<K,V> left, BSTNode<K,V> right ) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }
    
    /**
     * Recursive method.
     * @return the height of the tree.
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
     * Recursive method.
     * @return 1 + the number of nodes in the left subtree + the number of nodes in the right subtree.
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
     * Recursive method.
     * Prints the key + value of the node and the key + value of all the nodes in it's subtrees. 
     */
    public void print() {
        if( left != null)
            left.print();
        System.out.println(key + ": " + value);
        if( right != null )
            right.print();
    }
    
//    public void showTree() {
//        javax.swing.JOptionPane.showMessageDialog( null, new ShowBST<K,V>( this, 800,600 ), "Show tree", javax.swing.JOptionPane.PLAIN_MESSAGE );
//    }
}
