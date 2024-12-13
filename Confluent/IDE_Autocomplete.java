            package Confluent;

            import java.util.*;

            /*
            Lets say the classes available are :


            Container
            Panel
            AutoPanel
            RidePrinter
            ResumePanel
            RegularContainer
            The class names are camel case
            The upper case letters in the pattern match the different segments of the class names
            AutoComplete("R") - > ["ResumePanel", "RegularContainer", "RidePrinter"]
            AutoComplete("Re") - > ["ResumePanel", "RegularContainer"]
            AutoComplete("RP") - > ["ResumePanel", "RidePrinter"]
            AutoComplete("RPr") - > ["RidePrinter"]
            https://leetcode.com/discuss/interview-question/algorithms/132310/ide-autocomplete-feature
             */
            public class IDE_Autocomplete {
                public static void main(String[] args) {
                    Trie trie = new Trie();
                    String[] words = new String[] {"Container", "Panel", "AutoPanel", "RidePrinter", "ResumePanel",
                            "RegularContainer"};
                    Arrays.stream(words).forEach(w -> trie.insert(w));
                    System.out.println(trie.getAllMatchingWords("R").toString());
                    System.out.println(trie.getAllMatchingWords("Re").toString());
                    System.out.println(trie.getAllMatchingWords("RP").toString());
                    System.out.println(trie.getAllMatchingWords("RPr").toString());
                    //System.out.println(splitStringOnCamelCase("RidePrinter"));
                }


            }
            class Trie {
                private static List<String> splitStringOnCamelCase(String s) {
                    List<String> splits = new ArrayList<>();
                    StringBuilder sb = new StringBuilder();
                    for(char c : s.toCharArray()) {
                        if(c-'Z' <= 0 && sb.length() != 0) {
                            splits.add(sb.toString());
                            sb = new StringBuilder();
                        }
                        sb.append(c);
                    }
                    splits.add(sb.toString());
                    return splits;
                }
                TrieNode root = null;

                Trie () {
                    if(root == null) {
                        synchronized (this) {
                            if(root == null) {
                                root = new TrieNode();
                            }
                        }
                    }
                }

                public void insert(String word) {
                    TrieNode node = root;
                    for(char c : word.toCharArray()) {
                        if(!node.links.containsKey(c)) {
                            node.links.put(c, new TrieNode());
                        }
                        node = node.links.get(c);
                    }
                    node.isEnd = true;
                }

                public TrieNode searchPrefix(String word) {
                    TrieNode node = root;
                    for(char c : word.toCharArray()) {
                        if(node.links.containsKey(c)) {
                            node = node.links.get(c);
                        } else {
                            return null;
                        }
                    }
                    return node;
                }

                public boolean search(String word) {
                    TrieNode node = searchPrefix(word);
                    return node != null && node.isEnd;
                }

                public boolean startsWith(String word) {
                    TrieNode node = searchPrefix(word);
                    return node != null;
                }
                List<String> resultBuffer = new ArrayList<>();
                void dfsWithPrefix(TrieNode curr, String word, String capital) {
                    if(curr == null) {
                        return;
                    }

                    if (curr.isEnd) {
                        //if(word.contains(new StringBuffer(capital.toString()))) {
                        int[] wordArray = new int[26];
                        for(Character c : word.toCharArray()) {
                            if(Character.isUpperCase(c))
                                wordArray[c - 'A']++;
                        }
                        int[] capitalArray = new int[26];
                        for(Character c : capital.toCharArray()) {
                            if(Character.isUpperCase(c))
                                capitalArray[c - 'A']++;
                        }
                        boolean isMatching = true;
                        for(int i = 0; i < 26; i++) {
                            if(capitalArray[i] > wordArray[i]) {
                                isMatching = false;
                                break;
                            }
                        }
                        if(isMatching)
                            resultBuffer.add(word);
                       // }

                    }

                    // Run DFS on all possible paths.

                        for(var entry : curr.links.entrySet()) {
                            dfsWithPrefix(entry.getValue(), word + entry.getKey(), capital);
                        }

                }

                public List<String> getAllMatchingWords(String word) {
                    List<String> capitalLetteredWords = splitStringOnCamelCase(word);
                    StringBuilder caps = new StringBuilder();
                    for (String capitalLetteredWord : capitalLetteredWords) {
                        caps.append(capitalLetteredWord.charAt(0));
                    }
                    dfsWithPrefix(searchPrefix(capitalLetteredWords.get(0)), capitalLetteredWords.get(0), caps.toString());
                    return resultBuffer;
                }
            }
            class TrieNode {
                public SortedMap<Character, TrieNode> links;

                public boolean isEnd;

                public TrieNode() {
                    links = new TreeMap<>();
                }

            }