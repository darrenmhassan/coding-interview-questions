package recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a list of words (without duplicates),
 * please write a program that returns all concatenated words in the given list of words.
 *
 * A concatenated word is defined as a string that is comprised entirely of at least two
 * shorter words in the given array.
 *
 * Example:
 * Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 *
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 *
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 *  "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 *
 * Source: https://leetcode.com/problems/concatenated-words/
 */
class FindAllConcatWords {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {

        Set<String> wordSet = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            wordSet.add(words[i]);
        }
        Set<String> concatWords = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            wordSearch(words[i], 0, 1, wordSet, concatWords);
        }
        return new ArrayList<>(concatWords);
    }

    private void wordSearch(String word, int startIdx, int endIdx,
                            Set<String> wordSet,
                            Set<String> concatWords) {
        while (endIdx <= word.length() && !concatWords.contains(word)) {
            String subStr = word.substring(startIdx, endIdx);
            if (subStr.length() < word.length() && wordSet.contains(subStr)) {
                if (endIdx == word.length()) {
                    concatWords.add(word);
                    return;
                }
                wordSearch(word, startIdx, endIdx + 1, wordSet, concatWords);
                startIdx = endIdx;
            }
            endIdx++;
        }
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
