package LeetCode;


import java.util.ArrayList;
import java.util.List;

/*
TC-o(n * 2^n)
SC-o(n)
*/
public class PalindromePartitionLC131 {
    public static void main(String[] args) {
        System.out.println(new PalindromePartitionLC131().getPalindromicSubStrings("aab").toString());
    }
    public List<List<String>> getPalindromicSubStrings(String input) {
        List<List<String>> res = new ArrayList<>();
        backTrack(input, new ArrayList<>(), res, 0);
        return res;
    }

    private boolean isPalindrome(String input, int i, int j) {
        while (i < j) {
            if(input.charAt(i++) != input.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

//aaab = (aaa, b) (a, a, a, b)
    private void backTrack(String input, List<String> partition, List<List<String>> res, int start) {
        if(start >= input.length()) {
            res.add(new ArrayList<>(partition));
            return;
        }

        for(int end = start; end < input.length(); end++) {
            if(isPalindrome(input, start, end)) {
                partition.add(input.substring(start, end + 1));
                backTrack(input, partition, res, end + 1);
                partition.removeLast();
            }
        }
    }
}
