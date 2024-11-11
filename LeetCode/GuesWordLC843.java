package LeetCode;

import java.util.*;

public class GuesWordLC843 {
    HashMap<String, Boolean> visited;

    /*public void findSecretWord(String[] words, Master master) {
        List<String> wordsList = new ArrayList<>(Arrays.asList(words));
        Collections.shuffle(wordsList);

        for(String word : wordsList) {
            if(visited.get(word) == null) {
                int matchedCharCount = master.guess(word);
                if(matchedCharCount == 6) {
                    break;
                }
                if(matchedCharCount == 0) {
                    removeCompletely(word, wordsList);
                }
                removePartially(word, wordsList, matchedCharCount);
            }
        }
    } */

    private void removePartially(String source, List<String> wordsList, int matchedCharCountForSourceWord) {
        for(String word : wordsList) {
            if(word.equals(source)) {
                continue;
            }
            int matchedCharCount = getMatchingCharCount(source, word);
            if(matchedCharCount < matchedCharCountForSourceWord) {
                visited.put(word, true);
            }
        }
    }

    private void removeCompletely(String source, List<String> wordsList) {
        for(String word : wordsList) {
            if(word.equals(source)) {
                continue;
            }
            int matchedCharCount = getMatchingCharCount(source, word);
            if(matchedCharCount > 0) {
                visited.put(word, true);
            }
        }
    }

    private int getMatchingCharCount(String source, String word) {
        int count = 0;
        for(int i = 0; i < source.length(); i++) {
            if(source.charAt(i) == word.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}
