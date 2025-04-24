package prj5;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class LinkedList<T>
implements Iterable<T>
{
    //~ Fields ................................................................

    //~ Constructors ..........................................................

    //~Public  Methods ........................................................
    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T d) { data = d; }
    }

    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;


    public void add(T element) {
        Node<T> node = new Node<>(element);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }


    public void insertionSort(Comparator<T> comp) {
        if (head == null || head.next == null) return;
        Node<T> sorted = null;

        Node<T> curr = head;
        while (curr != null) {
            Node<T> next = curr.next;
            if (sorted == null || comp.compare(curr.data, sorted.data) < 0) {

                curr.next = sorted;
                sorted = curr;
            } else {

                Node<T> p = sorted;
                while (p.next != null && comp.compare(curr.data, p.next.data) >= 0) {
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

    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> curr = head;
            @Override
            public boolean hasNext() {
                return curr != null;
            }
            @Override
            public T next() {
                if (curr == null) throw new NoSuchElementException();
                T data = curr.data;
                curr = curr.next;
                return data;
            }
        };
    }

    public int size() {
        return size;
    }
}
