import java.lang.reflect.Array;

/**
 *
 */
public class HashMap<K, V> {

    private class MapNode<K,V> {
        private K key;
        private V value;
        private MapNode next = null;

        public MapNode(final K key, final V value) {
            this.key = key;
            this.value = value;
        }

        private MapNode() { }

        public String toString() {
            return "[" + key + " -> " + value + "]";
        }
    }

    private final MapNode[] hashKeys;
    private final int size;

    public <K, V> HashMap(final int size) {
        if (size < 1) {
            throw new IllegalArgumentException("size must be greater than or equal to 1");
        }

        // create a generic array
        hashKeys = (MapNode[])Array.newInstance(new MapNode<K,V>().getClass(), size);
        this.size = size;
    }

    public void put(final K key, final V value) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        final int i = key.hashCode() % size;
        if (hashKeys[i] == null) {
            final MapNode n = new MapNode(key, value);
            hashKeys[i] = n;
        } else {
            MapNode n = hashKeys[i];
            while (!n.key.equals(key)) {
                if (n.next == null) {
                    break;
                }
                n = n.next;
            }
            if (n.key.equals(key)) {
                n.value = value;
                return;
            }
            n.next = new MapNode(key, value);
        }
    }

    public V get(final K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        final int i = key.hashCode() % size;
        if (hashKeys[i] != null) {
            MapNode n = hashKeys[i];
            while (!n.key.equals(key)) {
                if (n.next == null) {
                    break;
                }
                n = n.next;
            }
            if (n.key.equals(key)) {
                return (V)n.value;
            }
        }
        return null;
    }

    public V remove(final K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        final int i = key.hashCode() % size;
        if (hashKeys[i] != null) {
            if (hashKeys[i].next != null) {
                final V value = (V)hashKeys[i].value;
                if (hashKeys[i].key.equals(key)) {
                    hashKeys[i] = hashKeys[i].next;
                } else {
                    hashKeys[i] = null;
                }
                return value;
            }
            MapNode n = hashKeys[i];
            MapNode p = n;
            while (!n.key.equals(key)) {
                if (n.next == null) {
                    break;
                }
                p = n;
                n = n.next;
            }
            if (n.key.equals(key)) {
                p.next = n.next;
                return (V)n.value;
            }
        }
        return null;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        MapNode n = null;
        for (int i = 0; i < size; i++) {
            n = hashKeys[i];
            sb.append(n);
            while (n != null && n.next != null) {
                n = n.next;
                sb.append(", ");
                sb.append(n);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(final String[] argc) {

        final HashMap<String, Integer> m = new HashMap<>(5);
        m.put("one", 1);
        m.put("two", 2);
        m.put("three", 3);
        m.put("four", 4);
        m.put("five", 5);
        m.put("six", 6);
        m.put("seven", 7);
        System.out.print(m.toString());

        System.out.println(m.get("four"));
        System.out.println(m.get("tow"));
        System.out.println(m.get("one"));
        m.remove("two");
        System.out.println(m.get("four"));
        System.out.print(m.toString());
    }
}
