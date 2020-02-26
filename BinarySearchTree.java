import java.util.*;
/**
 * Write a description of class BinarySearchTree here.
 *
 * @author Michael K. Tshimanga
 * @version (a version number or a date)
 */
public class BinarySearchTree<E extends Comparable<E>>
{
    private class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;
        
        public Node(T value) {
            this.value = value;
            left = null;
            right = null;
        }
    }
    private Node<E> root;
    
    public BinarySearchTree() {
        root = null;
    }
    
    public void insert(E value) {
        root = insertRec(root, value);
    }
    private Node<E> insertRec(Node<E> node, E value) {
        if (node == null) {
            node = new Node<>(value);
            return node;
        }
        if (value.compareTo(node.value) < 0)
            node.left = insertRec(node.left, value);
        else if (value.compareTo(node.value) > 0)
            node.right = insertRec(node.right, value);
        return node;
    }
    
    public void delete(E value) {
        root = deleteRec(root, value);
    }
    private Node<E> deleteRec(Node<E> node, E value) {
        if (node == null)
            return node;
        if (value.compareTo(node.value) < 0)
            node.left = deleteRec(node.left, value);
        else if (value.compareTo(node.value) > 0)
            node.right = deleteRec(node.right, value);
        else {
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            node.value = findMin();
            node.right = deleteRec(node.right, node.value);
        }
        return node;
    }
    public E findMin() {
        return findMinRec(root);
    }
    private E findMinRec(Node<E> node) {
        if (node.left == null)
            return node.value;
        return findMinRec(node.left);
    }
    public E findMax() {
        return findMaxRec(root);
    }
    private E findMaxRec(Node<E> node) {
        if (node.right == null)
            return node.value;
        return findMaxRec(node.right);
    }
    
    public E findNext(E value) {
        return findNextRec(root, null, value);
    }
    private E findNextRec(Node<E> node, Node<E> next, E value) {
        if (node == null)
            return null;
        if (value.equals(node.value)) {
            if (node.right != null)
                return findMinRec(node.right);
        } else if (value.compareTo(node.value) < 0) {
            next = node;
            return findNextRec(node.left, next, value);
        } else
            return findNextRec(node.right, next, value);
        return next.value;
    }
    private E findNextIter(Node<E> node, E value) {
        return null;
    }
    public E findPrev(E value) {
        return findPrevRec(root, null, value);
    }
    private E findPrevRec(Node<E> node, Node<E> prev, E value) {
        if (node == null)
            return null;
        if (value.equals(node.value)) {
            if (node.left != null)
                return findMaxRec(node.left);
        } else if (value.compareTo(node.value) < 0)
            return findPrevRec(node.left, prev, value);
        else {
            prev = node;
            return findPrevRec(node.right, prev, value);
        }
        return prev.value;
    }
    private E findPrevIter(Node<E> node, E value) {
        return null;
    }
    
    public static int[] getRandomArray(int n) {
        int[] arr = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            int num = rand.nextInt();
            for (int j = 0; j < i; j++)
                while (arr[j] == num)
                    num = rand.nextInt();
            arr[i] = num;
        }
        return arr;
    }
    public static int[] getSortedArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = n - i;
        return arr;
    }
}
