package LeetCode;


/*

You are running a classroom and suspect that some of your students are passing around the answer to a multiple-choice question disguised as a random note.

Your task is to write a function that, given a list of words and a note, finds and returns the word in the list that is scrambled inside the note, if any exists. If none exist, it returns the result "-" as a string. There will be at most one matching word. The letters don't need to be in order or next to each other. The letters cannot be reused.

Example:
words = ["baby", "referee", "cat", "dada", "dog", "bird", "ax", "baz"]
note1 = "ctay"
find(words, note1) => "cat"   (the letters do not have to be in order)
wIdx-abby, act, aada
noteIdx=acty


noteIdx=abbbbbbjkkly


note2 = "bcanihjsrrrferet"
find(words, note2) => "cat"   (the letters do not have to be together)

note3 = "tbaykkjlga"
find(words, note3) => "-"     (the letters cannot be reused)

note4 = "bbbblkkjbaby"
find(words, note4) => "baby"

note5 = "dad"
find(words, note5) => "-"

note6 = "breadmaking"
find(words, note6) => "bird"

note7 = "dadaa"
find(words, note7) => "dada"

All Test Cases:
find(words, note1) -> "cat"
find(words, note2) -> "cat"
find(words, note3) -> "-"
find(words, note4) -> "baby"
find(words, note5) -> "-"
find(words, note6) -> "bird"
find(words, note7) -> "dada"

Complexity analysis variables:

W = number of words in `words`
S = maximal length of each word or of the note

 */


import java.io.*;
        import java.util.*;

        import javax.print.attribute.ResolutionSyntax;

import javafx.util.Pair;

public class ScrambledWord {
    public static void main(String[] argv) {
        String[] words = {"baby", "referee", "cat", "dada", "dog", "bird", "ax", "baz"};
        String note1 = "ctay";
        String note2 = "bcanihjsrrrferet";
        String note3 = "tbaykkjlga";
        String note4 = "bbbblkkjbaby";
        String note5 = "dad";
        String note6 = "breadmaking";
        String note7 = "dadaa";


        System.out.println(find(words, note1));
        System.out.println(find(words, note2));
        System.out.println(find(words, note3));

        System.out.println(find(words, note4));
        System.out.println(find(words, note5));
        System.out.println(find(words, note6));
        System.out.println(find(words, note7));
    }
  /*
  words = ["baby", "referee", "cat", "dada", "dog", "bird", "ax", "baz"]
note1 = "ctay"
find(words, note1) => "cat"   (the letters do not have to be in order)
ws=abby, act, aada
n-acty


abbbbbbjkklyzz
  */

    public static String wordExist(String[] words, String note1) {
        String res = "-";
        HashMap<String, String> map = new HashMap<>();

        for(String word : words) {
            String originalWord = new String(word);
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            map.put(sorted, originalWord);
        }


        //String originalNote = new String(note1);
        char[] chars = note1.toCharArray();
        Arrays.sort(chars);
        String sortedNote = new String(chars);

        //System.out.println(" map " + map.toString() + " note: " + note1);

        for(var entry : map.entrySet()) {
            String wordSorted = entry.getKey();
            int noteIdx = 0, wordIdx = 0;

            while(noteIdx < sortedNote.length() && wordIdx < wordSorted.length()) {
                if(sortedNote.charAt(noteIdx) == wordSorted.charAt(wordIdx)) {
                    wordIdx++;
                    noteIdx++;
                } else {
                    noteIdx++;
                }
            }
            //System.out.println("wordSorted: " + wordSorted + " note: " + sortedNote + " nodeIdx: "+noteIdx + " wordIdx: " +wordIdx);
            if(wordIdx >= wordSorted.length() - 1) {

                    res = entry.getValue();
                    break;


            }
        }
        return res;


    }


    public static String find(String[] words, String note1) {
        String res = "-";
        HashMap<int[], String> map = new HashMap<>();

        for(String word : words) {
            int[] wordInt = new int[26];
            for(char c : word.toCharArray()) {
                wordInt[c - 'a']++;
            }
            map.put(wordInt, word);
        }

        int[] noteInt = new int[26];
        for(char c : note1.toCharArray()) {
            noteInt[c - 'a']++;
        }
        //System.out.println("Note: " + Arrays.toString(noteInt) + " = " + note1);
        for(var entry : map.entrySet()) {
            //System.out.println("word: " + Arrays.toString(entry.getKey()) + " = " + entry.getValue());
            int[] word = entry.getKey();
            int i = 0;
            for(; i < 26; i++) {
                if(word[i] > noteInt[i]) {
                    break;
                }
            }
            if(i == 26) {
                res = entry.getValue();
            }
        }

        return res;
    }
}

