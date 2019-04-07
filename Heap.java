import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Heap {

    private int heapSize = 0;
    private int maxHeapSize = 10;
    private Integer[] heapStorage;

    public Heap() {
        heapStorage = new Integer[maxHeapSize];
    }

    public void add(int value) {
        heapStorage[heapSize++] = new Integer(value);
        if (heapSize >= maxHeapSize) {
            maxHeapSize *= 2;
            Integer[] tmpHeapStorage = new Integer[maxHeapSize];
            for (int i = 0; i < heapSize; i++) {
                tmpHeapStorage[i] = heapStorage[i];
            }
            heapStorage = tmpHeapStorage;
        }
        int parentIdx = parentIdx(heapSize);
        for (int i = parentIdx; i >= 0; i--) {
            maxHeapify(heapStorage, heapSize, i);
        }
    }

    public Integer[] sort() {
        Integer[] sortedHeap = new Integer[heapSize];
        for (int i = 0; i < heapSize; i++) {
            sortedHeap[i] = new Integer(heapStorage[i]);
        }
        for (int i = 1; i < heapSize; i++) {
            Integer tmp = sortedHeap[0];
            sortedHeap[0] = sortedHeap[heapSize - i];
            sortedHeap[heapSize - i] = tmp;
            maxHeapify(sortedHeap, heapSize - i - 1, 0);
        }
        return sortedHeap;
    }

    private void maxHeapify(Integer[] heap, int heapSize, int i) {
        int leftChildIdx = leftChildIdx(i);
        int rightChildIdx = rightChildIdx(i);
        int largest = i;
        if (leftChildIdx < heapSize
                && heap[leftChildIdx] > heap[i]) {
            largest = leftChildIdx;
        } else {
            largest = i;
        }
        if (rightChildIdx < heapSize
                && heap[rightChildIdx] > heap[largest]) {
            largest = rightChildIdx;
        }
        if (largest != i) {
            Integer tmp = new Integer(heap[largest]);
            heap[largest] = heap[i];
            heap[i] = tmp;
            maxHeapify(heap, heapSize, largest);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(heapStorage).forEach(i -> sb.append(i).append(", "));
        return sb.toString();
    }

    private Integer parent(int i) {
        int parentIdx = parentIdx(i);
        if (parentIdx >= 0) {
            return heapStorage[parentIdx];
        } else {
            return null;
        }
    }

    // pi = (i - 1) / 2
    private int parentIdx(int i) {
        return (i - 1) / 2;
    }

    private Integer leftChild(int i) {
        int leftChildIdx = leftChildIdx(i);
        if (leftChildIdx < maxHeapSize) {
            return heapStorage[leftChildIdx];
        } else {
            return null;
        }
    }

    // li = (i * 2) + 1
    private int leftChildIdx(int i) {
        return (i * 2) + 1;
    }

    private Integer rightChild(int i) {
        int rightChildIdx = rightChildIdx(i);
        if (rightChildIdx < maxHeapSize) {
            return heapStorage[rightChildIdx];
        } else {
            return null;
        }
    }

    // ri = (i * 2) + 2
    private int rightChildIdx(int i) {
        return (i * 2) + 2;
    }

    public static void main(String[] args) {
        Heap h = new Heap();
        for (int i = 0; i < 11; i++) {
            h.add(ThreadLocalRandom.current().nextInt(20));
            System.out.println(String.format("heap @ %s: %s", i, h.toString()));
        }
        Integer[] sortedHeap = h.sort();
        StringBuilder sb = new StringBuilder();
        Arrays.stream(sortedHeap).forEach(i -> sb.append(i).append(", "));
        System.out.println(String.format("sorted heap: %s", sb.toString()));
        System.out.println(String.format("heap @ final: %s", h.toString()));
    }
}
