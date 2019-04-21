import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.Arrays;

import static junit.framework.TestCase.assertFalse;
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

    private static class Coord {
        public int x;
        public int y;
    }

    public boolean isSelfCrossing(int[] x) {
        if (x == null || x.length < 4) {
            return false;
        }
        if (Arrays.stream(x).anyMatch(i -> i < 0)) {
            return false;
        }
        int n = x.length;
        for (int i = n - 1; i > 1; i--) {
            Coord line1Start = findCoord(x, i);
            Coord line1End = findCoord(x, i - 1);

            for (int j = i - 2; j >= 0; j--) {

                Coord line2Start = findCoord(x, j);
                Coord line2End = findCoord(x, j - 1);

                if (linesCross(line1Start, line1End, line2Start, line2End)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean linesCross(Coord line1Start,
                               Coord line1End,
                               Coord line2Start,
                               Coord line2End) {
        boolean line1Vert = line1Start.x == line1End.x;
        boolean line2Vert = line2Start.x == line2End.x;
        if (line1Vert && line2Vert) {
            return line1Start.x == line2Start.x &&
                    ((line1Start.y >= line2Start.y && line1Start.y <= line2End.y) ||
                    (line1Start.y >= line2End.y && line1Start.y <= line2Start.y));
        } else if (!line1Vert && !line2Vert) {
            return line1Start.y == line2Start.y &&
                    ((line1Start.x >= line2Start.x && line1Start.x <= line2End.x) ||
                    (line1Start.x >= line2End.x && line1Start.x <= line2Start.x));
        } else if (line1Vert) {
            return ((line1Start.y >= line2Start.y && line1End.y <= line2Start.y) ||
                    (line1End.y >= line2Start.y && line1Start.y <= line2Start.y)) &&
                    ((line1Start.x >= line2Start.x && line1Start.x <= line2End.x) ||
                    (line1Start.x >= line2End.x && line1Start.x <= line2Start.x));
        } else {
            return ((line2Start.y >= line1Start.y && line2End.y <= line1Start.y) ||
                    (line2End.y >= line1Start.y && line2End.y <= line1Start.y)) &&
                    ((line2Start.x >= line1Start.x && line2Start.x <= line1End.x) ||
                    (line2Start.x >= line1End.x && line2Start.x <= line1Start.x));
        }
    }

    private Coord findCoord(int[] x, int index) {
        Coord coord = new Coord();
        int[] sign = new int[] {1 , -1, -1, 1};
        int signCnt = 0;
        for (int i = 0; i <= index; i++) {
            if (i % 2 == 0) {
                coord.y += (x[i] * sign[signCnt++]);
            } else {
                coord.x += (x[i] * sign[signCnt++]);
            }
            signCnt = signCnt >= sign.length ? 0 : signCnt;
        }
        return coord;
    }

    @Test
    public void testCase4() {
        SelfCrossing solution = new SelfCrossing();
        assertFalse(solution.isSelfCrossing(new int[] {3, 3, 4, 2, 2}));
    }

    @Test
    public void testCase3() {
        SelfCrossing solution = new SelfCrossing();
        assertFalse(solution.isSelfCrossing(new int[] {1, 2, 3, 4}));
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
