package Docusign;

import java.util.Arrays;

public class LongestIncresingSubSequence {
    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[] {1,2,5,9,0}));
    }

    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];

        Arrays.fill(dp, 1);

        for(int i = 1; i < nums.length;i++) {
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    dp[i]=Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res =0;
        for(int i = 0; i < nums.length;i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
