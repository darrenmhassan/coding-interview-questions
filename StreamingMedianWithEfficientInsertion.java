package iv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Created by dhssn on 4/5/17.
 */
public class StreamingMedianWithEfficientInsertion {

    private Integer[] buffer;
    private int startOfBuffer = 0;
    private boolean lessThanStreamSize = true;

    public static void main(String args[]) {

        final StreamingMedianWithEfficientInsertion o = new StreamingMedianWithEfficientInsertion(9);
        final IntStream intStream = IntStream.iterate(0, i -> i + 1).limit(100);
//        final IntStream intStream = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(100)).limit(100);
        final int[] intArray = intStream.toArray();
        for (int i = 0; i < intArray.length; i++) {

            System.out.println("Inserting: " + intArray[i]);
            o.insertValue(intArray[i]);
            System.out.println("Streaming median: " + o.getStreamingMedian());
        }
    }

    public StreamingMedianWithEfficientInsertion(int streamSize) {
        buffer = new Integer[streamSize];
    }

    /**
     * Constant time insertion.
     *
     * @param num
     */
    public void insertValue(int num) {

        buffer[startOfBuffer] = num;
        // tricky: implementing a circular queue
        if (startOfBuffer + 1 >= buffer.length) {
            startOfBuffer = 0;
            lessThanStreamSize = false;
        } else {
            startOfBuffer++;
        }
    }

    /**
     * O(n log n) time using merge sort to sort the buffer
     *
     * @return
     */
    public double getStreamingMedian() {

        // tricky: sorting the stream of values
        // I cannot convert the buffer to an ArrayList with:
        //   Arrays.<Integer>asList(buffer)
        // because doing so would create an ArrayList that contains null
        // when the buffer is not full.
        final List<Integer> sortedList = new ArrayList();
        if (lessThanStreamSize) {
            for (int i = 0; i < startOfBuffer; i++) {
                sortedList.add(buffer[i]);
            }
        } else {
            for (int i = 0; i < buffer.length; i++) {
                sortedList.add(buffer[i]);
            }
        }
        // O(n log n)
        Collections.sort(sortedList);

        int streamSize = sortedList.size();
        int midPoint = streamSize / 2;
        if (streamSize % 2 == 1) {
            return sortedList.get(midPoint);
        } else {
            return (double)(sortedList.get(midPoint - 1) + sortedList.get(midPoint)) / 2d;
        }
    }
}
