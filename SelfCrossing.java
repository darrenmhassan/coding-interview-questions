import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;

/**
 * Question: You are given an array x of n positive numbers.
 * You start at point (0,0) and moves x[0] metres to the north,
 * then x[1] metres to the west, x[2] metres to the south,
 * x[3] metres to the east and so on. In other words,
 * after each move your direction changes counter-clockwise.
 *
 * Write a one-pass algorithm with O(1) extra space to determine, if your path crosses itself, or not.
 *
 * Example:
 * ┌───┐
 * │   │
 * └───┼──>
 *     │
 *
 * Input: [2,1,1,2]
 * Output: true
 *
 * ┌──────┐
 * │      │
 * │
 * │
 * └────────────>
 *
 * Input: [1,2,3,4]
 * Output: false
 *
 * Source: https://leetcode.com/problems/self-crossing/
 */
public class SelfCrossing {

    public boolean isSelfCrossing(int[] x) {
        if (x == null || x.length < 4) {
            return false;
        }
        if (Arrays.stream(x).anyMatch(i -> i < 0)) {
            return false;
        }
        int directionIdx = 0;
        int[] directionSum = new int[4];
        for (int i = 0; i < x.length; i++) {
            directionSum[directionIdx++] += x[i];
            if (directionIdx >= directionSum.length) {
                directionIdx = 0;
            }
        }
        if (directionSum[0] >= directionSum[2] && directionSum[1] <= directionSum[3]) {
            return true;
        }
        return false;
    }

    @Test
    public void testCase2() {
        SelfCrossing solution = new SelfCrossing();
        assertTrue(solution.isSelfCrossing(new int[] {1, 1, 2, 1, 1}));
    }

    @Test
    public void testCase1() {
        SelfCrossing solution = new SelfCrossing();
        assertTrue(solution.isSelfCrossing(new int[] {2, 1, 1, 2}));
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(SelfCrossing.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
