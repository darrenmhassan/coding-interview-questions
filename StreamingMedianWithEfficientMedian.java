import java.util.Collections;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Created by dhssn on 4/5/17.
 */
public class StreamingMedianWithEfficientMedian {

    private int[] buffer;
    // if the numbers in the buffer are arranged in order,
    // the maxQueue is on the left of the median and
    // the minQueue is on the right of the median
    private PriorityQueue<Integer> maxQueue, minQueue;
    private int startOfBuffer = 0;
    private boolean bufferIsFull = false;


    public static void main(String args[]) {

        final StreamingMedianWithEfficientMedian o = new StreamingMedianWithEfficientMedian(9);
        final IntStream intStream = IntStream.iterate(0, i -> i + 1).limit(100);
//        final IntStream intStream = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(100)).limit(100);
        final int[] intArray = intStream.toArray();
        for (int i = 0; i < intArray.length; i++) {

            System.out.println("Inserting: " + intArray[i]);
            o.insertValue(intArray[i]);
            System.out.println("Streaming median: " + o.getStreamingMedian());
        }
    }


    public StreamingMedianWithEfficientMedian(int streamSize) {
        maxQueue = new PriorityQueue<>(Collections.reverseOrder());
        minQueue = new PriorityQueue<>();
        buffer = new int[streamSize];
    }

    /**
     * Time complexity of O(log n), which is the time needed to remove the
     * min value from the min heap or max value from the max heap.
     *
     * @param num
     */
    public void insertValue(int num) {

        // tricky: purging the priority queues/heaps of the oldest value
        if (bufferIsFull) {
            int valueToRemove = buffer[startOfBuffer];
            if (valueToRemove < maxQueue.peek()) {
                maxQueue.remove(valueToRemove);
            } else {
                minQueue.remove(valueToRemove);
            }
        }

        buffer[startOfBuffer] = num;
        if (startOfBuffer + 1 >= buffer.length) {
            startOfBuffer = 0;
            bufferIsFull = true;
        } else {
            startOfBuffer++;
        }

        // dealing with the first value
        if (maxQueue.isEmpty()) {
            maxQueue.offer(num);
            return;
        }

        // dealing with the second value
        if (minQueue.isEmpty()) {
            if (num < maxQueue.peek()) {
                minQueue.offer(maxQueue.poll());
                maxQueue.offer(num);
            } else {
                minQueue.offer(num);
            }
            return;
        }

        // if the priority queues are equal in size add the new value to the
        // appropriate priority queue
        if (minQueue.size() == maxQueue.size()) {
            if (num < maxQueue.peek()) {
                maxQueue.offer(num);
            } else {
                minQueue.offer(num);
            }
        } else {
            // if the priority queues are different in size by one
            if (num < maxQueue.peek()) {
                // and the new value belongs in the max queue
                if (maxQueue.size() > minQueue.size()) {
                    // and the max queue is larger than the min queue
                    // move the max value from the max queue into the min queue
                    minQueue.offer(maxQueue.poll());
                }
                // add the new value to the max queue
                maxQueue.offer(num);
            } else {
                // and the new value belongs in the min queue
                if (minQueue.size() > maxQueue.size()) {
                    // and the min queue is larger than the max queue
                    // move the min value from the min queue into the max queue
                    maxQueue.offer(minQueue.poll());
                }
                // add the new value to the min queue
                minQueue.offer(num);
            }
        }
    }

    /**
     * Constant time complexity to compare the max value of the max heap
     * and min value of the min heap.
     *
     * @return
     */
    public double getStreamingMedian() {

        if (maxQueue.isEmpty()) {
            throw new RuntimeException();
        }
        // for the first value
        if (minQueue.isEmpty()) {
            return maxQueue.peek();
        }

        if (minQueue.size() == maxQueue.size()) {
            return (double)(minQueue.peek() + maxQueue.peek()) / 2d;
        } else {
            if (maxQueue.size() > minQueue.size()) {
                return maxQueue.peek();
            } else {
                return minQueue.peek();
            }
        }
    }
}
