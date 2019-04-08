import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

/**
 *  In Find a duplicate, Space Edition™, we were given an array of integers where:
 *     the integers are in the range 1..n1..n1..n
 *     the array has a length of n+1n+1n+1
 *
 * These properties mean the array must have at least 1 duplicate.
 * Our challenge was to find a duplicate number, while optimizing for space.
 * We used a divide and conquer approach, iteratively cutting the array in half to find a
 * duplicate integer in O(n log n) time and O(1) space (sort of a modified binary search).
 *
 * But we can actually do better. We can find a duplicate integer in O(n) time while
 * keeping our space cost at O(1).
 *
 * This is a tricky one to derive (unless you have a strong background in graph theory), so we'll get you started:
 *
 * Imagine each item in the array as a node in a linked list. In any linked list,
 * each node has a value and a "next" pointer. In this case:
 *     The value is the integer from the array.
 *     The "next" pointer points to the value-eth node in the list (numbered starting from 1).
 *
 * For example, if our value was 3, the "next" node would be the third node.
 *
 * Here’s a full example:
 *  [2, 3, 1, 3]
 *
 *  Source: www.interviewbit.com
 */
public class FindDuplicateInLinkedList {

    public static int findDuplicate(int[] theArray) {

        // find a number that appears more than once ... in O(n) time
        if (theArray == null || theArray.length < 1) {
            return 0;
        }
        int n = theArray.length;
        int dup = 0;
        int pos1 = n;
        for (int i = 0; i < n; i++) {
            pos1 = theArray[pos1 - 1];
            int pos2 = theArray[pos1 - 1];
            for (int j = 0; j < n; j++) {
                if (pos1 == pos2) {
                    dup = pos1;
                    break;
                }
                pos2 = theArray[pos2 - 1];
            }
            if (dup > 0) {
                break;
            }
        }

        return dup;
    }

    // tests

    @Test
    public void justTheRepeatedNumberTest() {
        final int[] numbers = {1, 1};
        final int expected = 1;
        final int actual = findDuplicate(numbers);
        assertEquals(expected, actual);
    }

    @Test
    public void shortArrayTest() {
        final int[] numbers = {1, 2, 3, 2};
        final int expected = 2;
        final int actual = findDuplicate(numbers);
        assertEquals(expected, actual);
    }

    @Test
    public void mediumArrayTest() {
        final int[] numbers = {1, 2, 5, 5, 5, 5};
        final int expected = 5;
        final int actual = findDuplicate(numbers);
        assertEquals(expected, actual);
    }

    @Test
    public void longArrayTest() {
        final int[] numbers = {4, 1, 4, 8, 3, 2, 7, 6, 5};
        final int expected = 4;
        final int actual = findDuplicate(numbers);
        assertEquals(expected, actual);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(FindDuplicateInLinkedList.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}