import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Question: Given a set of non-overlapping intervals,
 * insert a new interval into the intervals (merge if necessary).
 *
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 *
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 *
 * Source: https://leetcode.com/problems/insert-interval/
 */
class InsertInterval {

    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals == null || intervals.isEmpty()) {
            return Collections.singletonList(newInterval);
        }
        if (newInterval == null || newInterval.start > newInterval.end) {
            return intervals;
        }
        List<Interval> intervalsCopy = new ArrayList<>(intervals);
        List<Interval> overlappingIntervals = new ArrayList<>();
        for (Interval interval : intervals) {
            //  ---
            // ---
            if (newInterval.start >= interval.start && newInterval.start <= interval.end) {
                intervalsCopy.remove(interval);
                overlappingIntervals.add(interval);
                // -----
                //  ---
            } else if (newInterval.start <= interval.start && newInterval.end >= interval.end) {
                intervalsCopy.remove(interval);
                overlappingIntervals.add(interval);
                // ---
                //  ---
            } else if (newInterval.end >= interval.start && newInterval.end <= interval.end) {
                intervalsCopy.remove(interval);
                overlappingIntervals.add(interval);
                //  -
                // ---
            } else if (newInterval.start >= interval.start && newInterval.end <= interval.end) {
                intervalsCopy.remove(interval);
                overlappingIntervals.add(interval);
            }
        }
        Interval mergedInterval = mergeIntervals(overlappingIntervals, newInterval);
        List<Interval> ret = new ArrayList<>();
        boolean merged = false;
        for (Interval i : intervalsCopy) {
            if (!merged) {
                if (i.start > mergedInterval.start) {
                    ret.add(mergedInterval);
                    merged = true;
                }
            }
            ret.add(i);
        }
        if (!merged) {
            ret.add(mergedInterval);
            return ret;
        }
        return ret;
    }

    private Interval mergeIntervals(List<Interval> overlappingIntervals, Interval newInterval) {
        if (overlappingIntervals.isEmpty()) {
            return newInterval;
        }
        return new Interval(minStart(overlappingIntervals, newInterval),
                maxEnd(overlappingIntervals, newInterval));
    }

    private int minStart(List<Interval> overlappingIntervals, Interval newInterval) {
        int minStart = newInterval.start;
        for (Interval i : overlappingIntervals) {
            if (i.start < minStart) {
                minStart = i.start;
            }
        }
        return minStart;
    }

    private int maxEnd(List<Interval> overlappingIntervals, Interval newInterval) {
        int maxEnd = newInterval.end;
        for (Interval i : overlappingIntervals) {
            if (i.end > maxEnd) {
                maxEnd = i.end;
            }
        }
        return maxEnd;
    }
}
