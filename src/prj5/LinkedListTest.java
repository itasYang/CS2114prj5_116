package prj5;

import java.util.Comparator;
import java.util.Iterator;

// -------------------------------------------------------------------------
/**
 * This is a test class for LinkedList.
 *
 * @author Yinhan Wang, Ethan Yang, Boyuan Zhao, Chenghan Yang
 * @version 2025/04/24
 */
public class LinkedListTest extends student.TestCase {

    private LinkedList<Integer> list;

    // ----------------------------------------------------------
    /**
     * set up method
     */
    public void setUp() {
        list = new LinkedList<>();
    }


    // ----------------------------------------------------------
    /**
     * Test method for addAndSize method.
     */
    public void testAddAndSize() {
        assertEquals(0, list.size());

        list.add(10);
        assertEquals(1, list.size());

        list.add(20);
        list.add(30);
        assertEquals(3, list.size());
    }


    // ----------------------------------------------------------
    /**
     * This is a test method for iteratorBasic method.
     */
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


    // ----------------------------------------------------------
    /**
     * This is a test method for iteratoremptylist method.
     */
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


    // ----------------------------------------------------------
    /**
     * This is a test method for getException method.
     */
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


    // ----------------------------------------------------------
    /**
     * This is a test method for insertionSortAscending method.
     */
    public void testInsertionSortAscending() {

        list.add(5);
        list.insertionSort(Comparator.naturalOrder());
        list.add(2);
        list.add(8);
        list.add(1);

        list.insertionSort(Comparator.naturalOrder());

        assertEquals(1, (int)list.get(0));
        assertEquals(2, (int)list.get(1));
        assertEquals(5, (int)list.get(2));
        assertEquals(8, (int)list.get(3));
    }


    // ----------------------------------------------------------
    /**
     * This is a test method for insertionSortDescending method.
     */
    public void testInsertionSortDescending() {
        list.add(5);
        list.add(2);
        list.add(8);
        list.add(1);

        list.insertionSort(Comparator.reverseOrder());

        assertEquals(8, (int)list.get(0));
        assertEquals(5, (int)list.get(1));
        assertEquals(2, (int)list.get(2));
        assertEquals(1, (int)list.get(3));
    }


    // ----------------------------------------------------------
    /**
     * This is a test method for insertionSortSingleElement method.
     */
    public void testInsertionSortSingleElement() {
        LinkedList<Integer> list1 = new LinkedList<>();
        list1.add(5);
        list1.insertionSort(Integer::compare);
        assertEquals(1, list1.size());
        assertEquals(5, (int)list1.get(0));
    }


    // ----------------------------------------------------------
    /**
     * This is a test method for insertionSortEmptyList method.
     */
    public void testInsertionSortEmptyList() {
        list.insertionSort(Comparator.naturalOrder());
        assertEquals(0, list.size());
    }


    // ----------------------------------------------------------
    /**
     * This is a test method for insertionSortEmptyList method.
     */
    public void testInsertion() {
        list.add(5);
        list.add(3);
        list.add(4);
        list.insertionSort(Comparator.naturalOrder());

        assertEquals(3, (int)list.get(0));
        assertEquals(4, (int)list.get(1));
        assertEquals(5, (int)list.get(2));
    }
}
