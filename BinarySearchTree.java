import java.util.*;
/**
 * Write a description of class BinarySearchTree here.
 *
 * @author Michael K. Tshimanga
 * @version (a version number or a date)
 */
public class BinarySearchTree<E extends Comparable<E>>
{
    class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;
        
        public Node(T value) {
            this.value = value;
            left = null;
            right = null;
        }
    }
    Node<E> root;
    
    public BinarySearchTree() {
        root = null;
    }
    
    public void insert(E value) {
        root = insertRec(root, value);
    }
    Node<E> insertRec(Node<E> node, E value) {
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
    Node<E> insertIter(Node<E> node, E value) {
        Node<E> x = node;
        Node<E> parent = null;
        if (node == null)
            return new Node<>(value);
        while (x != null) {
            parent = x;
            if (value.compareTo(x.value) < 0)
                x = x.left;
            else
                x = x.right;
        }
        if (value.compareTo(parent.value) < 0)
            parent.left = new Node<>(value);
        else
            parent.right = new Node<>(value);
        return node;
    }
    
    public void delete(E value) {
        root = deleteIter(root, value);
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
            node.value = findMinRec(node);
            node.right = deleteRec(node.right, node.value);
        }
        return node;
    }
    private Node<E> deleteIter(Node<E> node, E value) {
        return null;
        /** Node<E> parent = null, x = node;
        boolean left = false;
        
        if (node == null)
            return node;
        
        while (x != null) {
            if (x.value.equals(value))
                break;
            
            parent = x;
            if (value.compareTo(x.value) < 0) {
                left = true;
                x = x.left;
            } else {
                left = false;
                x = x.right;
            }
        }
        
        if (parent == null) {
            if (x != null) {
                if (x.left == null && x.right == null)
                    return null;
                if (x.left != null && x.right != null) {
                    Node<E> y = x;
                    x = x.right;
                    boolean right = x.left == null;
                    while (x.left != null) {
                        y = x;
                        x = x.left;
                    }
                    if (right)
                        y.right = x.right;
                    else
                        y.left = x.left;
                    x.right = null;
                    
                    Node<E> next = x;
                    x.value = next.value;
                } else if (x.left != null)
                    x = x.left;
                else
                    x = x.right;
            }
            return x;
        }
        
        if (left) {
            if (x != null) {
                if (x.left == null && x.right == null)
                    parent.left = null;
                if (x.left != null && x.right != null) {
                    Node<E> y = x;
                    x = x.right;
                    boolean right = x.left == null;
                    while (x.left != null) {
                        y = x;
                        x = x.left;
                    }
                    if (right)
                        y.right = x.right;
                    else
                        y.left = x.left;
                    x.right = null;
                    
                    Node<E> next = x;
                    x.value = next.value;
                } else if (x.left != null)
                    x = x.left;
                else
                    x = x.right;
            }
            parent.left = x;
        } else {
            if (x != null) {
                if (x.left == null && x.right == null)
                    parent.left = null;
                if (x.left != null && x.right != null) {
                    Node<E> y = x;
                    x = x.right;
                    boolean right = x.left == null;
                    while (x.left != null) {
                        y = x;
                        x = x.left;
                    }
                    if (x.left == null)
                        y.right = x.right;
                    else
                        y.left = x.left;
                    x.right = null;
                    
                    Node<E> next = x;
                    x.value = next.value;
                } else if (x.left != null)
                    x = x.left;
                else
                    x = x.right;
            }
            parent.right = x;
        }
        
        return node; **/
    }
    public E findMin() {
        return findMinRec(root);
    }
    private E findMinRec(Node<E> node) {
        if (node.left == null)
            return node.value;
        return findMinRec(node.left);
    }
    private E findMinIter(Node<E> node) {
        Node<E> curr = node;
        while (curr.left != null)
            curr = curr.left;
        return curr.value;
    }
    public E findMax() {
        return findMaxRec(root);
    }
    private E findMaxRec(Node<E> node) {
        if (node.right == null)
            return node.value;
        return findMaxRec(node.right);
    }
    private E findMaxIter(Node<E> node) {
        Node<E> curr = node;
        while (curr.right != null)
            curr = curr.right;
        return curr.value;
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
        E next = null;
        
        while (true) {
            if (value.compareTo(node.value) < 0) {
                next = node.value;
                node = node.left;
            } else if (value.compareTo(node.value) > 0)
                node = node.right;
            else {
                if (node.right != null)
                    next = findMinIter(node.right);
                break;
            }
            if (node == null)
                return null;
        }
        
        return next;
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
        E prev = null;
        
        while (true) {
            if (value.compareTo(node.value) < 0)
                node = node.left;
            else if (value.compareTo(node.value) > 0) {
                prev = node.value;
                node = node.right;
            } else {
                if (node.left != null)
                    prev = findMaxIter(node.left);
                break;
            }
            if (node == null)
                return null;
        }
        
        return prev;
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
