import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        Collections.sort(values);
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        if (values.size() == 0) {
            return bst;
        }

        bst.add(values.get(values.size()/2));
        BinarySearchTree<Integer> leftTree = minimalHeight(values.subList(0, values.size()/2));
        BinarySearchTree<Integer> rightTree = minimalHeight(values.subList((values.size()/2 + 1), values.size()));
        bst.root.leftChild = leftTree.root;
        bst.root.rightChild = rightTree.root;

        return bst;
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        boolean llrr = false;
        boolean lrlr = false;
        if (n1.key != n2.key) {
            return false;
        } if ( (!n1.hasLeftChild() && !n2.hasLeftChild() ) && (!n1.hasRightChild() && !n2.hasRightChild() ) ) { //if theyre the same and neither have children
            return true;
        }
        if ((n1.leftChild.key == n2.leftChild.key) && (n1.rightChild.key == n2.rightChild.key)) {
            llrr =  (isIsomorphic(n1.leftChild, n2.leftChild) && (isIsomorphic(n1.rightChild, n2.rightChild)));
        } if ((n1.leftChild.key == n2.rightChild.key) && (n1.rightChild.key == n2.leftChild.key)) {
            lrlr =  (isIsomorphic(n1.leftChild, n2.rightChild) && (isIsomorphic(n1.rightChild, n2.leftChild)));
        }
        return (llrr || lrlr);
    }
}
