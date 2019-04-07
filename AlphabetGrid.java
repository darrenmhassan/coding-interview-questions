import java.util.ArrayList;
import java.util.List;

/**
 *
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
        for (final Coordinate c : coordinateList) {
            System.out.print(alphabetGrid[c.y][c.x]);
        }
    }

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

    private static List<Coordinate> toCoordinateList(final String s,
                                                     final int n) {

        final List<Coordinate> coordinateList = new ArrayList<>();
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            // this step requires an understanding of ASCII character encoding
            int offset = charArray[i] - 'A';
            coordinateList.add(new Coordinate(offset % n, offset / n));
        }
        return coordinateList;
    }
}
