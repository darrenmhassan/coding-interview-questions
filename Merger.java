import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Merger<T extends Comparable<T>> {

    public static void main(String args[]) {

        final IntStream intStream1 = IntStream.generate(
                () -> ThreadLocalRandom.current().nextInt(100)).limit(10);
        final List<Integer> l1 = toList(intStream1.toArray());
        Collections.sort(l1);
        System.out.println("List1: " + l1);
        final IntStream intStream2 = IntStream.generate(
                () -> ThreadLocalRandom.current().nextInt(100)).limit(15);
        final List<Integer> l2 = toList(intStream2.toArray());
        Collections.sort(l2);
        System.out.println("List2: " + l2);
        final Merger<Integer> merger = new Merger<>();
        final List<Integer> sortedList =
                merger.mergeSortedLists(l1, l2);
        for (final Integer i : sortedList) {
            System.out.print(i + ", ");
        }
    }

    public List<T> mergeSortedLists(final List<T> l1,
                                    final List<T> l2) {

        List<T> sortedList = new ArrayList<>();
        int numElem = l1.size() + l2.size();
        for (int i = 0; i < numElem; i++) {
            if (l1.get(0).compareTo(l2.get(0)) < 0) {
                sortedList.add(l1.get(0));
                l1.remove(0);
                if (l1.isEmpty()) {
                    sortedList.addAll(l2);
                    break;
                }
            } else {
                sortedList.add(l2.get(0));
                l2.remove(0);
                if (l2.isEmpty()) {
                    sortedList.addAll(l1);
                    break;
                }
            }
        }
        return sortedList;
    }

    private static List<Integer> toList(final int[] a) {
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            l.add(a[i]);
        }
        return l;
    }
}
