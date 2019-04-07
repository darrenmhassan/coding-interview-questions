import java.util.Arrays;

/**
 * Print concentric rectangular pattern in a 2d matrix.
 * Let us show you some examples to clarify what we mean.
 *
 * Example 1:
 *
 * Input: A = 4.
 * Output:
 *
 * 4 4 4 4 4 4 4
 * 4 3 3 3 3 3 4
 * 4 3 2 2 2 3 4
 * 4 3 2 1 2 3 4
 * 4 3 2 2 2 3 4
 * 4 3 3 3 3 3 4
 * 4 4 4 4 4 4 4
 *
 * Example 2:
 *
 * Input: A = 3.
 * Output:
 *
 * 3 3 3 3 3
 * 3 2 2 2 3
 * 3 2 1 2 3
 * 3 2 2 2 3
 * 3 3 3 3 3
 *
 * The outermost rectangle is formed by A, then the next outermost is formed by A-1 and so on.
 *
 * You will be given A as an argument to the function you need to implement, and you need to return a 2D array.
 */
public class PrettyPrint {

    private int A;
    public int[][] prettyPrint(int A) {
        if (A <= 0) {
            return new int[0][0];
        }
        this.A = A;
        int hw = A + (A - 1);
        int[][] grid = new int[hw][hw];
        return paintGrid(grid, A);
    }

    private int[][] paintGrid(int[][] grid, int B) {
        int hw = B + (B - 1);
        int c = hw / 2;
        if (B == 1) {
            grid[grid.length/2][grid.length/2] = 1;
            return grid;
        }
        int startIdx = this.A - B;
        for (int x = startIdx; x < startIdx + hw; x++) {
            for (int y = startIdx; y < startIdx + hw; y++) {
                grid[y][x] = B;
            }
        }
        return paintGrid(grid, B - 1);
    }

    public static void main(String[] args) {
        PrettyPrint prettyPrint = new PrettyPrint();
        int[][] grid = prettyPrint.prettyPrint(5);
        for (int i = 0; i < grid[0].length; i++) {
            System.out.println(String.format("row %s: %s", i, Arrays.toString(grid[i])));
        }
    }
}
