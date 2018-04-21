package common;

import java.util.*;

public class BSTree<T> implements Iterable<T> {
    BSTNode<T> root;
    Comparator<T> comp;

    // default constructor, use comparison of natural order of object T
    public BSTree() {
        root = null;
        comp = new Comparator<T>() {
            @Override
            public int compare(T element1, T element2) {
                return ((Comparable) element1).compareTo(element2);
            }
        };
    }

    // overload constructor
    public BSTree(Comparator<T> comp) {
        root = null;
        this.comp = comp;
    }

    // unbounded structure, always returns false
    public boolean isFull() {
        return false;
    }

    // returns true if root is null; false otherwise
    public boolean isEmpty() {
        return root == null;
    }

    // ------------------------------------------------------
    /*
     * recursive add method; it invokes a private recursive
     * method with passing root as the argument
     */
    public boolean recursiveAdd(T element) {
        root = recAdd(element, root);
        return true;
    }

    private BSTNode<T> recAdd(T element, BSTNode<T> node) {
        BSTNode<T> newNode = new BSTNode<T>(element);
        if (node == null)
            node = newNode;
        else if (comp.compare(element, node.getInfo()) <= 0)
            node.setLeft(recAdd(element, node.getLeft()));
        else
            node.setRight(recAdd(element, node.getRight()));
        return node;
    }
    // ------------------------------------------------------

    // iterative add method;
    // small elements are added to left subtree
    // large elements are added to right subtree
    public boolean iterativeAdd(T element) {
        BSTNode<T> newNode = new BSTNode<T>(element);
        BSTNode<T> curr = root;
        BSTNode<T> prev = null;
        while (curr != null) {
            prev = curr;
            if (comp.compare(element, prev.getInfo()) <= 0)
                curr = curr.getLeft();
            else
                curr = curr.getRight();
        }
        if (prev == null)
            root = newNode;
        else if (comp.compare(element, prev.getInfo()) <= 0)
            prev.setLeft(newNode);
        else
            prev.setRight(newNode);
        return true;
    }

    // --------------------------------------------------------------------
    /*
     * recursive size method;
     */
    public int recursiveSize() {
        return recSize(root);
    }

    private int recSize(BSTNode<T> node) {
        if (node == null)
            return 0;
        else
            return 1 + recSize(node.getLeft()) + recSize(node.getRight());
    }
    // --------------------------------------------------------------------

    // iterative size method
    public int iterativeSize() {
        int count = 0;
        Stack<BSTNode<T>> nodeStack = new Stack<BSTNode<T>>();
        if (root == null)
            return count;
        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            BSTNode<T> node = nodeStack.pop();
            count++;
            if (node.getRight() != null)
                nodeStack.push(node.getRight());
            if (node.getLeft() != null)
                nodeStack.push(node.getLeft());
        }
        return count;
    }

    // ---------------------------------------------------------
    /*
     * recursive find maximum depth method
     */
    public int maxDepth() {
        return recMaxDepth(root);
    }

    private int recMaxDepth(BSTNode<T> node) {
        if (node.getLeft() == null && node.getRight() == null)
            return 1;
        else if (node.getLeft() == null)
            return 1 + recMaxDepth(node.getRight());
        else if (node.getRight() == null)
            return 1 + recMaxDepth(node.getLeft());
        else
            return 1 + (recMaxDepth(node.getLeft()) >= recMaxDepth(node.getRight()) ? recMaxDepth(node.getLeft())
                    : recMaxDepth(node.getRight()));
    }
    // ---------------------------------------------------------

    // return an iterator object instance based on traversal type
    public Iterator<T> getIterator(Traversal order) {
        final Queue<T> infoQueue = new LinkedList<T>();
        if (order == Traversal.Preorder)
            preorder(root, infoQueue);
        if (order == Traversal.Postorder)
            postorder(root, infoQueue);
        if (order == Traversal.Inorder)
            inorder(root, infoQueue);

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return !infoQueue.isEmpty();
            }

            @Override
            public T next() {
                if (!hasNext())
                    return null;
                return infoQueue.poll();
            }

            @Override
            public void remove() {
                System.out.println("Unsupported action.");
            }
        };

    }

    // return an iterator object instance based on default inorder traversal
    @Override
    public Iterator<T> iterator() {
        return getIterator(Traversal.Inorder);
    }

    // save elements to a queue by preorder traversal
    private void preorder(BSTNode<T> node, Queue<T> q) {
        if (node != null) {
            q.add(node.getInfo());
            preorder(node.getLeft(), q);
            preorder(node.getRight(), q);
        }
    }

    // save elements to a queue by postorder traversal
    private void postorder(BSTNode<T> node, Queue<T> q) {
        if (node != null) {
            postorder(node.getLeft(), q);
            postorder(node.getRight(), q);
            q.add(node.getInfo());
        }
    }

    // save elements to a queue by inorder traversal
    private void inorder(BSTNode<T> node, Queue<T> q) {
        if (node != null) {
            inorder(node.getLeft(), q);
            q.add(node.getInfo());
            inorder(node.getRight(), q);
        }
    }

    public class BSTNode<E> {
        private BSTNode<E> left;
        private BSTNode<E> right;
        private E info;

        public BSTNode(E info) {
            left = null;
            right = null;
            this.info = info;
        }

        public void setInfo(E info) {
            this.info = info;
        }

        public E getInfo() {
            return info;
        }

        public void setLeft(BSTNode<E> left) {
            this.left = left;
        }

        public void setRight(BSTNode<E> right) {
            this.right = right;
        }

        public BSTNode<E> getLeft() {
            return left;
        }

        public BSTNode<E> getRight() {
            return right;
        }
    }

    public enum Traversal {
        Inorder, Preorder, Postorder;
    }
}
