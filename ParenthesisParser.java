import java.util.Stack;

/**
 * Qu1: Write code that determines whether parenthesis are correctly formed or not?
 * Correctly formed: "(())"
 * Incorrectly formed: "(()("
 *
 * Qu2: Modify your code so that it determines the longest length of correctly formed parans?
 * Ex: "(())" An: 4
 * Ex: "(()(" An: 2
 */
public class ParenthesisParser {

    public static void main(String[] arc) {
        ParenthesisParser sol = new ParenthesisParser();
        String in1 = "(())";
        String in2 = "(()((())";
        System.out.println(sol.parensCorrect(in1));
        System.out.println(sol.parensCorrect(in2));
        System.out.println(sol.correctParensLength(in1));
        System.out.println(sol.correctParensLength(in2));
    }

    public boolean parensCorrect(String s) {
        Stack<Character> stk = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stk.push(s.charAt(i));
            } else {
                if (stk.empty()) {
                    return false;
                } else {
                    stk.pop();
                }
            }
        }
        if (stk.empty()) {
            return true;
        }
        return false;
    }

    public int correctParensLength(String s) {
        Stack<Integer> stk = new Stack<>();
        int[] cnt = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stk.push(i);
            } else {
                if (!stk.empty()) {
                    cnt[stk.pop()] = 1;
                    cnt[i] = 1;
                }
            }
        }
        int tmpCnt = 0;
        int maxCnt = 0;
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] == 1) {
                tmpCnt += 1;
            } else {
                maxCnt = Math.max(tmpCnt, maxCnt);
                tmpCnt = 0;
            }
        }
        return Math.max(tmpCnt, maxCnt);
    }
}
