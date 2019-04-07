import java.util.Stack;

/**
 * https://leetcode.com/problems/longest-valid-parentheses/
 *
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 *
 * Example 1:
 *
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 *
 * Example 2:
 *
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 */
class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int n = s.length();

        // Create a stack and push -1 as initial index to it. 
        Stack<Integer> stk = new Stack<>();
        stk.push(-1);

        // Initialize result 
        int result = 0;

        // Traverse all characters of given string 
        for (int i=0; i<n; i++)
        {
            // If opening bracket, push index of it 
            if (s.charAt(i) == '(')
                stk.push(i);

            else // If closing bracket, i.e.,str[i] = ')' 
            {
                // Pop the previous opening bracket's index 
                stk.pop();

                // Check if this length formed with base of 
                // current valid substring is more than max  
                // so far 
                if (!stk.empty())
                    result = Math.max(result, i - stk.peek());

                    // If stack is empty. push current index as
                    // base for next valid substring (if any)
                else stk.push(i);
            }
        }

        return result;
    }
}