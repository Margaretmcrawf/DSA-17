import static java.lang.Math.max;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    /**
     * Delete a key from the tree rooted at the given node.
     */
    @Override
    TreeNode<T> delete(TreeNode<T> n, T key) {
        n = super.delete(n,key);
        if (n != null) {
            int rHeight = (n.hasRightChild()) ? n.rightChild.height  : -1;
            int lHeight = (n.hasLeftChild()) ? n.leftChild.height : -1;
            n.height = max(rHeight, lHeight) + 1;
            return balance(n);
        }
        return null;
    }
    /**
     * Insert a key into the tree rooted at the given node.
     */
    @Override
    TreeNode<T> insert(TreeNode<T>  n, T key) {
        n = super.insert(n,key);
        if (n != null) {
            int rHeight = (n.hasRightChild()) ? n.rightChild.height  : -1;
            int lHeight = (n.hasLeftChild()) ? n.leftChild.height : -1;
            n.height = max(rHeight, lHeight) + 1;
            return balance(n);
        }
        return null;
    }

    /**
     * Delete the minimum descendant of the given node.
     */
    @Override
    TreeNode<T> deleteMin(TreeNode<T> n){
        n = super.deleteMin(n);
        if(n != null) {
            n.height = 1 + max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    // Return the height of the given node. Return -1 if null.
    private int height(TreeNode<T> n) {
        if (n == null) {
            return -1;
        } else {
            return max(height(n.rightChild), height(n.leftChild)) + 1;
        }
    }

    public int height() {
        return max(height(root),0);
    }

    // Restores the AVL tree property of the subtree.
    TreeNode<T> balance(TreeNode<T> n) {
        if  (balanceFactor(n) < -1) {
            //very left heavy
            if (balanceFactor(n.leftChild) > 0) {
                //left child is right heavy
                n.leftChild = rotateLeft(n.leftChild);
            }
            n = rotateRight(n);
        }
        if (balanceFactor(n) > 1) {
            //very right heavy
            if (balanceFactor(n.rightChild) < 0) {
                //right child is left heavy
                n.rightChild = rotateRight(n.rightChild);
            }
            n = rotateLeft(n);
        }
        return n;
    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     */
    private int balanceFactor(TreeNode<T> n) {
        int rightHeight = height(n.rightChild);
        int leftHeight = height(n.leftChild);
        return rightHeight - leftHeight;
    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateRight(TreeNode<T> n) {
        TreeNode<T> m = n.leftChild;
        TreeNode<T> beta = n.leftChild.rightChild;
        n.leftChild.rightChild = n;
        n.leftChild = beta;
        n.height = height(n);
        m.height = height(m);
        return m;
    }

    /**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateLeft(TreeNode<T> n) {
        TreeNode<T> m = n.rightChild;
        TreeNode<T> beta = n.rightChild.leftChild;
        n.rightChild.leftChild = n;
        n.rightChild = beta;
        n.height = height(n);
        m.height = height(m);
        return m;
    }
}
