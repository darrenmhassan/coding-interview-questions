import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Question: Given a non-empty array of unique positive integers A, consider the following graph:
 *
 *     There are A.length nodes, labelled A[0] to A[A.length - 1];
 *     There is an edge between A[i] and A[j] if and only if A[i] and A[j] share a common factor greater than 1.
 *
 * Return the size of the largest connected component in the graph.
 *
 * Example:
 * Input: [4,6,15,35]
 * Output: 4
 *
 * Source: https://leetcode.com/problems/largest-component-size-by-common-factor/
 */
public class LargestComponentSizeByCommonFactor {

    public int largestComponentSize(int[] A) {
        List<Set<Integer>> connectedNodeGroups = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            for (Set<Integer> connectedNodes : connectedNodeGroups) {
                if (connected(connectedNodes, A[i])) {
                    connectedNodes.add(A[i]);
                }
            }
            Set<Integer> newNodeGroup = new HashSet<>();
            newNodeGroup.add(A[i]);
            connectedNodeGroups.add(newNodeGroup);
        }
        List<Set<Integer>> mergedConnectedNodeGroups = mergeConnectedNodes(connectedNodeGroups);
        int maxConnectedNodes = 0;
        for (Set<Integer> connectedNodes : mergedConnectedNodeGroups) {
            if (connectedNodes.size() > maxConnectedNodes) {
                maxConnectedNodes = connectedNodes.size();
            }
        }
        return maxConnectedNodes;
    }

    private boolean connected(Set<Integer> conNodes, int node) {
        for (Integer cn : conNodes) {
            if (shareDivisor(cn, node)) {
                return true;
            }
        }
        return false;
    }

    private boolean shareDivisor(int x1, int x2) {
        int min = Math.min(x1, x2);
        int potentialDivisor = 2;
        while (potentialDivisor <= min) {
            if (x1 % potentialDivisor == 0 && x2 % potentialDivisor == 0) {
                return true;
            }
            potentialDivisor++;
        }
        return false;
    }

    public int solutionOne(int[] A) {
        Map<Integer, Set<Integer>> divisorsToNodes = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            List<Integer> divisors = findPrimeDivisorsGreaterThanOne(A[i]);
            for (Integer divisor : divisors) {
                Set<Integer> nodes;
                if (divisorsToNodes.containsKey(divisor)) {
                    nodes = divisorsToNodes.get(divisor);
                } else {
                    nodes = new HashSet<>();
                }
                nodes.add(A[i]);
                divisorsToNodes.put(divisor, nodes);
            }
        }
        List<Set<Integer>> connectedNodeGroups = mergeConnectedNodes(new ArrayList<>(divisorsToNodes.values()));
        int maxConnectedNodes = 0;
        for (Set<Integer> connectedNodes : connectedNodeGroups) {
            if (connectedNodes.size() > maxConnectedNodes) {
                maxConnectedNodes = connectedNodes.size();
            }
        }
        return maxConnectedNodes;
    }

    private List<Set<Integer>> mergeConnectedNodes(List<Set<Integer>> nodeGroups) {
        List<Set<Integer>> connectedNodeGroups = new ArrayList<>();
        for (Set<Integer> subjectNodes : nodeGroups) {
            Set<Integer> connectedNodes = new HashSet<>();
            connectedNodes.addAll(subjectNodes);
            for (Set<Integer> nodes : nodeGroups) {
                if (!nodes.equals(subjectNodes)) {
                    for (Integer subjectNode : subjectNodes) {
                        if (nodes.contains(subjectNode)) {
                            connectedNodes.addAll(nodes);
                            break;
                        }
                    }
                }
            }
            connectedNodeGroups.add(connectedNodes);
        }
        List<Set<Integer>> ret = connectedNodeGroups;
        if (!connectedNodeGroups.equals(nodeGroups)) {
            ret = mergeConnectedNodes(connectedNodeGroups);
        }
        return ret;
    }

    private List<Integer> findPrimeDivisorsGreaterThanOne(int num) {
        int potentialDivisor = 2;
        List<Integer> divisors = new ArrayList<>();
        while (potentialDivisor <= num) {
            if (potentiallyNewDivisor(potentialDivisor, divisors)) {
                if (num % potentialDivisor == 0) {
                    divisors.add(potentialDivisor);
                }
            }
            potentialDivisor++;
        }
        return divisors;
    }

    private boolean potentiallyNewDivisor(int potentialDivisor, List<Integer> divisors) {
        for (Integer divisor : divisors) {
            if (potentialDivisor % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testCase3() {
        int[] A = {2048,6155,8209,2071,4120,8222,31,32,4137,42,43,4144,4148,2114,8261,70,75,2130,2867,95,8288,6243,100,2152,8298,2158,2161,6265,8314,2172,4221,6279,2185,3778,142,8341,163,8356,8359,173,8366,8379,2237,2245,198,8392,4299,4303,210,6356,2275,2280,8425,2282,7207,2288,6386,8435,2292,4341,4344,6400,2306,4364,6413,4366,6426,2335,6433,2340,293,6442,300,4400,1758,8502,8515,6470,2375,8520,2382,4434,4435,4437,8537,2397,8545,356,6503,4456,8553,6511,2420,374,2441,4162,2446,8602,6557,6561,419,8618,4525,4530,2491,3148,4557,2511,4562,9294,8662,2521,8666,494,4592,6641,498,9983,508,2566,1452,4621,6671,6676,8730,4635,4638,4640,545,2603,556,4653,6710,6716,5557,6722,2631,4691,6765,624,2680,2683,4732,642,4742,2696,651,661,680,9388,2730,6829,8882,2743,696,699,4801,8900,4809,714,6867,2772,2169,6874,4827,4828,6880,737,6907,766,2518,2825,8972,2830,6931,8980,791,795,2522,6942,2848,2853,2865,6961,2866,4915,6965,6968,825,2875,9022,4928,833,837,6983,9034,4943,6992,2898,4238,855,7000,857,9050,7003,9052,827,7017,3220,901,906,9113,2885,2987,2988,943,7089,7090,5048,5760,7100,5053,5066,7115,5068,3026,9178,9182,1002,7149,1006,7154,1016,9215,5125,5129,5134,5135,5138,7187,9237,7192,1540,2906,9249,1567,5297,860,7210,5167,1084,1085,9278,5183,5196,3150,6669,9296,3156,1115,3172,9325,9405,3185,3262,5238,3192,7302,7308,7314,5267,7316,5269,9366,3229,7327,5287,1192,1196,9391,1201,1203,5301,3257,7711,1213,9406,9412,7368,3276,7377,9433,7386,5339,9449,5355,5361,3318,1272,3322,5373,9473,7426,3337,3338,9492,1306,5403,9512,8091,7730,5422,1329,9526,1336,5159,9531,7485,9541,1350,7507,3413,9559,9562,5469,1380,1384,5486,9585,5491,7543,9595,1404,9598,3456,7559,1418,3468,1428,5017,9635,5547,5548,3501,9653,7607,5561,2293,1474,1478,9672,5579,7634,7635,3540,9685,5590,1499,1503,1506,1519,5715,5620,5629,5632,1538,7683,3588,9741,9742,5650,1555,9752,1565,3615,474,1572,9765,6065,3629,1586,9781,1596,7745,3653,9798,3655,7754,3661,1618,1619,3670,5719,9146,5737,5742,6077,1649,7794,5751,7801,7806,1664,3723,1677,3726,7825,9879,7834,4720,5801,9898,5803,3765,5819,1727,5825,9922,1738,3791,7890,9945,9949,9950,3807,1760,9955,5868,3824,3825,3830,9980,9982,5887,5889,1797,5896,6402,5902,3859,1833,1847,3896,1849,7994,3899,2890,8000,3905,1858,3916,8025,858,5997,8049,1907,8065,1925,9198,6043,3996,1950,8095,1952,1956,8102,8104,6060,8113,1970,1973,1974,4025,8123,1981,6437,6091,4056,4057,8156,4065,3411,2038,4092,8190};
        LargestComponentSizeByCommonFactor solution = new LargestComponentSizeByCommonFactor();
        long start = System.currentTimeMillis();
        int actual = solution.largestComponentSize(A);
        System.out.println(String.format("testCase3 tool %s ms", System.currentTimeMillis() - start));
        assertEquals(438, actual);
    }

    @Test
    public  void testCase2() {
        int[] A = {65, 35, 43, 76, 15, 11, 81, 22, 55, 92, 31};
        LargestComponentSizeByCommonFactor solution = new LargestComponentSizeByCommonFactor();
        int actual = solution.largestComponentSize(A);
        assertEquals(9, actual);
    }

    @Test
    public void testCase1() {
        int[] A = {4, 6, 15, 35};
        LargestComponentSizeByCommonFactor solution = new LargestComponentSizeByCommonFactor();
        int actual = solution.largestComponentSize(A);
        assertEquals(4, actual);
    }

    @Test
    public void testSolutionOneCase3() {
        int[] A = {2048,6155,8209,2071,4120,8222,31,32,4137,42,43,4144,4148,2114,8261,70,75,2130,2867,95,8288,6243,100,2152,8298,2158,2161,6265,8314,2172,4221,6279,2185,3778,142,8341,163,8356,8359,173,8366,8379,2237,2245,198,8392,4299,4303,210,6356,2275,2280,8425,2282,7207,2288,6386,8435,2292,4341,4344,6400,2306,4364,6413,4366,6426,2335,6433,2340,293,6442,300,4400,1758,8502,8515,6470,2375,8520,2382,4434,4435,4437,8537,2397,8545,356,6503,4456,8553,6511,2420,374,2441,4162,2446,8602,6557,6561,419,8618,4525,4530,2491,3148,4557,2511,4562,9294,8662,2521,8666,494,4592,6641,498,9983,508,2566,1452,4621,6671,6676,8730,4635,4638,4640,545,2603,556,4653,6710,6716,5557,6722,2631,4691,6765,624,2680,2683,4732,642,4742,2696,651,661,680,9388,2730,6829,8882,2743,696,699,4801,8900,4809,714,6867,2772,2169,6874,4827,4828,6880,737,6907,766,2518,2825,8972,2830,6931,8980,791,795,2522,6942,2848,2853,2865,6961,2866,4915,6965,6968,825,2875,9022,4928,833,837,6983,9034,4943,6992,2898,4238,855,7000,857,9050,7003,9052,827,7017,3220,901,906,9113,2885,2987,2988,943,7089,7090,5048,5760,7100,5053,5066,7115,5068,3026,9178,9182,1002,7149,1006,7154,1016,9215,5125,5129,5134,5135,5138,7187,9237,7192,1540,2906,9249,1567,5297,860,7210,5167,1084,1085,9278,5183,5196,3150,6669,9296,3156,1115,3172,9325,9405,3185,3262,5238,3192,7302,7308,7314,5267,7316,5269,9366,3229,7327,5287,1192,1196,9391,1201,1203,5301,3257,7711,1213,9406,9412,7368,3276,7377,9433,7386,5339,9449,5355,5361,3318,1272,3322,5373,9473,7426,3337,3338,9492,1306,5403,9512,8091,7730,5422,1329,9526,1336,5159,9531,7485,9541,1350,7507,3413,9559,9562,5469,1380,1384,5486,9585,5491,7543,9595,1404,9598,3456,7559,1418,3468,1428,5017,9635,5547,5548,3501,9653,7607,5561,2293,1474,1478,9672,5579,7634,7635,3540,9685,5590,1499,1503,1506,1519,5715,5620,5629,5632,1538,7683,3588,9741,9742,5650,1555,9752,1565,3615,474,1572,9765,6065,3629,1586,9781,1596,7745,3653,9798,3655,7754,3661,1618,1619,3670,5719,9146,5737,5742,6077,1649,7794,5751,7801,7806,1664,3723,1677,3726,7825,9879,7834,4720,5801,9898,5803,3765,5819,1727,5825,9922,1738,3791,7890,9945,9949,9950,3807,1760,9955,5868,3824,3825,3830,9980,9982,5887,5889,1797,5896,6402,5902,3859,1833,1847,3896,1849,7994,3899,2890,8000,3905,1858,3916,8025,858,5997,8049,1907,8065,1925,9198,6043,3996,1950,8095,1952,1956,8102,8104,6060,8113,1970,1973,1974,4025,8123,1981,6437,6091,4056,4057,8156,4065,3411,2038,4092,8190};
        LargestComponentSizeByCommonFactor solution = new LargestComponentSizeByCommonFactor();
        long start = System.currentTimeMillis();
        int actual = solution.solutionOne(A);
        System.out.println(String.format("testSolutionOneCase3 tool %s ms", System.currentTimeMillis() - start));
        assertEquals(438, actual);
    }

    @Test
    public  void testSolutionOneCase2() {
        int[] A = {65, 35, 43, 76, 15, 11, 81, 22, 55, 92, 31};
        LargestComponentSizeByCommonFactor solution = new LargestComponentSizeByCommonFactor();
        int actual = solution.solutionOne(A);
        assertEquals(9, actual);
    }

    @Test
    public void testSolutionOneCase1() {
        int[] A = {4, 6, 15, 35};
        LargestComponentSizeByCommonFactor solution = new LargestComponentSizeByCommonFactor();
        int actual = solution.solutionOne(A);
        assertEquals(4, actual);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(LargestComponentSizeByCommonFactor.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
