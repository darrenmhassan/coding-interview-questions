package iv;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by dhssn on 10/23/16.
 */
public class HuffmanEncodingDemo {

    public static void main(final String args[]) {

        final PriorityQueue<HuffmanEncoding.Pair> charFreqHeap =
                new PriorityQueue<>(
                        (Comparator<HuffmanEncoding.Pair>) (o1, o2)
                                -> o1.getFreq() - o2.getFreq());
        charFreqHeap.add(new HuffmanEncoding.Pair("a", 15));
        charFreqHeap.add(new HuffmanEncoding.Pair("b", 7));
        charFreqHeap.add(new HuffmanEncoding.Pair("c", 6));
        charFreqHeap.add(new HuffmanEncoding.Pair("d", 6));
        charFreqHeap.add(new HuffmanEncoding.Pair("e", 5));

        final HuffmanEncoding huffmanEncoding =
                new HuffmanEncoding();
        final Map<Character, String> encodings =
                huffmanEncoding.getHuffmanEncoding(charFreqHeap);

        for (final Map.Entry<Character, String> encoding : encodings.entrySet()) {
            System.out.println(encoding.getKey() + " : " + encoding.getValue());
        }
    }

    private static class HuffmanEncoding {

        private static class Pair {

            private String characters;
            private int frequency;

            public Pair(final String characters, final int frequency) {
                this.characters = characters;
                this.frequency = frequency;
            }

            public String getChars() {
                return characters;
            }

            public int getFreq() {
                return frequency;
            }
        }

//        private static class HuffmanTable {
//
//            private String characters;
//
//            private HuffmanTable leftChild; // 0
//            private HuffmanTable rightChild; // 1
//
//            private void setCharacters(final String characters) {
//                this.characters = characters;
//            }
//            private String getCharacters() {
//                return characters;
//            }
//
//            private void setLeftChild(final HuffmanTable leftChild) {
//                this.leftChild = leftChild;
//            }
//
//            private void setRightChild(final HuffmanTable rightChild) {
//                this.rightChild = rightChild;
//            }
//        }

        public Map<Character, String> getHuffmanEncoding(
                final PriorityQueue<Pair> chars2FreqHeap) {

            final Map<Character, String> huffmanTable = new HashMap<>();
            while (chars2FreqHeap.size() > 1) {

                final Pair pair1 = chars2FreqHeap.poll();
                final Pair pair2 = chars2FreqHeap.poll();

                final String chars = pair1.getChars() + pair2.getChars();
                final int len = chars.length();
                for (int i = 0; i < len; i++) {

                    final String suffix = i < len / 2 ? "0" : "1";
                    final char c = chars.charAt(i);
                    if (huffmanTable.containsKey(c)) {
                        String encoding = huffmanTable.get(c);
                        encoding += suffix;
                        huffmanTable.put(c, encoding);
                    } else {
                        huffmanTable.put(c, suffix);
                    }
                }
                final Pair newPair = new Pair(chars, pair1.getFreq() + pair2.getFreq());
                chars2FreqHeap.add(newPair);
            }
            return huffmanTable;
        }
    }
}
