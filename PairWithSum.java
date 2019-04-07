import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by dhssn on 2/16/17.
 */
public class PairWithSum {


    public static void main(final String argv[]) {

        int sortedNumbers[] = { 1, 3, 5, 7, 9 };
        int target = 9;

        printResult(sortedNumbers, target,
                sortedNumbersHavePairThatSumToTarget(sortedNumbers, target));

        int numbers[] = { 5, 1, 6, -3, 1 };
        int target2 = 11;

        printResult(numbers, target2,
                unsortedNumbersHavePairThatSumToTarget(numbers, target2));

        int numbers2[] = { 5, 1, 6, -3, 1, 2 };
        int n = 3;
        final int target3 = 10;
        printResult(numbers2, target3,
                unsortedNumbersHaveNNumbersThatWhenSummedEqualTheTarget(numbers2, target3, n),
                n);
    }

    private static boolean sortedNumbersHavePairThatSumToTarget(final int[] numbers,
                                                                final int target) {

        int start = 0;
        int end = numbers.length -1;
        while (start < end) {
            int sum = numbers[start] + numbers[end];
            if (sum == target) return true;
            if (sum > target) end--;
            if (sum < target) start++;
        }
        return false;
    }

    private static boolean unsortedNumbersHavePairThatSumToTarget(final int[] numbers,
                                                                  final int target) {

        final Set<Integer> compliment = new HashSet<>();
        for (int i = 0; i < numbers.length; i++) {
            if (compliment.contains(numbers[i])) {
                return true;
            }
            compliment.add(target - numbers[i]);
        }
        return false;
    }

    /**
     * Time complexity O(n * m)
     * Where:
     *  - n is the number of element to sum together.
     *  - m is the number of elements in the collection.
     */
    private static boolean unsortedNumbersHaveNNumbersThatWhenSummedEqualTheTarget(final int[] numbers,
                                                                                   final int target,
                                                                                   final int n) {

        if (n < 1 || n > numbers.length) {
            return false;
        }
        final Map<Integer, Integer> complimentToN = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (complimentToN.containsKey(numbers[i]) && complimentToN.get(numbers[i]) == 1) {
                return true;
            }
            final Map<Integer, Integer> tempMap = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : complimentToN.entrySet()) {
                if (entry.getValue() > 1) {
                    tempMap.put(entry.getKey() - numbers[i], entry.getValue() - 1);
                }
            }
            complimentToN.putAll(tempMap);
            complimentToN.put(target - numbers[i], n - 1);
        }
        return false;
    }

    private static void printResult(final int[] numbers,
                                    final int target,
                                    final boolean result) {

        printResult(numbers, target, result, 2);
    }

    private static void printResult(final int[] numbers,
                                    final int target,
                                    final boolean result,
                                    final int num) {

        StringBuilder sb = new StringBuilder();
        for (int n : numbers) {
            sb.append(n).append(", ");
        }

        if (result) {
            System.out.println(String.format(
                    "The numbers %s has %s numbers that when summed equals the target %s",
                    sb.toString(),
                    num,
                    target));
        } else {
            System.out.println(String.format(
                    "The numbers %s do not have %s numbers that when summed equals the target %s",
                    sb.toString(),
                    num,
                    target));
        }
    }
}
