import java.util.HashSet;
import java.util.Set;

/**
 * Problem: 1044
 * Source: https://leetcode.com/problems/longest-duplicate-substring/
 */
public class LongestDuplicateSubString {
    public String longestDupSubstring(String s) {
        for (int len = s.length() - 1; len > 0; len--) {
            Set<String> subStrSet = new HashSet<>();
            for (int i = 0; i + len <= s.length(); i++) {
                String subStr = s.substring(i, i + len);
                if (subStrSet.contains(subStr)) {
                    return subStr;
                } else {
                    subStrSet.add(subStr);
                }
            }
        }
        return "";
    }
}
