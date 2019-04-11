package knightsDialer;

import java.util.*;
import java.util.HashMap;

/**
 * Blog post: https://hackernoon.com/google-interview-questions-deconstructed-the-knights-dialer-f780d516f029
 */
public class KnightsDialer {

    private static Map<Integer, List<Integer>> neighbours = new HashMap<>();
    static {
        neighbours.put(0, Arrays.asList(4, 6));
        neighbours.put(1, Arrays.asList(6, 8));
        neighbours.put(2, Arrays.asList(7, 9));
        neighbours.put(3, Arrays.asList(4, 8));
        neighbours.put(4, Arrays.asList(0, 3, 9));
        neighbours.put(5, new ArrayList<>());
        neighbours.put(6, Arrays.asList(0, 1, 7));
        neighbours.put(7, Arrays.asList(2, 6));
        neighbours.put(8, Arrays.asList(1, 3));
        neighbours.put(9, Arrays.asList(2, 4));
    }

    private int count = 0;
    private List<String> path = new ArrayList<>();


    public int getN(int startingPos,
                    int numberOfHops) {
        if (!neighbours.containsKey(startingPos) || numberOfHops < 1) {
            throw new IllegalArgumentException("Invalid starting position or number of hops");
        } else if (numberOfHops == 1) {
            return 1;
        }
        path.add(hopsToString(Arrays.asList(startingPos)));
        List<Integer> neighbours = getNeighbours(startingPos);
        getN(neighbours, numberOfHops-1);
        return count;
    }

    public String getPath() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(String.join(", ", path.get(i)));
        }
        return sb.toString();
    }

    private String hopsToString(List<Integer> hops) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        hops.stream().forEach(s -> sb.append(s + ", "));
        sb.append("}");
        return sb.toString();
    }

    private int getN(List<Integer> positions,
                     int numberOfHops) {
        path.add(hopsToString(positions));
        if (numberOfHops > 1) {
            for (Integer pos : positions) {
                List<Integer> neighours = getNeighbours(pos);
                count += getN(neighours, numberOfHops-1);
            }
        }
        return positions.size();
    }

    private List<Integer> getNeighbours(int pos) {
        if (!neighbours.containsKey(pos)) {
            throw new IllegalArgumentException("Invalid position: " + pos);
        }
        return neighbours.get(pos);
    }
}
