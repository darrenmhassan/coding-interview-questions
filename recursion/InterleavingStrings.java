package recursion;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.assertEquals;

/**
 * Question: Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 *
 * Example:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 *
 * Source: https://leetcode.com/problems/interleaving-string/
 */
public class InterleavingStrings {

    private boolean stringsInterleave = false;

    public boolean isInterleave(String s1, String s2, String s3) {

        if ((s1 == null || s1.isEmpty()) &&
                (s2 == null || s2.isEmpty()) &&
                (s3 == null || s3.isEmpty())) {
            return true;
        } else if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        char[] s1CharArr = s1.toCharArray();
        char[] s2CharArr = s2.toCharArray();
        char[] s3CharArr = s3.toCharArray();
        recSearch(0, 0, 0, s1CharArr, s2CharArr, s3CharArr);

        return stringsInterleave;
    }

    private boolean recSearch(int s1i, int s2i, int s3i, char[] s1CharArr, char[] s2CharArr, char[] s3CharArr) {
        while (s3i < s3CharArr.length && !stringsInterleave) {
            char c = s3CharArr[s3i];
            if (charsEqual(c, s1i, s1CharArr) && charsEqual(c, s2i, s2CharArr)) {
                recSearch(s1i, s2i + 1, s3i + 1, s1CharArr, s2CharArr, s3CharArr);
                s1i++;
            } else if (charsEqual(c, s1i, s1CharArr)) {
                s1i++;
            } else if (charsEqual(c, s2i, s2CharArr)) {
                s2i++;
            } else {
                return false;
            }
            s3i++;
        }
        stringsInterleave = true;
        return stringsInterleave;
    }

    private boolean charsEqual(char c, int i, char[] charArr) {
        return i < charArr.length && c == charArr[i];
    }

    @Test
    public void testInterleaving3() {
        String s1 = "bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa";
        String s2 = "babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab";
        String s3 = "babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab";
        InterleavingStrings interleavingStrings = new InterleavingStrings();
        long start = System.currentTimeMillis();
        boolean actual = interleavingStrings.isInterleave(s1, s2, s3);
        long end = System.currentTimeMillis();
        System.out.println(String.format("Time: %s ms", end - start));
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void testInterleaving2() {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbbaccc";
        InterleavingStrings interleavingStrings = new InterleavingStrings();
        boolean actual = interleavingStrings.isInterleave(s1, s2, s3);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void testInterleaving() {
        InterleavingStrings interleavingStrings = new InterleavingStrings();
        boolean actual = interleavingStrings.isInterleave("aabcc", "dbbca", "aadbbcbcac");
        boolean expected = true;
        assertEquals(expected, actual);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(InterleavingStrings.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
