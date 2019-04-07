import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * https://medium.com/solvingalgo/solving-algorithmic-problems-possible-hotel-bookings-fa3a544c6683
 */
public class FulfillmentCenterPacking {

    public boolean isThereCapacityWithSorting(List<Integer> arr,
                                              List<Integer> dep,
                                              int k) {
        Collections.sort(arr);
        Collections.sort(dep);

        int a, d, occupacy;
        a = d = occupacy = 0;
        while (a < arr.size()) {
            if (arr.get(a) < dep.get(d)) {
                occupacy++;
                a++;
            } else {
                occupacy--;
                d++;
            }
            if (occupacy > k) {
                return false;
            }
        }
        return true;
    }

    public boolean isThereCapacityBlogMap(List<Integer> arrivals,
                                          List<Integer> departures,
                                          int k) {
        // Collection of events
        Map<Integer, Integer> events = new HashMap<>();

        // Number of rooms
        int n = arrivals.size();

        for (int i = 0; i < n; i++) {
            int arrival = arrivals.get(i);
            int departure = departures.get(i);

            // Add one during an arrival
            Integer current = events.get(arrival);
            events.put(arrival, current == null ? 1 : current + 1);

            // Remove one during a departure
            current = events.get(departure);
            events.put(departure, current == null ? -1 : current - 1);
        }

        // Sort the map
        Map<Integer, Integer> sortedEvents = new TreeMap<>(events);

        int count = 0;
        for (Map.Entry<Integer, Integer> entry : sortedEvents.entrySet()) {
            count += entry.getValue();

            // If the current count exceeds the maximum number of rooms
            if (count > k) {
                return false;
            }
        }

        return true;
    }

    public boolean isThereCapacityWithMap(List<Integer> arr,
                                      List<Integer> dep,
                                      int k) {

        Map<Integer, Integer> occupacy = new HashMap<>();
        for (int i = 0; i < arr.size(); i++) {
            int s = arr.get(i);
            int e = dep.get(i);
            for (int d = s; d < e; d++) {
                if (occupacy.containsKey(d)) {
                    occupacy.put(d, occupacy.get(d) + 1);
                } else {
                    occupacy.put(d, 1);
                }
            }
        }
        // The natural order of integers results in a min priority queue
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 < o2 ? 1 : o1 > o2 ? -1 : 0;
            }
        });
        pq.addAll(occupacy.values());
        if (pq.peek() > k) {
            return false;
        }
        return true;
    }

    public boolean isThereCapacityWithArray(List<Integer> arr,
                                            List<Integer> dep,
                                            int k) {
        int[] occupacy = new int[getMaxDay(dep)];
        for (int i = 0; i < arr.size(); i++) {
            int start = arr.get(i);
            int end = dep.get(i);
            for (int d = start; d < end; d++) {
                occupacy[d] += 1;
            }
        }
        for (int i = 0; i < occupacy.length; i++) {
            if (occupacy[i] > k) {
                return false;
            }
        }
        return true;
    }

    private int getMaxDay(List<Integer> array) {
        int maxDay = 0;
        for (Integer day : array) {
            if (day > maxDay) {
                maxDay = day;
            }
        }
        return maxDay;
    }

    @Test
    public void case3Sort() {
        List<Integer> arr = Arrays.asList(2, 3, 4);
        List<Integer> dep = Arrays.asList(10, 5, 7);
        int k = 3;
        FulfillmentCenterPacking solution = new FulfillmentCenterPacking();
        boolean actual = solution.isThereCapacityWithSorting(arr, dep, k);
        assertEquals(true, actual);
    }

    @Test
    public void case2Sort() {
        List<Integer> arr = Arrays.asList(2, 4, 5);
        List<Integer> dep = Arrays.asList(3, 6, 10);
        int k = 1;
        FulfillmentCenterPacking solution = new FulfillmentCenterPacking();
        boolean actual = solution.isThereCapacityWithSorting(arr, dep, k);
        assertEquals(false, actual);
    }

    @Test
    public void case1Sort() {
        List<Integer> arr = Arrays.asList(1, 3, 5);
        List<Integer> dep = Arrays.asList(2, 6, 10);
        int k = 1;
        FulfillmentCenterPacking solution = new FulfillmentCenterPacking();
        boolean actual = solution.isThereCapacityWithSorting(arr, dep, k);
        assertEquals(false, actual);
    }


    @Test
    public void case3BlogMap() {
        List<Integer> arr = Arrays.asList(2, 3, 4);
        List<Integer> dep = Arrays.asList(10, 5, 7);
        int k = 3;
        FulfillmentCenterPacking solution = new FulfillmentCenterPacking();
        boolean actual = solution.isThereCapacityBlogMap(arr, dep, k);
        assertEquals(true, actual);
    }

    @Test
    public void case2BlogMap() {
        List<Integer> arr = Arrays.asList(2, 4, 5);
        List<Integer> dep = Arrays.asList(3, 6, 10);
        int k = 1;
        FulfillmentCenterPacking solution = new FulfillmentCenterPacking();
        boolean actual = solution.isThereCapacityBlogMap(arr, dep, k);
        assertEquals(false, actual);
    }

    @Test
    public void case1BlogMap() {
        List<Integer> arr = Arrays.asList(1, 3, 5);
        List<Integer> dep = Arrays.asList(2, 6, 10);
        int k = 1;
        FulfillmentCenterPacking solution = new FulfillmentCenterPacking();
        boolean actual = solution.isThereCapacityBlogMap(arr, dep, k);
        assertEquals(false, actual);
    }

    @Test
    public void case3Map() {
        List<Integer> arr = Arrays.asList(2, 3, 4);
        List<Integer> dep = Arrays.asList(10, 5, 7);
        int k = 3;
        FulfillmentCenterPacking solution = new FulfillmentCenterPacking();
        boolean actual = solution.isThereCapacityWithMap(arr, dep, k);
        assertEquals(true, actual);
    }

    @Test
    public void case2Map() {
        List<Integer> arr = Arrays.asList(2, 4, 5);
        List<Integer> dep = Arrays.asList(3, 6, 10);
        int k = 1;
        FulfillmentCenterPacking solution = new FulfillmentCenterPacking();
        boolean actual = solution.isThereCapacityWithMap(arr, dep, k);
        assertEquals(false, actual);
    }

    @Test
    public void case1Map() {
        List<Integer> arr = Arrays.asList(1, 3, 5);
        List<Integer> dep = Arrays.asList(2, 6, 10);
        int k = 1;
        FulfillmentCenterPacking solution = new FulfillmentCenterPacking();
        boolean actual = solution.isThereCapacityWithMap(arr, dep, k);
        assertEquals(false, actual);
    }

    @Test
    public void case3Array() {
        List<Integer> arr = Arrays.asList(2, 3, 4);
        List<Integer> dep = Arrays.asList(10, 5, 7);
        int k = 3;
        FulfillmentCenterPacking solution = new FulfillmentCenterPacking();
        boolean actual = solution.isThereCapacityWithArray(arr, dep, k);
        assertEquals(true, actual);
    }

    @Test
    public void case2Array() {
        List<Integer> arr = Arrays.asList(2, 4, 5);
        List<Integer> dep = Arrays.asList(3, 6, 10);
        int k = 1;
        FulfillmentCenterPacking solution = new FulfillmentCenterPacking();
        boolean actual = solution.isThereCapacityWithArray(arr, dep, k);
        assertEquals(false, actual);
    }

    @Test
    public void case1Array() {
        List<Integer> arr = Arrays.asList(1, 3, 5);
        List<Integer> dep = Arrays.asList(2, 6, 10);
        int k = 1;
        FulfillmentCenterPacking solution = new FulfillmentCenterPacking();
        boolean actual = solution.isThereCapacityWithArray(arr, dep, k);
        assertEquals(false, actual);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(FulfillmentCenterPacking.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
