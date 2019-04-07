package iv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dhssn on 10/2/16.
 */
public class AlphabetGridWithPreComputedMap {

    private static class Coordinate {

        private int x;
        private int y;

        public Coordinate(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "[ " + x + ", " + y +" ]";
        }
    }

    private static final char[][] alphabetGrid = new char[][] {
            {'A', 'B', 'C', 'D', 'E'},
            {'F', 'G', 'H', 'I', 'J'},
            {'K', 'L', 'M', 'N', 'O'},
            {'P', 'Q', 'R', 'S', 'T'},
            {'U', 'V', 'W', 'X', 'Y'},
            {'Z'}
    };

    private static final Map<Character, Coordinate> letterToCoordinate =
            new HashMap<>();

    static {
        letterToCoordinate.put('A', new Coordinate(0, 0));
        letterToCoordinate.put('B', new Coordinate(1, 0));
        letterToCoordinate.put('C', new Coordinate(2, 0));
        letterToCoordinate.put('D', new Coordinate(3, 0));
        letterToCoordinate.put('E', new Coordinate(4, 0));
        letterToCoordinate.put('F', new Coordinate(0, 1));
        letterToCoordinate.put('G', new Coordinate(1, 1));
        letterToCoordinate.put('H', new Coordinate(2, 1));
        letterToCoordinate.put('I', new Coordinate(3, 1));
        letterToCoordinate.put('J', new Coordinate(4, 1));
        letterToCoordinate.put('K', new Coordinate(0, 2));
        letterToCoordinate.put('L', new Coordinate(1, 2));
        letterToCoordinate.put('M', new Coordinate(2, 2));
        letterToCoordinate.put('N', new Coordinate(3, 2));
        letterToCoordinate.put('O', new Coordinate(4, 2));
        letterToCoordinate.put('P', new Coordinate(0, 3));
        letterToCoordinate.put('Q', new Coordinate(1, 3));
        letterToCoordinate.put('R', new Coordinate(2, 3));
        letterToCoordinate.put('S', new Coordinate(3, 3));
        letterToCoordinate.put('T', new Coordinate(4, 3));
        letterToCoordinate.put('U', new Coordinate(0, 4));
        letterToCoordinate.put('V', new Coordinate(1, 4));
        letterToCoordinate.put('W', new Coordinate(2, 4));
        letterToCoordinate.put('X', new Coordinate(3, 4));
        letterToCoordinate.put('Y', new Coordinate(4, 4));
        letterToCoordinate.put('Z', new Coordinate(0, 5));
    }

    public static void main(String[] args) {

        final String s = "PRIME";
        final List<Coordinate> coordinateList =
                AlphabetGridWithPreComputedMap.toCoordinateList(s);
        System.out.println(String.format(
                "the coordinates of %s are: %s",
                s,
                coordinateList));
        for (final Coordinate c : coordinateList) {
            System.out.print(alphabetGrid[c.y][c.x]);
        }
    }

    private static List<Coordinate> toCoordinateList(final String s) {

        final List<Coordinate> coordinateList = new ArrayList<>();
        final char[] charArray = s.toCharArray();
        for (final char c : charArray) {
            coordinateList.add(letterToCoordinate.get(c));
        }
        return coordinateList;
    }
}
