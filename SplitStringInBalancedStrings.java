public class SplitStringInBalancedStrings {
    public static void main(String[] argc) {
        //              0123456789
        //String input = "RLLLLRRRLR"; // 3
        String input = "RLRRLLRLRL"; // 4
        SplitStringInBalancedStrings sol = new SplitStringInBalancedStrings();
        System.out.println(sol.balancedStringSplit(input));
    }
    public int balancedStringSplit(String s) {

        int maxSplits = 0;
        for (int i = 2; i <= s.length(); i += 2) {
            int splits = 0;
            if (isBalanced(s.substring(0, i))) {
                splits++;
                splits += recBalancedStringSplit(s, i);
                if (splits > maxSplits) {
                    maxSplits = splits;
                }
            }
        }
        return maxSplits;
    }
    private int recBalancedStringSplit(String s, int startIdx) {

        int splits = 0;
        for (int i = startIdx + 2; i <= s.length(); i += 2) {
            if (isBalanced(s.substring(startIdx, i))) {
                splits++;
                splits += recBalancedStringSplit(s, i);
                return splits;
            }
        }
        return splits;
    }
    private boolean isBalanced(String s) {
        int lCnt = 0, rCnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'L') {
                lCnt++;
            } else {
                rCnt++;
            }
        }
        return lCnt == rCnt ? true : false;
    }
}
