package prj5;

import java.util.Comparator;
import java.util.Iterator;

// -------------------------------------------------------------------------
/**
 * create a linked list that can sort the data
 * 
 * @param <T>
 *            anytype of object
 * 
 * @author yinhan wang, ethan yang, boyuan zhao, chenghan yang
 * @version 2025/04/24
 */
public class LinkedList<T> implements Iterable<T> {

    private static class Node<T> {
        private T data;
        private Node<T> next;

        Node(T d) {
            data = d;
        }
    }

    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    // ----------------------------------------------------------
    /**
     * add a new element to the list and increment size
     * 
     * @param element
     *            element will be added
     */
    public void add(T element) {
        Node<T> node = new Node<>(element);
        if (head == null) {
            head = node;
            tail = node;
        }
        else {
            tail.next = node;
            tail = node;
        }
        size++;
    }


    // ----------------------------------------------------------
    /**
     * sort the insertion
     * 
     * @param comp
     *            comparator object
     */
    public void insertionSort(Comparator<T> comp) {
        if (head == null || head.next == null)
            return;
        Node<T> sorted = null;

        Node<T> curr = head;
        while (curr != null) {
            Node<T> next = curr.next;
            if (sorted == null || comp.compare(curr.data, sorted.data) < 0) {

                curr.next = sorted;
                sorted = curr;
            }
            else {

                Node<T> p = sorted;
                while (p.next != null && comp.compare(curr.data,
                    p.next.data) >= 0) {
                    p = p.next;
                }
                curr.next = p.next;
                p.next = curr;
            }
            curr = next;
        }

        head = sorted;
        tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
    }


    // -------------------------------------------------------------------------
    /**
     * private class iterator
     * 
     * @param <T>
     *            anytype of object
     * 
     * @author yinhan wang, ethan yang, boyuan zhao, chenghan yang
     * @version 2025/04/24
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }


            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                T data = curr.data;
                curr = curr.next;
                return data;
            }
        };
    }


    // ----------------------------------------------------------
    /**
     * get the size of iterator
     * 
     * @return size
     *         size of iterator
     */
    public int size() {
        return size;
    }


    // ----------------------------------------------------------
    /**
     * get element base on index
     * 
     * @param index
     *            index of element
     * @return
     *         element on the index
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
                + size);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
}
