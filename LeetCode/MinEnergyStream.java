package LeetCode;

import java.util.Arrays;

public class MinEnergyStream {
    /*int[][] arr = new int[][] {
            {2,3,1},
            {1,2,3},
            {3,1,4}
    };*/

    int[][] arr = new int[][] {
            {2,3,1,2},
            {1,2,3,1},
            {3,1,4,6},
            {3,1,4,6}
    };
    int[][] memo = new int[4][4];
    int n=4;

    public static void main(String[] args) {
        MinEnergyStream obj = new MinEnergyStream();
        int[][] arr = new int[][] {
                {2,3,1,2},
                {1,2,3,1},
                {3,1,4,6},
                {3,1,4,6}
        };
        System.out.println(obj.minFallingPathSum(arr));

    }
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];

        for (int j = 0; j < m; j++) {
            dp[0][j] = matrix[0][j];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int ld = Integer.MAX_VALUE, rd = Integer.MAX_VALUE;
                int up = matrix[i][j] + dp[i - 1][j];

                if (j - 1 >= 0) {
                    ld = matrix[i][j] + dp[i - 1][j - 1];
                }
                if (j + 1 < m) {
                    rd = matrix[i][j] + dp[i - 1][j + 1];
                }

                dp[i][j] = Math.min(up, Math.min(ld, rd));
            }
        }

        int mini = dp[n - 1][0];
        for (int j = 1; j < m; j++) {
            mini = Math.min(mini, dp[n - 1][j]);
        }
        return mini;
    }

    public int minStream() {
        for(int  i = 0 ; i < memo.length; i++) {
            Arrays.fill(memo[0], -1);
        }
        for(int j = 0; j < n ; j++) {
            calculate(0, j);
        }
        int min = Integer.MAX_VALUE;
        for(int j = 0; j < n ; j++) {
            min = Math.min(min, memo[0][j]);
        }
        return min;
    }

    public int calculate(int i, int j) {
        if(i >= n || j >= n || j < 0 || i < 0) {
            return Integer.MAX_VALUE;
        }
        if(i == n-1) {
            return arr[i][j];
        }
        if(memo[i][j] != -1) {
            return memo[i][j];
        }
        return memo[i][j] = arr[i][j] +
                            Math.min(calculate(i+1, j-1),
                                    Math.min(calculate(i+1, j), calculate(i+1, j+1)));
    }
}
