import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Multimap {

    private static class MapNode {

        private String customerId;
        private List<Long> timestamps;
        private MapNode next = null;

        public MapNode(final String customerId, final Long timestamp) {

            this.timestamps = new ArrayList<>();
            this.timestamps.add(timestamp);
            this.customerId = customerId;
        }
    }

    private final MapNode[] buckets;
    private final int bucketSize;

    public Multimap(final int bucketSize) {

        if (bucketSize < 1) {
            throw new IllegalArgumentException();
        }

        this.buckets = new MapNode[bucketSize];
        this.bucketSize = bucketSize;
    }

    public void put(final String customerId, final Long timestamp) {

        if (customerId == null) {
            throw new IllegalArgumentException();
        }

        int bucket = customerId.hashCode() % bucketSize;
        MapNode n = buckets[bucket];
        while (n != null && !n.customerId.equals(customerId) && n.next != null) {
            n = n.next;
        }
        if (n == null) {
            buckets[bucket] = new MapNode(customerId, timestamp);
        } else if (n.customerId.equals(customerId)) {
            n.timestamps.add(timestamp);
        } else {
            n.next = new MapNode(customerId, timestamp);
        }
    }

    public List<Long> get(final String customerId) {

        if (customerId == null) {
            throw new IllegalArgumentException();
        }

        int bucket = customerId.hashCode() % bucketSize;
        MapNode n = buckets[bucket];
        while (n != null && !n.customerId.equals(customerId) && n.next != null) {
            n = n.next;
        }
        if (n.customerId.equals(customerId)) {
            return n.timestamps;
        } else {
            return new ArrayList<>();
        }
    }

    public void remove(final String customerId) {

        if (customerId == null) {
            throw new IllegalArgumentException();
        }

        int bucket = customerId.hashCode() % bucketSize;
        MapNode n = buckets[bucket];
        MapNode prev = null;
        while (n != null && n.next != null) {
            if (n.customerId.equals(customerId)) {
                if (prev != null) {
                    prev.next = n.next;
                } else {
                    buckets[bucket] = n.next;
                }
                break;
            }
            prev = n;
            n = n.next;
        }
    }
}
