import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Question:  Given an array A, we may rotate it by a non-negative integer K
 * so that the array becomes A[K], A[K+1], A{K+2], ... A[A.length - 1], A[0], A[1], ..., A[K-1].
 * Afterward, any entries that are less than or equal to their index are worth 1 point.
 *
 * For example, if we have [2, 4, 1, 3, 0], and we rotate by K = 2, it becomes [1, 3, 0, 2, 4].
 * This is worth 3 points because 1 > 0 [no points], 3 > 1 [no points], 0 <= 2 [one point],
 * 2 <= 3 [one point], 4 <= 4 [one point].
 *
 * Over all possible rotations, return the rotation index K that corresponds to the highest
 * score we could receive.  If there are multiple answers, return the smallest such index K.
 *
 * Example:
 * Example 1:
 * Input: [2, 3, 1, 4, 0]
 * Output: 3
 * Explanation:
 * Scores for each K are listed below:
 * K = 0,  A = [2,3,1,4,0],    score 2
 * K = 1,  A = [3,1,4,0,2],    score 3
 * K = 2,  A = [1,4,0,2,3],    score 3
 * K = 3,  A = [4,0,2,3,1],    score 4
 * K = 4,  A = [0,2,3,1,4],    score 3
 *
 * Source: https://leetcode.com/problems/smallest-rotation-with-highest-score/
 */
public class SmallestRotationWithHighestScoreEfficient {

    public int bestRotation(int[] A) {

        TreeMap<Integer, Queue<Integer>> scoreToK = new TreeMap<>();

        for (int k = 0; k < A.length; k++) {
            int score = calcScore(A, k);

            if (scoreToK.containsKey(score)) {
                Queue<Integer> q = scoreToK.get(score);
                q.add(k);
            } else {
                Queue<Integer> q = new PriorityQueue<>();
                q.add(k);
                scoreToK.put(score, q);
            }
        }
        int highestScore = scoreToK.lastKey();
        Queue<Integer> ks = scoreToK.get(highestScore);
        return ks.peek();
    }

    private int calcScore(int[] A, int k) {
        int score = 0;
        int i = 0;
        for (i = 0; i < A.length; i++) {
            if (A[i + k] <= i) {
                score++;
            }
        }
        for (int j = 0; j < k; j++) {
            if (A[j] <= i++) {
                score++;
            }
        }
        return score;
    }
}
