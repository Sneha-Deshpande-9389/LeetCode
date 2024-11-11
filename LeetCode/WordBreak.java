package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordBreak {
    public boolean isWordCombination(String s, List<String> dictionaryWords) {
        /*
        root
        l       c
        e        o
        e       d
        t-word  e-word

        ["cats","dog","sand","and","cat"]
        catsanddogs
                        root
        c           d           s           a
        a           o           a           n
        t-word      g-word      n           d-word
        s-word                  d-word

                catsandog-F
       cat - F             cats-F
      sand-T og-x-F     and-T    og-F



         */
        Trie root = new Trie();
        for(String word : dictionaryWords) {
            Trie curr = root;
            for(char c: word.toCharArray()) {
                if(!curr.children.containsKey(c)) {
                    curr.children.put(c, new Trie());
                }
                curr = curr.children.get(c);
            }
            curr.isWord = true;
        }



        return false;
    }
}

class Trie {
    boolean isWord = false;
    Map<Character, Trie> children;

    Trie() {
        children = new HashMap<>();
    }
}
