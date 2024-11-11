package LeetCode;

import java.util.Arrays;

public class Jumps {

    public static void main(String[] args) {
        int  n = 9;
        int[] arr = new int[]{3, 5 ,4 ,7, 2, 12, 8, 10, 1};
        System.out.println(findMaxAcore(arr));
        int[][] dp = new int[n-1][n+1];
        for(int[] row : dp) {
            Arrays.fill(row, -1);
        }
        //System.out.println(dp(arr, 1, 1, dp));
    }

    public static int findMaxAcore(int[] nums) {
        int n = nums.length;
        int prev = n-1;
        int res = prev * nums[prev];

        for(int i = n-2; i> -1; i--) {
            System.out.println(res + " " + i + " " + nums[i]);
            if(nums[i] > nums[prev]) {
                res += i * (nums[i] - nums[prev]);
                prev = i;
            }
        }
        return res;
    }

    public static int dp(int[] arr, int dist,int n, int[][] dp) {
        if(n > arr.length-1) {
            return 0;
        }
        if(n == arr.length-1) {
            return dist * arr[n];
        }
        if(dp[dist][n] != -1) {
            return dp[dist][n];
        }
        return dp[dist][n] = Math.max(arr[n] + dp(arr, dist, n+1, dp), dp(arr, dist+1, n+1, dp));
    }


}

//12AM
/*
      3 5 4 7 2 12 8 10 1

ex: 3 to 12 (512) + 12 to 10 (210) + 10 to 1 (1*1) = 81

3 5 4
    (5)5                        x
  (4)4 x                    (8)4  x
  x (end of array)          x(end of array)

3,1,8
1*1+1*8 = 9
2*8 = 16

recursion-
  if(n > length)
    return 0;
  if(n == length)
    return dist*arr[n];

  return Math.max(arr[n] + dp(arr, dist, n+1), dp(arr, dist+1, n+1));
                           dp(arr, 1, 1)=9
                           5 + dp(arr, 1, 1) = 9,       dp(arr, 1, 1)= 8
                              dp(arr, 1,2)=4                dp(arr, 2,2)=8

    3,1,8
                                                    dp(arr, 1,1)=16
                          1 + dp(arr, 1, 2)=9                         dp(arr, 2, 2)=16
                                    1*8                                        2*8
memoise -
int[][] dp = new int[length-1][n+1];
  if(n == 0)
    return 0;
  if(dp[dist][n]) != -1)
    return  dp[dist][n];
  return dp[dist][n] = Math.max(arr[n] + dp(arr, dist, n+1), dp(arr, dist+1, n+1));



top-down
int[][] dp = new int[length-1][n+1];




 */
