package iv;

import java.util.Random;

/**
 * Created by dhssn on 10/20/16.
 */
public class IslandCounting {

    private static final int GRID_WIDTH = 10;
    private static final int GRID_HEIGHT = 11;
    private static final int[][] grid = new int[GRID_WIDTH][GRID_HEIGHT];

    public static void main(final String args[]) {

        final Random r = new Random();
        for (int y = 0; y < GRID_HEIGHT; y++) {
            for (int x = 0; x < GRID_WIDTH; x++) {
                if (r.nextBoolean()) {
                    grid[x][y] = 1;
                } else {
                    grid[x][y] = 0;
                }
            }
        }
        printGrid(grid);
        System.out.println("There are " + islandCount(grid) + " islands");
        printGrid(grid);
    }

    private static int islandCount(final int[][] grid) {

        int islandCnt = 0;
        for (int y = 0; y < GRID_HEIGHT; y++) {
            for (int x = 0; x < GRID_WIDTH; x++) {

                if (grid[x][y] == 1) {
                    islandCnt++;
                    lookForAdjLand(grid, x, y);
                }
            }
        }
        return islandCnt;
    }

    private static void lookForAdjLand(final int[][] grid,
                                       final int x,
                                       final int y) {
        if (grid[x][y] == 1) {
            grid[x][y] = 2;
            if (x + 1 < GRID_WIDTH) {
                lookForAdjLand(grid, x + 1, y);
            }
            if (x > 0) {
                lookForAdjLand(grid, x - 1, y);
            }
            if (y + 1 < GRID_HEIGHT) {
                lookForAdjLand(grid, x, y + 1);
            }
            if (y > 0) {
                lookForAdjLand(grid, x, y - 1);
            }
        }
    }

    private static void printGrid(final int[][] grid) {

        for (int y = 0; y < GRID_HEIGHT; y++) {
            for (int x = 0; x < GRID_WIDTH; x++) {
                System.out.print(grid[x][y] + " ");
            }
            System.out.println();
        }
    }
}
