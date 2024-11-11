package LeetCode;

public class SubsetSum {
    public static boolean isSubSetWithSum(int target, int n, int[] nums) {
        boolean[][] memo = new boolean[n + 1][target + 1];



        for (int j = 0; j < target + 1; j++)
            memo[0][j] = false;

        for (int i = 0; i < n + 1; i++)
            memo[i][0] = true;


        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < target + 1; j++) {
                if (nums[i-1] <= j) {
                    memo[i][j] = memo[i-1][j- nums[i-1]] || memo[i-1][j];
                } else {
                    memo[i][j] = memo[i-1][j];
                }
            }
        }
        return memo[n][target];
    }


    public static void main(String[] args) {
        System.out.println(isSubSetWithSum(6, 5, new int[]{2,3,4,5,1}));
    }
}
