package countSmaller;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Question: You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 * Example:
 * Input: [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 *
 * Source: https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 */
public class CountOfSmallerNumbersAfterSelf {

    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        Integer[] res = new Integer[nums.length];
        res[nums.length - 1] = 0;
        LinkedList<Integer> ll = new LinkedList<>();
        ll.add(nums[nums.length - 1]);
        for (int i = nums.length - 2; i >= 0; i--) {
            res[i] = cntLess(ll, nums[i]);
        }
        return Arrays.asList(res);
    }

    private int cntLess(LinkedList<Integer> ll, int val) {
        int cnt = 0;
        ListIterator<Integer> it = ll.listIterator();
        while (it.hasNext()) {
            if (it.next() < val) {
                cnt++;
            } else {
                it.previous();
                break;
            }
        }
        it.add(val);
        return cnt;
    }

    @Test
    public void testCase2() {
        int[] arr = readTestDataFromFile();
        Integer[] expRes = readTestResultFromFile();
        CountOfSmallerNumbersAfterSelf solution = new CountOfSmallerNumbersAfterSelf();
        long start = System.currentTimeMillis();
        List<Integer> res = solution.countSmaller(arr);
        System.out.println(String.format("Test case 2 time %s", System.currentTimeMillis() - start));
        assertEquals(Arrays.asList(expRes), res);
    }

    @Test
    public void testCase1() {
        int[] arr = {5, 2, 6, 1};
        CountOfSmallerNumbersAfterSelf solution = new CountOfSmallerNumbersAfterSelf();
        List<Integer> res = solution.countSmaller(arr);
        assertEquals(Arrays.asList(2, 1, 1, 0), res);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(CountOfSmallerNumbersAfterSelf.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }

    private int[] readTestDataFromFile() {
        int[] data = new int[0];
        File file = new File("C:\\Users\\darre\\coding-interview-questions\\countSmaller\\CountOfSmallerNumbersAfterSelfData.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String fileStr = br.readLine();
            String[] dataStr = fileStr.split(",");
            data = new int[dataStr.length];
            for (int p = 0; p < dataStr.length; p++) {
                data[p] = Integer.parseInt(dataStr[p].trim());
            }
        } catch (IOException e) {
        }
        return data;
    }

    private Integer[] readTestResultFromFile() {
        Integer[] data = new Integer[0];
        File file = new File("C:\\Users\\darre\\coding-interview-questions\\countSmaller\\CountOfSmallerNumbersAfterSelfResult.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String fileStr = br.readLine();
            String[] dataStr = fileStr.split(",");
            data = new Integer[dataStr.length];
            for (int p = 0; p < dataStr.length; p++) {
                data[p] = Integer.parseInt(dataStr[p].trim());
            }
        } catch (IOException e) {
        }
        return data;
    }
}
