package Confluent;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SearchWordInAllDocs {
    public static void main(String[] args) {
        Pair<String, List<String>> doc1 = new Pair("doc1", Arrays.asList("hi", "hi1", "hi2"));
        Pair<String, List<String>> doc2 = new Pair("doc2", Arrays.asList("he", "he1", "he2"));
        Pair<String, List<String>> doc3 = new Pair("doc3", Arrays.asList("ho", "ho1", "ho2"));
        Pair<String, List<String>> doc4 = new Pair("doc4", Arrays.asList("hp", "hp1", "hp3"));

        List<Pair<String, List<String>>> docs = Arrays.asList(doc1, doc2, doc3, doc4);
        TrieDoc trie = new TrieDoc();
        for(var doc : docs) {
            for(var word : doc.getValue()) {
                trie.insert(word, doc.getKey());
            }
        }
        System.out.println(trie.search("hi"));
    }



}

class TrieDoc {
    TrieDocNode root;

    TrieDoc () {
        root = new TrieDocNode();
    }

    public void insert(String word, String docId) {
        TrieDocNode node= root;

        for(char c : word.toCharArray()) {
            if(!node.links.containsKey(c)) {
                node.links.put(c, new TrieDocNode());
            }
            node = node.links.get(c);
        }
        node.isEnd = true;
        node.docId = docId;
    }

    public String search(String word) {
        TrieDocNode node= root;

        for(char c : word.toCharArray()) {
            if(node.links.containsKey(c)) {
                node = node.links.get(c);
            } else {
                return null;
            }
        }
        if(node != null && node.isEnd) {
            return node.docId;
        }
        return null;
    }
}

class TrieDocNode {
    HashMap<Character, TrieDocNode> links;
    boolean isEnd;
    String docId;

    TrieDocNode() {
        links = new HashMap<>();

    }
}
