import java.util.HashSet;
import java.util.Set;

/**
 * Problem 1466: Reorder Routes to Make All Paths Lead to the City Zero
 * Source: https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/
 */
public class ReorderRoutesToCityZero {
    public int minReorder(int n, int[][] connections) {
        int reorderCnt = 0;
        //List<Integer> exploredCities = new ArrayList<>();
        Set<Integer> exploredCities = new HashSet<>();
        exploredCities.add(0);
        //List<Integer> newCities = new ArrayList<>();
        //List<Integer> cities = Arrays.asList(0);
        Set<Integer> newCities = new HashSet<>();
        Set<Integer> cities = new HashSet<>();
        cities.add(0);

        while (exploredCities.size() < n) {
            for (int i = 0; i < n-1; i++) {
                if (cities.contains(connections[i][0])
                        && !exploredCities.contains(connections[i][1])) {
                    reorderCnt++;
                    newCities.add(connections[i][1]);
                    exploredCities.add(connections[i][1]);
                } else if (cities.contains(connections[i][1])
                        && !exploredCities.contains(connections[i][0])) {
                    newCities.add(connections[i][0]);
                    exploredCities.add(connections[i][0]);
                }
            }
            cities = newCities;
            //newCities = new ArrayList<>();
            newCities = new HashSet<>();
        }
        return reorderCnt;
    }
}
