package precompute;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.assertEquals;

/**
 * Question: Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 *
 * Note: You may not engage in multiple transactions at the same time
 * (i.e., you must sell the stock before you buy again).
 *
 * Examples:
 * Input: [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *              Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 *
 * Source: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 */
public class BestTimeToBuySellStock3 {

    int maxProfit(int[] prices) {
        int n = prices.length, min_l = Integer.MAX_VALUE, max_r = 0, res = 0;
        int[] left = new int[n + 1], right = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            int price_l = prices[i], price_r = prices[n - i - 1];
            min_l = Math.min(min_l, price_l);
            max_r = Math.max(max_r, price_r);
            left[i + 1] = Math.max(left[i], price_l - min_l);
            right[n - i - 1] = Math.max(right[n - i], max_r - price_r);
        }
        for (int i = 0; i <= n; ++i) res = Math.max(res, left[i] + right[i]);
        return res;
    }

    @Test
    public void testCase1() {
        int[] prices = new int[] {3,3,5,0,0,3,1,4};
        BestTimeToBuySellStock3 solution = new BestTimeToBuySellStock3();
        int maxProfit = solution.maxProfit(prices);
        assertEquals(6, maxProfit);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(BestTimeToBuySellStock3.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
