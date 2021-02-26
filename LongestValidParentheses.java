import java.util.Stack;

/**
 * Question: Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 *
 * Example:
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 *
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 *
 * Source: https://leetcode.com/problems/longest-valid-parentheses/
 */
public class LongestValidParentheses{
        public int longestValidParentheses(String s) {
            int n = s.length();
            int[] sol = new int[n];
            Stack<Integer> stk = new Stack<>();
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '(') {
                    stk.push(i);
                } else if (s.charAt(i)== ')' && !stk.empty()) {
                    int sIdx = stk.pop();
                    sol[sIdx] = 1;
                    sol[i] = 1;
                }
            }
            int ret = 0, cnt = 0;
            for (int i = 0; i < n; i++) {
                if (sol[i] == 1) {
                    cnt++;
                } else {
                    ret = Math.max(ret, cnt);
                    cnt = 0;
                }
            }
            ret = Math.max(ret, cnt);
            return ret;
        }
    }