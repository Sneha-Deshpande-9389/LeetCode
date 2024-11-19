package LeetCode;
/*
An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
using all the original letters exactly once.
 */

import java.util.*;
import java.util.stream.Collectors;

public class GroupAnagramsLC49 {
    public static void main(String[] args) {
        System.out.println(new GroupAnagramsLC49().groupAnagrams(new String[] {"eat","tea","tan","ate","nat","bat"}).toString());
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> wordToAnagramsMap = new HashMap<>();
        for(String str : strs) {
            char[] sorted = str.toCharArray();
            Arrays.sort(sorted);
            String sortedString =new String(sorted);
            wordToAnagramsMap.putIfAbsent(sortedString, new ArrayList<>());
            wordToAnagramsMap.get(sortedString).add(str);
        }
        List<List<String>> res = new ArrayList<>();
        //for(Map.Entry<String, List<String>> entry : wordToAnagramsMap.entrySet()) {

         //   res.add(entry.getValue());
       // }

        wordToAnagramsMap.entrySet().forEach(entry -> res.add(entry.getValue()));
        return res;
    }
}
