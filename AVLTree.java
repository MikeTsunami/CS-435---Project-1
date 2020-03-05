
/**
 * Write a description of class AVLTree here.
 *
 * @author Michael K. Tshimanga
 * @version (a version number or a date)
 */
public class AVLTree<E extends Comparable<E>>
{
    private BinarySearchTree<E>.Node<E> root;
    
    public AVLTree() {
        root = null;
    }
    
    public int height(E value) {
        return height((new BinarySearchTree<E>()).new Node<>(value));
    }
    private int height(BinarySearchTree<E>.Node<E> node) {
        if (node == null)
            return -1;
        else if (contains(node)) {
            if (node.left == null && node.right == null)
                return 0;
            else
                return 1 + Math.max(height(node.left), height(node.right));
        } else
            return -1;
    }
    public boolean contains(E value) {
        return contains(root, (new BinarySearchTree<E>()).new Node<>(value));
    }
    private boolean contains(BinarySearchTree<E>.Node<E> node) {
        return contains(root, node);
    }
    private boolean contains(BinarySearchTree<E>.Node<E> root, BinarySearchTree<E>.Node<E> node) {
        if (root == null)
            return false;
        if (node.value.compareTo(root.value) < 0)
            return contains(root.left, node);
        else if (node.value.compareTo(root.value) > 0)
            return contains(root.right, node);
        else
            return true;
    }
    
    public void insert(E value) {
        root = insertRec(root, value);
    }
    private BinarySearchTree<E>.Node<E> insertRec(BinarySearchTree<E>.Node<E> node, E value) {
        if (node == null)
            return (new BinarySearchTree<E>()).new Node<>(value);
        if (value.compareTo(node.value) < 0)
            node.left = insertRec(node.left, value);
        else if (value.compareTo(node.value) > 0)
            node.right = insertRec(node.right, value);
        else
            return node;
        int bf = height(node.left) - height(node.right);
        if (bf > 1 && value.compareTo(node.left.value) < 0)
            return right(node);
        if (bf < -1 && value.compareTo(node.right.value) > 0)
            return left(node);
        if (bf > 1 && value.compareTo(node.left.value) > 0) {
            node.left = left(node.left);
            return right(node);
        }
        if (bf < -1 && value.compareTo(node.right.value) < 0) {
           node.right = right(node.right);
           return left(node);
        }
        return node;
    }
    private BinarySearchTree<E>.Node<E> insertIter(BinarySearchTree<E>.Node<E> node, E value) {
        BinarySearchTree<E>.Node<E> x = node;
        BinarySearchTree<E>.Node<E> parent = null;
        
        if (node == null)
            return (new BinarySearchTree<E>()).new Node<>(value);
        while (x != null) {
            parent = x;
            if (value.compareTo(x.value) < 0)
                x = x.left;
            else
                x = x.right;
        }
        if (value.compareTo(parent.value) < 0)
            parent.left = (new BinarySearchTree<E>()).new Node<>(value);
        else
            parent.right = (new BinarySearchTree<E>()).new Node<>(value);
        int bf = height(node.left) - height(node.right);
        if (bf > 1 && value.compareTo(node.left.value) < 0)
            return right(node);
        if (bf < -1 && value.compareTo(node.right.value) > 0)
            return left(node);
        if (bf > 1 && value.compareTo(node.left.value) > 0) {
            node.left = left(node.left);
            return right(node);
        }
        if (bf < -1 && value.compareTo(node.right.value) < 0) {
            node.right = right(node.right);
            return left(node);
        }
        return node;
    }
    private BinarySearchTree<E>.Node<E> right(BinarySearchTree<E>.Node<E> node) {
        BinarySearchTree<E>.Node<E> n = node.left;
        BinarySearchTree<E>.Node<E> S2 = n.right;
        n.right = node;
        node.left = S2;
        return n;
    }
    private BinarySearchTree<E>.Node<E> left(BinarySearchTree<E>.Node<E> node) {
        BinarySearchTree<E>.Node<E> n = node.right;
        BinarySearchTree<E>.Node<E> S2 = n.left;
        n.left = node;
        node.right = S2;
        return n;
    }
    
    public void delete(E value) {
        root = deleteIter(root, value);
    }
    private BinarySearchTree<E>.Node<E> deleteRec(BinarySearchTree<E>.Node<E> node, E value) {
        if (node == null)
            return node;
        if (value.compareTo(node.value) < 0)
            node.left = deleteRec(node.left, value);
        else if (value.compareTo(node.value) > 0)
            node.right = deleteRec(node.right, value);
        else {
            if (node.left == null || node.right == null) {
                BinarySearchTree<E>.Node<E> temp = null;
                if (temp.equals(node.left))
                    temp = node.right;
                else
                    temp = node.left;
                if (temp == null) {
                    temp = node;
                    node = null;
                } else
                    node = temp;
            } else {
                BinarySearchTree<E>.Node<E> temp = findMinRec(node.right);
                node.value = temp.value;
                node.right = deleteRec(node.right, temp.value);
            }
        }
        if (node == null)
            return node;
        int bf = height(node.left) - height(node.right);
        if (bf > 1 && height(node.left.left) - height(node.left.right) >= 0)
            return right(node);
        if (bf > 1 && height(node.left.left) - height(node.left.right) < 0) {
            node.left = left(node.left);
            return right(node);
        }
        if (bf < -1 && height(node.right.left) - height(node.right.right) <= 0)
            return left(node);
        if (bf < -1 && height(node.right.left) - height(node.right.right) > 0) {
            node.right = right(node.right);
            return left(node);
        }
        return node;
    }
    private BinarySearchTree<E>.Node<E> deleteIter(BinarySearchTree<E>.Node<E> node, E value) {
        return null;
    }
    public E findMin() {
        return findMinRec(root).value;
    }
    private BinarySearchTree<E>.Node<E> findMinRec(BinarySearchTree<E>.Node<E> node) {
        if (node.left == null)
            return node;
        return findMinRec(node.left);
    }
    private BinarySearchTree<E>.Node<E> findMinIter(BinarySearchTree<E>.Node<E> node) {
        BinarySearchTree<E>.Node<E> curr = node;
        while (curr.left != null)
            curr = curr.left;
        return curr;
    }
    public E findMax() {
        return findMaxRec(root).value;
    }
    private BinarySearchTree<E>.Node<E> findMaxRec(BinarySearchTree<E>.Node<E> node) {
        if (node.right == null)
            return node;
        return findMaxRec(node.right);
    }
    private BinarySearchTree<E>.Node<E> findMaxIter(BinarySearchTree<E>.Node<E> node) {
        BinarySearchTree<E>.Node<E> curr = node;
        while (curr.right != null)
            curr = curr.right;
        return curr;
    }
    
    public E findNext(E value) {
        return findNextRec(root, null, value);
    }
    private E findNextRec(BinarySearchTree<E>.Node<E> node, BinarySearchTree<E>.Node<E> next, E value) {
        if (node == null)
            return null;
        if (value.equals(node.value)) {
            if (node.right != null)
                return findMinRec(node.right).value;
        } else if (value.compareTo(node.value) < 0) {
            next = node;
            return findNextRec(node.left, next, value);
        } else
            return findNextRec(node.right, next, value);
        return next.value;
    }
    private E findNextIter(BinarySearchTree<E>.Node<E> node, E value) {
        BinarySearchTree<E>.Node<E> next = null;
        
        while (true) {
            if (value.compareTo(node.value) < 0) {
                next = node;
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
        
        return next.value;
    }
    
    public E findPrev(E value) {
        return findPrevRec(root, null, value);
    }
    private E findPrevRec(BinarySearchTree<E>.Node<E> node, BinarySearchTree<E>.Node<E> prev, E value) {
        if (node == null)
            return null;
        if (value.equals(node.value)) {
            if (node.left != null)
                return findMaxRec(node.left).value;
        } else if (value.compareTo(node.value) < 0)
            return findPrevRec(node.left, prev, value);
        else {
            prev = node;
            return findPrevRec(node.right, prev, value);
        }
        return prev.value;
    }
    private E findPrevIter(BinarySearchTree<E>.Node<E> node, E value) {
        BinarySearchTree<E>.Node<E> prev = null;
        
        while (true) {
            if (value.compareTo(node.value) < 0)
                node = node.left;
            else if (value.compareTo(node.value) > 0) {
                prev = node;
                node = node.right;
            } else {
                if (node.left != null)
                    prev = findMaxIter(node.left);
                break;
            }
            if (node == null)
                return null;
        }
        
        return prev.value;
    }
    
    public static void main(String[]args) {
        int[] arr = BinarySearchTree.getRandomArray(10000);
        
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < arr.length; i++) {
            avl.insertRec(avl.root, arr[i]);
        }
        
        arr = BinarySearchTree.getRandomArray(10);
        
        bst = new BinarySearchTree<>();
        avl = new AVLTree<>();
        for (int i = 0; i < arr.length; i++) {
            bst.insertRec(bst.root, arr[i]);
            avl.insertRec(avl.root, arr[i]);
        }
        
        arr = BinarySearchTree.getRandomArray(10000);
        
        bst = new BinarySearchTree<>();
        avl = new AVLTree<>();
        for (int i = 0; i < arr.length; i++) {
            avl.insertIter(avl.root, arr[i]);
        }
    }
}
