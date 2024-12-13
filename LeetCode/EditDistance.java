package LeetCode;

public class EditDistance {
    public int minDistance(String word1, String word2) {
        int wLen1 = word1.length(), wLen2 = word2.length();
        if (wLen1 == 0) {
            return wLen2;
        }
        if (wLen2 == 0) {
            return wLen1;
        }
        int[][] dp = new int[wLen1+1][wLen2+1];

        for(int i = 1; i <= wLen1; i++) {
            dp[i][0] = i;
        }

        for(int i = 1; i <= wLen2; i++) {
            dp[0][i] = i;
        }

        for(int i = 1; i <= wLen1;i++) {
            for(int j = 1; j <= wLen2; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1+ Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]);
                }
            }
        }

        return dp[wLen1][wLen2];
    }
}
