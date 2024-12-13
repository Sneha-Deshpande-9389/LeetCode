package Docusign;

import java.util.ArrayList;
import java.util.List;

public class StringCombinations {
    /*
    TC-O(n!/(k-1)!*(n-k)!)
    SC-O(k)
     */
    static List<String> res = new ArrayList<>();

    public static void main(String[] args) {
        String str = "abcd";
        int k = 2;
        backTrack(0, new StringBuilder(), str, k);
        System.out.println(res);
    }

    private static void backTrack(int idx, StringBuilder com, String str, int k) {
        if(com.length() == k) {
            res.add(com.toString());
            return;
        }
        for(int i = idx; i < str.length(); i++) {
            com.append(str.charAt(i));
            backTrack(i + 1, com, str, k);
            com.deleteCharAt(com.length()-1);
        }
    }
}
