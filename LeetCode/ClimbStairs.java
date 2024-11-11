package LeetCode;

import java.util.Arrays;

public class ClimbStairs {
    public int dp(int n, int[] memo) {
        if(n == 0) {
            return 0;
        }
        if(n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if(memo[n] != -1) {
            return memo[n];
        }
        return memo[n] = dp(n-1, memo)+dp(n-2, memo);
    }

    public static void main(String[] args) {
        int n = 5;
        int[] memo = new int[n+1];
        Arrays.fill(memo, -1);
        System.out.println(new ClimbStairs().dp(n, memo));
    }
}
