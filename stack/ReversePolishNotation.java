package stack;

import java.util.Stack;

/**
 * Problem: 150
 * Source: https://leetcode.com/problems/evaluate-reverse-polish-notation/submissions/
 */
public class ReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<String> ops = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("+")
                    || tokens[i].equals("-")
                    || tokens[i].equals("*")
                    || tokens[i].equals("/")) {
                if (ops.size() < 2) {
                    return 0;
                }
                int op1 = Integer.parseInt(ops.pop());
                int op2 = Integer.parseInt(ops.pop());
                int res = 0;
                switch(tokens[i]) {
                    case "+": res = op2 + op1; break;
                    case "-": res = op2 - op1; break;
                    case "*": res = op2 * op1; break;
                    case "/": res = op2 / op1; break;
                    default: res = 0;
                }
                ops.push(new Integer(res).toString());
            } else {
                ops.push(tokens[i]);
            }
        }
        return Integer.parseInt(ops.pop());
    }
}
