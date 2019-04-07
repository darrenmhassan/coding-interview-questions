package iv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dhssn on 10/23/16.
 */
public class WordLadderDemo {

    private static class WordLadder {

        private final int WORD_LEN;
        private Map<String, List> wordGraph = new HashMap<>();

        public WordLadder(final List<String> dictionary) {

            WORD_LEN = dictionary.get(0).length();
            for (String word : dictionary) {

                if (word.length() != WORD_LEN) {
                    throw new UnsupportedOperationException(
                            "All the words in the dictory should have the same length");
                }

                final List<String> adjWords = new ArrayList<>();
                for (final String wordNode : wordGraph.keySet()) {
                    if (differentByOne(word, wordNode)) {
                        List<String> wordsAdjToWordNode = wordGraph.get(wordNode);
                        wordsAdjToWordNode.add(lowerCaseDiff(wordNode, word));
                        wordGraph.put(wordNode, wordsAdjToWordNode);
                        adjWords.add(wordNode);
                    }
                }
                wordGraph.put(word, adjWords);
            }
        }

        public List<String> getWordLadder(final String start, final String end) {

            if (start.equals(end)) {
                return Arrays.asList(start);
            }
            if (!wordGraph.containsKey(start)) {
                throw new UnsupportedOperationException(
                        "The word " + start + " is not in the dictionary");
            }

            final List<String> route = new ArrayList<>();
            route.add(start);
            search(start, end, route);
            if (!route.get(route.size() - 1).equalsIgnoreCase(end)) {
                throw new UnsupportedOperationException(
                        "No route exists from start to end");
            }

            return route;
        }

        private void search(final String start, final String end, final List<String> route) {

            final String startUpperCase = start.toUpperCase();
            final List<String> adjWords = wordGraph.get(startUpperCase);
            for (final String adjWord : adjWords) {

                if (adjWord.equalsIgnoreCase(end)) {
                    route.add(adjWord);
                    return;
                }
                boolean contains = false;
                for (final String step : route) {
                    if (step.equalsIgnoreCase(adjWord)) {
                        contains = true;
                    }
                }
                if (!contains) {
                    route.add(adjWord);
                    search(adjWord, end, route);
                }
            }
        }

        private boolean differentByOne(final String w1, final String w2) {

            int diff = 0;
            for (int i = 0; i < WORD_LEN; i++) {
                if (w1.charAt(i) != w2.charAt(i)) {
                    diff++;
                }
            }
            return diff == 1;
        }

        private String lowerCaseDiff(final String w1, final String w2) {

            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < WORD_LEN; i++) {
                if (w1.charAt(i) != w2.charAt(i)) {
                    sb.append(Character.toLowerCase(w2.charAt(i)));
                } else {
                    sb.append(w2.charAt(i));
                }
            }
            return sb.toString();
        }
    }

    public static void main(final String args[]) {

        final List<String> dictionary = Arrays.asList(
                "MALL", "TALL", "FALL", "BALL", "BELL", "BELT", "BENT");
        final WordLadder wordLadder = new WordLadder(dictionary);
        final List<String> route = wordLadder.getWordLadder("MALL", "BENT");
        for (final String w : route) {
            System.out.println(w);
        }
    }
}
