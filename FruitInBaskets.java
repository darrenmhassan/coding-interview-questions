import java.util.ArrayList;
import java.util.List;

/**
 * Problem: 904
 * Source: https://leetcode.com/problems/fruit-into-baskets/
 */
public class FruitInBaskets {
    public int totalFruit(int[] tree) {
        int mostFruitCollected = Integer.MIN_VALUE;
        for (int i = 0; i < tree.length; i++) {
            int firstType = -1;
            int secondType = -1;
            List<Integer> fruitCollected = new ArrayList<>();
            for (int j = i; j < tree.length; j++) {
                if (firstType == -1 || firstType == tree[j]) {
                    firstType = tree[j];
                    fruitCollected.add(tree[j]);
                } else if (secondType == -1 || secondType == tree[j]) {
                    secondType = tree[j];
                    fruitCollected.add(tree[j]);
                } else {
                    break;
                }
            }
            if (fruitCollected.size() > mostFruitCollected) {
                mostFruitCollected = fruitCollected.size();
            }
            if (mostFruitCollected == tree.length) {
                break;
            }
        }
        return mostFruitCollected;
    }
}
