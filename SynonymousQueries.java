package iv;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Blog post: https://medium.com/@alexgolec/google-interview-problems-synonymous-queries-36425145387c
 *
 * Example input
 * SYNONYMS = [
 *   ('rate', 'ratings'),
 *   ('approval', 'popularity'),
 * ]
 *
 * QUERIES = [
 *   ('obama approval rate', 'obama popularity ratings'),
 *   ('obama approval rates', 'obama popularity ratings'),
 *   ('obama approval rate', 'popularity ratings obama')
 * ]
 */
public class SynonymousQueries {

    public static void main(String args[]) {
        final List<List<String>> synonyms = new ArrayList<>();
        synonyms.add(Arrays.asList("rate", "ratings"));
        synonyms.add(Arrays.asList("approval", "popularity"));
        final List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("obama approval rate", "obama popularity ratings"));
        queries.add(Arrays.asList("obama approval rates", "obama popularity ratings"));
        queries.add(Arrays.asList("obama approval rate", "popularity ratings obama"));

        final SynonymousQueries synonymousQueries = new SynonymousQueries();
        final List<Boolean> results = synonymousQueries.queriesAreSynonymous(synonyms, queries);
        for (final Boolean result : results) {
            System.out.println(result);
        }
    }

    private Map<String, List<String>> wordToSynonyms;

    private void buildSynonymsMap(final List<List<String>> synonyms) {

        wordToSynonyms = new HashMap<>();
        for (final List<String> synonymPair : synonyms) {
            if (wordToSynonyms.containsKey(synonymPair.get(0))) {
                wordToSynonyms.get(synonymPair.get(0)).add(synonymPair.get(1));
            } else {
                final List<String> wordSynonyms = new ArrayList<>();
                wordSynonyms.add(synonymPair.get(1));
                wordToSynonyms.put(synonymPair.get(0), wordSynonyms);
            }
        }
    }

    private boolean wordsAreSynonymous(final String w1,
                                       final String w2) {
        return wordToSynonyms.containsKey(w1) && wordToSynonyms.get(w1).contains(w2)
                || wordToSynonyms.containsKey(w2) && wordToSynonyms.get(w2).contains(w1);
    }

    private boolean areSynonymous(final String q1,
                                  final String q2) {

        final String[] q1Words = q1.split(" ");
        final String[] q2Words = q2.split(" ");
        if (q1Words.length != q2Words.length) return false;
        for (int i = 0; i < q1Words.length; i++) {
            if (!q1Words[i].equals(q2Words[i]) && !wordsAreSynonymous(q1Words[i], q2Words[i])) return false;
        }
        return true;
    }

    public List<Boolean> queriesAreSynonymous(final List<List<String>> synonyms,
                                              final List<List<String>> queries) {

        buildSynonymsMap(synonyms);
        final List<Boolean> results = new ArrayList<>();
        for (final List<String> queryPair : queries) {
            results.add(areSynonymous(queryPair.get(0), queryPair.get(1)));
        }
        return results;
    }
}
