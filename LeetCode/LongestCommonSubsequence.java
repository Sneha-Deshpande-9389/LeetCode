package LeetCode;

public class LongestCommonSubsequence {
    public int lcsubstring(String s1, String s2, int n, int m) {
        int[][] dp = new int[n+1][m+1];
/*
abcs   xaby

abcs  ""
 */

        for(int i = 0; i < n+1; i++) {
            dp[i][0] = 0;
        }


        for(int j = 0; j < m+1; j++) {
            dp[0][j] = 0;
        }

        int maxLength = 0;
        for(int i = 1; i < n+1; i++) {
            for(int j = 1; j < m+1; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    maxLength = Math.max(dp[i][j], maxLength);
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return maxLength;
    }


    public int lcsubsequence(String s1, String s2, int n, int m) {
        int[][] dp = new int[n+1][m+1];
/*
abcs   xaby

abcs  ""
 */

        for(int i = 0; i < n+1; i++) {
            dp[i][0] = 0;
        }


        for(int j = 0; j < m+1; j++) {
            dp[0][j] = 0;
        }


        for(int i = 1; i < n+1; i++) {
            for(int j = 1; j < m+1; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;

                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[n][m];
    }
    public String lcsubsequenceString(String s1, String s2, int n, int m) {
        int[][] dp = new int[n+1][m+1];
/*
abcs   xaby

abcs  ""
 */

        for(int i = 0; i < n+1; i++) {
            dp[i][0] = 0;
        }


        for(int j = 0; j < m+1; j++) {
            dp[0][j] = 0;
        }


        for(int i = 1; i < n+1; i++) {
            for(int j = 1; j < m+1; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;

                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        char[] lcs = new char[dp[n][m] + 1];

        int index = dp[n][m], i = n, j = m;
        //lcs[index] = '\u0000';
        while(i > 0 && j > 0) {
            if(s1.charAt(i-1) == s2.charAt(j-1)) {
                lcs[index -1] = s1.charAt(i-1);
                i--;
                j--;
                index--;
            } else if(dp[i-1][j] > dp[i][j-1]) {
                i--;
            } else {
                j--;
            }
        }

        return new String(lcs);
    }

    public static void main(String[] args) {
        System.out.println(new LongestCommonSubsequence().lcsubstring("absdr", "safebds", 5, 7));
        System.out.println(new LongestCommonSubsequence().lcsubsequence("absdr", "safebds", 5, 7));
        System.out.println(new LongestCommonSubsequence().lcsubsequenceString("absdr", "safebds", 5, 7));

        System.out.println(new LongestCommonSubsequence().lcsubstring("absdr", "aabsdrr", 5, 7));
        System.out.println(new LongestCommonSubsequence().lcsubsequence("absdr", "aabsdrr", 5, 7));
        System.out.println(new LongestCommonSubsequence().lcsubsequenceString("absdr", "aabsdrr", 5, 7));
    }
}
