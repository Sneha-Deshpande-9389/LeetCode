package Confluent;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/*
Find the first non-repeating character in a string.
 */
public class NonRepeatingCharInString {
    public static void main(String[] args) {
        String str = "abcdesaabc";

        LinkedHashMap<Character, Integer> orderedSet = new LinkedHashMap<>();
        for(char c : str.toCharArray()) {
            if(orderedSet.containsKey(c)) {
                orderedSet.put(c, orderedSet.get(c) + 1);
            } else {
                orderedSet.put(c, 1);
            }
        }
        for(var entry : orderedSet.entrySet()) {
            if(entry.getValue() == 1) {
                System.out.println(entry.getKey());
                break;
            }
        }

    }
}
