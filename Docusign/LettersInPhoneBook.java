package Docusign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LettersInPhoneBook {
    static List<String> res = new ArrayList<>();
    static String[] dict = new String[] {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public static void main(String[] args) {

        StringBuilder com = new StringBuilder();
        String input = "23";
        if(input.length() == 0) {
            System.out.println(res);
        }
        backTrack(0, input, com);
        System.out.println(res);
    }

    private static void backTrack(int idx, String input, StringBuilder com) {
        if(com.length() == input.length()) {
            res.add(com.toString());
            return;
        }

        String possibleChars = dict[input.charAt(idx)-'0'-2];
        for(char c : possibleChars.toCharArray()) {
            com.append(c);
            backTrack(idx+1, input, com);
            com.deleteCharAt(com.length()-1);
        }
    }
}
