import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

/**
 * Created by dhssn on 10/23/16.
 */
public class WordLadderWithDijkstraDemo {

    private static class WordLadder {

        private static class Pair implements Comparable<Pair> {

            private final String word;
            private Integer dist;

            public Pair(final String word, final Integer dist) {
                this.word = word;
                this.dist = dist;
            }

            @Override
            public int compareTo(final Pair other) {
                return this.dist - other.dist;
            }
        }

        public List<String> findShortestPath(final List<String> dictionary,
                                     final String start,
                                     final String end) {

            final Set<String> q = new HashSet<>(dictionary);
            final Map<String, Integer> distFromStart = new HashMap<>();
            for (final String w : dictionary) {
                if (!w.equals(start)) {

//                    final Pair p = new Pair(w, Integer.MAX_VALUE);
//                    q.add(p);
//                    distFromStart.add(p);
                    distFromStart.put(w, Integer.MAX_VALUE);

                }
            }
//            final Pair p = new Pair(start, 0);
//            q.add(p);
//            distFromStart.add(p);
            distFromStart.put(start, 0);
            final Map<String, String> prev = new HashMap<>();

            int altDist = 0;
            while (!q.isEmpty()) {

                altDist++;
                final String u = getMinDist(distFromStart);
                q.remove(u);

                for (final String v : q) {
                    if (isOneCharDiff(u, v)) {
                        if (altDist < distFromStart.get(v)) {
                            distFromStart.put(v, altDist);
                            prev.put(v, u);
                        }
                    }
                }
            }

            return getRoute(prev, start, end);
        }

        private boolean isOneCharDiff(final String w1, final String w2) {

            int diff = 0;
            for (int i = 0; i < w1.length(); i++) {
                if (w1.charAt(i) != w2.charAt(i)) {
                    diff++;
                }
            }
            return diff == 1;
        }

        private String getMinDist(final Map<String, Integer> distFromStart) {

            String w = null;
            int minDist = Integer.MIN_VALUE;
            for (final Map.Entry<String, Integer> s : distFromStart.entrySet()) {
                if (s.getValue() < minDist) {
                    w = s.getKey();
                    minDist = s.getValue();
                }
            }
            return w;
        }

        private List<String> getRoute(final Map<String, String> prev,
                                      final String start,
                                      final String end) {

            final Stack<String> s = new Stack<>();
            s.push(end);
            String next = end;
            do {
                next = prev.get(next);
                s.push(next);

            } while (!next.equals(start));

            final List<String> route = new ArrayList<>();
            while (!s.isEmpty()) {
                route.add(s.pop());
            }
            return route;
        }
    }

    public static void main(final String args[]) {

//        final Set<String> dictionary = new HashSet<>(Arrays.asList(
//                "MALL", "TALL", "FALL", "BALL", "BELL", "BELT", "BENT"));
        final List<String> dictionary = Arrays.asList(
                "MALL", "TALL", "FALL", "BALL", "BELL", "BELT", "BENT");
        final WordLadder wordLadder = new WordLadder();
        final List<String> route = wordLadder.findShortestPath(dictionary, "MALL", "BENT");
        for (final String w : route) {
            System.out.println(w);
        }
    }
}
