package common;

public class DoublyLinkedList<E> {

    protected DLLNode<E> head;
    protected DLLNode<E> tail;
    private int size;

    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    // add at the tail of doubly linked list
    public boolean add(E e) {
        DLLNode<E> element = new DLLNode<E>(e);
        if (isEmpty()) {
            head = tail = element;
            size++;
            return true;
        }
        element.setFront(tail);
        tail.setBack(element);
        tail = element;
        size++;
        return true;
    }

    // remove the i index of the doubly linked list starting from head
    public E remove(int i) {
        DLLNode<E> target = null;

        // list is empty
        if (isEmpty()) {
            System.out.println("Cannot remove on an empty list.");
            return null;
        }

        // not empty but out of bound
        if (i >= size || i < 0) {
            System.out.println("Index out of bound.");
            return null;
        }

        // at head and head not equal to tail
        else if (i == 0 && size > 1) {
            target = head;
            head = head.getBack();
            head.setFront(null);
            size--;
        }

        // at head/tail and head equals to tail
        else if (i == 0 && size == 1) {
            target = head;
            head = tail = null;
            size--;
        }

        // at tail and head not equal to tail
        else if (i == size - 1 && size > 1) {
            target = tail;
            tail = tail.getFront();
            tail.setBack(null);
            size--;
        }

        // not head or tail which is in the middle
        else {
            for (int j = 0; j < i; j++)
                target = target.getBack();
        }
        return target.getInfo();
    }

    // contains method
    public boolean contains(E e) {
        DLLNode<E> location = head;
        while (location != null) {
            if (location.getInfo().equals(e))
                return true;
            location = location.getBack();
        }
        return false;
    }

    // add elements from head or tail
    public boolean add(Where where, E e) {
        DLLNode<E> element = new DLLNode<E>(e);

        if (isEmpty()) {
            head = tail = element;
            size++;
            return true;
        }

        // add before head
        if (where == Where.Front) {
            element.setBack(head);
            head.setFront(element);
            head = element;
            size++;
        }

        // add after tail
        else if (where == Where.Back) {
            element.setFront(tail);
            tail.setBack(element);
            tail = element;
            size++;
        }

        // add in the middle
        else {
            System.out.println("Cannot add through this method.");
            return false;
        }
        return true;
    }

    // add elements in the middle
    public boolean add(Where where, int index, E e) {
        DLLNode<E> location = head;
        DLLNode<E> element = new DLLNode<E>(e);

        // if not in the middle or empty
        if (where != Where.Middle || isEmpty()) {
            System.out.println("Cannot be added except from middle.");
            return false;
        }

        // in the middle and not empty but out of bounds
        if (index <= 0 || index >= size) {
            System.out.println("Index " + index + " is not in the middle.");
            return false;
        }

        // in the middle, not empty and in the bound
        for (int i = 0; i < index; i++)
            location = location.getBack();
        element.setFront(location.getFront());
        element.setBack(location);
        location.getFront().setBack(element);
        location.setFront(element);
        size++;
        return true;
    }

    // Nested inner class doubly linked node.
    static class DLLNode<T> {
        private DLLNode<T> front;
        private DLLNode<T> back;
        private T info;

        DLLNode(T info) {
            this.info = info;
            front = null;
            back = null;
        }

        T getInfo() {
            return info;
        }

        DLLNode<T> getFront() {
            return front;
        }

        void setFront(DLLNode<T> front) {
            this.front = front;
        }

        DLLNode<T> getBack() {
            return back;
        }

        void setBack(DLLNode<T> back) {
            this.back = back;
        }
    }

    // Enumeration class where
    enum Where {
        Front, Middle, Back
    }
}
