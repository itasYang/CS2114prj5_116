package prj5;

import java.util.Comparator;
import java.util.Iterator;
import student.TestCase;

public class LinkedListTest extends TestCase {

    private LinkedList<Integer> list;

    public void setUp() {
        list = new LinkedList<>();
    }


    public void testAddAndSize() {
        assertEquals(0, list.size());

        list.add(10);
        assertEquals(1, list.size());

        list.add(20);
        list.add(30);
        assertEquals(3, list.size());
    }


    public void testIteratorBasic() {
        list.add(1);
        list.add(2);
        list.add(3);

        Iterator<Integer> it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals(1, (int)it.next());
        assertTrue(it.hasNext());
        assertEquals(2, (int)it.next());
        assertTrue(it.hasNext());
        assertEquals(3, (int)it.next());
        assertFalse(it.hasNext());
    }


    public void testIteratorEmptyList() {
        Iterator<Integer> it = list.iterator();
        assertFalse(it.hasNext());
        Exception thrown = null;
        try {
            it.next();
        }
        catch (Exception e) {
            thrown = e;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof java.util.NoSuchElementException);
    }


    public void testGetException() {
        Exception thrown = null;
        try {
            list.get(-1);
        }
        catch (Exception e) {
            thrown = e;
        }
        assertNotNull(thrown);
        Exception thrown1 = null;
        try {
            list.get(10);
        }
        catch (Exception e) {
            thrown1 = e;
        }
        assertNotNull(thrown1);
    }


    public void testInsertionSortAscending() {

        list.add(5);
        list.insertionSort(Comparator.naturalOrder());
        list.add(2);
        list.add(8);
        list.add(1);

        list.insertionSort(Comparator.naturalOrder());

        Iterator<Integer> it = list.iterator();
        assertEquals(1, (int)list.get(0));
        assertEquals(2, (int)list.get(1));
        assertEquals(5, (int)list.get(2));
        assertEquals(8, (int)list.get(3));
    }


    public void testInsertionSortDescending() {
        list.add(5);
        list.add(2);
        list.add(8);
        list.add(1);

        list.insertionSort(Comparator.reverseOrder());

        Iterator<Integer> it = list.iterator();
        assertEquals(8, (int)list.get(0));
        assertEquals(5, (int)list.get(1));
        assertEquals(2, (int)list.get(2));
        assertEquals(1, (int)list.get(3));
    }


    public void testInsertionSortSingleElement() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(5);
        list.insertionSort(Integer::compare);
        assertEquals(1, list.size());
        assertEquals(5, (int)list.get(0));
    }


    public void testInsertionSortEmptyList() {
        list.insertionSort(Comparator.naturalOrder());
        assertEquals(0, list.size());
    }
}
