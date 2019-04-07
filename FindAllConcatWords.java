import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class FindAllConcatWords {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {

        Set<String> wordSet = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            wordSet.add(words[i]);
        }
        List<String> concatWords = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int subStart = 0;
            for (int j = 1; j <= word.length(); j++) {
                String sub = word.substring(subStart, j);
                if (sub.length() < word.length()
                        && wordSet.contains(sub)) {
                    subStart = j;
                    if (j == word.length()) {
                        concatWords.add(word);
                    }
                }
            }
        }
        return concatWords;
    }

    public static void main(String[] args) {
        String[] words = {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        FindAllConcatWords solution = new FindAllConcatWords();
        List<String> concatWords = solution.findAllConcatenatedWordsInADict(words);
        for (String word : concatWords) {
            System.out.println(word);
        }
    }
}
