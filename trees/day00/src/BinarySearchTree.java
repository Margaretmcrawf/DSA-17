import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {
    TreeNode<T> root;
    private int size;

    public int size() {
        return size;
    }

    public boolean contains(T key) {
        return find(root, key) != null;
    }

    /**
     * Add a node to the BST. Internally calls insert to recursively find the new node's place
     */
    public boolean add(T key) {
        if (find(root, key) != null) return false;
        root = insert(root, key);
        size++;
        return true;
    }

    public void addAll(T[] keys) {
        for (T k : keys)
            add(k);
    }

    public List<T> inOrderTraversal() {
        ArrayList<T> values =  new ArrayList<T>();
        if (size == 0) {
            return values;
        } else if (size == 1) {
            values.add(root.key);
            return values;
        } else {
            TreeNode<T> tn = root;
            //get the minimum treenode, so you can move there.
            while (tn.leftChild != null) {
                tn = tn.leftChild;
            }
            values.add(tn.key);
            while (findSuccessor(tn) != null) {
                tn = findSuccessor(tn);
                values.add(tn.key);
            }
            return values;
        }
    }

    /**
     * Deletes a node from the BST using the following logic:
     * 1. If the node has a left child, replace it with its predecessor
     * 2. Else if it has a right child, replace it with its successor
     * 3. If it has no children, simply its parent's pointer to it
     */
    public boolean delete(T key) {
        TreeNode<T> toDelete = find(root, key);
        if (toDelete == null) {
            System.out.println("Key does not exist");
            return false;
        }
        TreeNode<T> deleted = delete(toDelete);
        if (toDelete == root) {
            root = deleted;
        }
        size--;
        return true;
    }

    private TreeNode<T> delete(TreeNode<T> n) {
        // Recursive base case
        if (n == null) return null;

        TreeNode<T> replacement;

        if (n.isLeaf())
            // Case 1: no children
            replacement = null;
        else if (n.hasRightChild() != n.hasLeftChild()) {
            // Case 2: one child
            replacement = (n.hasRightChild()) ? n.rightChild : n.leftChild; // replacement is the non-null child
        }
        else {
            // Case 3: two children
            replacement = findSuccessor(n);
            delete(replacement);
            replacement.moveChildrenFrom(n);
        }
        // Put the replacement in its correct place, and set the parent.
        n.replaceWith(replacement);
        return replacement;
    }

    public T findPredecessor(T key) {
        TreeNode<T> n = find(root, key);
        if (n != null) {
            TreeNode<T> predecessor = findPredecessor(n);
            if (predecessor != null)
                return predecessor.key;
        }
        return null;
    }

    public T findSuccessor(T key) {
        TreeNode<T> n = find(root, key);
        if (n != null) {
            TreeNode<T> successor = findSuccessor(n);
            if (successor != null)
                return successor.key;
        }
        return null;
    }

    private TreeNode<T> findPredecessor(TreeNode<T> n) {
        // https://www.quora.com/How-can-you-find-successors-and-predecessors-in-a-binary-search-tree-in-order
        //above was a really helpful reference for how to go about finding the predecessor and successor
        if (n == null) {
            return null;
        }
        if (n.leftChild != null) {
            TreeNode<T> current = n.leftChild;

            while (current.rightChild != null) { //while current key is less than n's key
                current = current.rightChild;
            }
            return current;
        } else {
            TreeNode<T> y =  n.parent;
            TreeNode<T> x = n;
            while ((y != null) && (x == y.leftChild)) {
                x = y;
                y = y.parent;
            }
            return y;
        }
    }

    private TreeNode<T> findSuccessor(TreeNode<T> n) {
        if (n == null) {
            return null;
        } else if (n.rightChild != null) {
            TreeNode<T> current = n.rightChild;
            while (current.leftChild != null) {
                current = current.leftChild;
            }
            return current;
        } else {
            TreeNode<T> y = n.parent;
            TreeNode<T> x = n;
            while (y != null && x == y.rightChild)
            {
                x = y;
                y = y.parent;
            }
            return y;
        }
    }

    /**
     * Returns a node with the given key in the BST, or null if it doesn't exist.
     */
    private TreeNode<T> find(TreeNode<T> currentNode, T key) {
        if (currentNode == null)
            return null;
        int cmp = key.compareTo(currentNode.key);
        if (cmp < 0)
            return find(currentNode.leftChild, key);
        else if (cmp > 0)
            return find(currentNode.rightChild, key);
        return currentNode;
    }

    /**
     * Recursively insert a new node into the BST
     */
    private TreeNode<T> insert(TreeNode<T> node, T key) {
        if (node == null) return new TreeNode<>(key);

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.leftChild = insert(node.leftChild, key);
            node.leftChild.parent = node;
        } else {
            node.rightChild = insert(node.rightChild, key);
            node.rightChild.parent = node;
        }
        return node;
    }
}
