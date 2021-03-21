import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem: 1237. Find Positive Integer Solution for a Given Equation
 * Source: https://leetcode.com/problems/find-positive-integer-solution-for-a-given-equation/
 */
public class FindSolutionForEquation {
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> ret = new ArrayList<>();
        for (int x = 1; x < 1001; x++) {
            for (int y = 1; y < 1001; y++) {
                int zz = customfunction.f(x, y);
                if (zz == z) {
                    List<Integer> res = Arrays.asList(x, y);
                    ret.add(res);
                }
            }
        }
        return ret;
    }
}
