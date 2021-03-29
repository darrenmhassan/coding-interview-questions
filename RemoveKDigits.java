import java.util.ArrayList;
import java.util.List;

/**
 * Problem: 402
 * Source: https://leetcode.com/problems/remove-k-digits/
 */
public class RemoveKDigits {
    //  0123456
    // "1432219"
    String minNum = "9999999999999999999999999999999999999999999999999999999";
    public static void main(String argc[]) {
        RemoveKDigits sol = new RemoveKDigits();
        System.out.println(sol.removeKdigits("5337", 2));
    }
    public String removeKdigits(String num, int k) {
        if (num.length() == k) {
            return "0";
        }
        recRemoveKdigits(num, k, new ArrayList<>(), 0);
        return removeLeadingZeros(minNum);
    }
    private void recRemoveKdigits(String num, int k, List<Integer> idxToRemove, int startIdx) {
        if (idxToRemove.size() == k) {
            String newNum = removeChars(num, idxToRemove);
            if (newNum.compareTo(minNum) < 0) {
                minNum = newNum;
            }
            return;
        }
        for (int i = startIdx; i < num.length(); i++) {
            idxToRemove.add(new Integer(i));
            recRemoveKdigits(num, k, idxToRemove, i+1);
            idxToRemove.remove(new Integer(i));
        }
    }
    private String removeChars(String num, List<Integer> idxToRemove) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num.length(); i++) {
            boolean noMatch = true;
            for (int j = 0; j < idxToRemove.size() && noMatch; j++) {
                if (idxToRemove.get(j).equals(i)) {
                    noMatch = false;
                }
            }
            if (noMatch) {
                sb.append(num.charAt(i));
            }
        }
        return sb.toString();
    }
    private String removeLeadingZeros(String num) {
        if (num.matches("00")) {
            return "0";
        }
        if (num.matches("^0+[1-9]+[0-9]*")) {
            return num.replaceAll("^0+", "");
        }
        return num;
    }
}
