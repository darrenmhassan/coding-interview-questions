package recursion;

/**
 * Source: https://leetcode.com/problems/palindrome-partitioning-iii/
 */
public class PalindromePartitioning3 {
    public int palindromePartition(String s, int k) {
        if (k > s.length()) {
            return 0;
        }
        return partition(s, k);
    }
    int partition(String s, int k) {
        if (k == 1) {
            return makePalindrome(s);
        }
        int minRet = 100000;
        for (int i = 1; i < s.length(); i++) {
            int ret = 0;
            ret = makePalindrome(s.substring(0, i));
            ret += partition(s.substring(i, s.length()), k--);
            minRet = Math.min(ret, minRet);
        }
        return minRet;
    }

    int makePalindrome(String s) {
        if (s.length() == 1) {
            return 0;
        }
        int ret = 0;
        int mid = s.length() / 2;
        char[] beg = s.substring(0, mid).toCharArray();
        char[] end = s.substring(mid + 1, s.length() -1).toCharArray();
        for (int i = 0; i < beg.length; i++) {
            if (beg[i] != end[end.length - 1 - i]) {
                ret++;
            }
        }
        return ret;
    }
}