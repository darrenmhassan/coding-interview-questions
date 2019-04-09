package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Question: given a number n and string, where n is the width of a grid
 * that contains the characters of the alphabet a-to-z, find the coordinates
 * for each character in the string.
 *
 * Example: where n is 5 the grid would consist of:
 * [[A, B, C, D, E]
 *  [F, G, H, I, J]
 *  [K, L, M, N, O]
 *  [P, Q, R, S, T]
 *  [U, V, W, X, Y]
 *  [Z]]
 *
 *  [[ 0, 0], [ 0, 1], ...
 *   [ 1, 0], [ 1, 1], ...
 *   ...
 *   [ 5, 0]]
 * The coordinates of the String "PRIME" are:
 *   [[ 0, 3 ], [ 2, 3 ], [ 3, 1 ], [ 2, 2 ], [ 4, 0 ]]
 *
 * Source: Google phone screen
 */
public class AlphabetGrid {

    private static final char[][] alphabetGrid = new char[][] {
        {'A', 'B', 'C', 'D', 'E'},
        {'F', 'G', 'H', 'I', 'J'},
        {'K', 'L', 'M', 'N', 'O'},
        {'P', 'Q', 'R', 'S', 'T'},
        {'U', 'V', 'W', 'X', 'Y'},
        {'Z'}
    };


    public static void main(String[] args) {

        final String s = "PRIME";
        final int n = 5;
        final List<Coordinate> coordinateList =
                AlphabetGrid.toCoordinateList(s, n);
        System.out.println(String.format(
                "the coordinates of %s are: %s",
                s,
                coordinateList));
        for (int i = 0; i < alphabetGrid.length; i++) {
            for (int j = 0; j < alphabetGrid[i].length; j++) {
                System.out.print(alphabetGrid[i][j] + ", ");
            }
            System.out.println();
        }
        for (final Coordinate c : coordinateList) {
            System.out.print(alphabetGrid[c.row][c.offset]);
        }
    }

    private static class Coordinate {

        private int offset;
        private int row;

        public Coordinate(final int row, final int offset) {
            this.offset = offset;
            this.row = row;
        }

        public String toString() {
            return "[ " + offset + ", " + row +" ]";
        }
    }

    private static List<Coordinate> toCoordinateList(final String s,
                                                     final int n) {

        final List<Coordinate> coordinateList = new ArrayList<>();
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            // this step requires an understanding of ASCII character encoding
            int offset = charArray[i] - 'A';
            coordinateList.add(new Coordinate(offset / n, offset % n));
        }
        return coordinateList;
    }
}
