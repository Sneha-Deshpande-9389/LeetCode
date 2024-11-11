package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestPalindromeSubString {
    public static String longestPalindrome(String s1) {
        StringBuilder input1 = new StringBuilder();
        input1.append(s1);
        input1.reverse();
        String s2 = input1.toString();
        int n = s1.length();
        int[][] dp = new int[n+1][n+1];

        for(int i=0; i<=n; i++) {
            dp[i][0] = 0;
        }
        for(int i=0; i<=n; i++) {
            dp[0][i] = 0;
        }
        int maxValue = 0, index = 0;

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                    if(maxValue < dp[i][j]) {
                        maxValue = dp[i][j];
                        index = i;
                    }
                } else {
                    dp[i][j] = 0;

                }
            }
        }
        Arrays.stream(dp).forEach(arr -> System.out.println(Arrays.toString(arr)));
        System.out.println(s1.substring(index-maxValue, maxValue));
        return s1.substring(index-maxValue, index);
    }

    public static void main(String[] args) {
        longestPalindrome("aacabdkacaa");
    }
}
