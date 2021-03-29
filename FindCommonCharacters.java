import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem: 1002
 * Source: https://leetcode.com/problems/find-common-characters/
 */
class FindCommonCharacters {
    public List<String> commonChars(String[] A) {

        List<char[]> sortedStrs = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            String s = A[i];
            char[] c = s.toCharArray();
            Arrays.sort(c);
            sortedStrs.add(c);
        }

        List<String> ret = new ArrayList<>();
        for (int i = 0; i < sortedStrs.get(0).length; i++) {
            char firstStrC = sortedStrs.get(0)[i];
            boolean charPresent = true;
            for (int j = 1; j < sortedStrs.size() && charPresent; j++) {
                for (int k = 0; k < sortedStrs.get(j).length; k++) {
                    if (k == sortedStrs.get(j).length -1
                            && firstStrC != sortedStrs.get(j)[k]) {
                        charPresent = false;
                    } else if (firstStrC == sortedStrs.get(j)[k]) {
                        char[] s = sortedStrs.get(j);
                        StringBuilder sb = new StringBuilder();
                        for (int l = 0; l < s.length; l++) {
                            if (l != k) {
                                sb.append(s[l]);
                            }
                        }
                        sortedStrs.set(j, sb.toString().toCharArray());
                        break;
                    }
                }
            }
            if (charPresent) {
                StringBuilder sb = new StringBuilder();
                sb.append(firstStrC);
                ret.add(sb.toString());
            }
        }
        return ret;
    }

    public static void main(String[] argc) {
        String[] input = {"bella","label","roller"};
        FindCommonCharacters sol = new FindCommonCharacters();
        List<String> ret = sol.commonChars(input);
        ret.stream().forEach(s -> System.out.println(s));
    }
}
