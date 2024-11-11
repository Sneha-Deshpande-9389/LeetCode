package LeetCode;


public class CoinChange {
    int[] memo = new int[12];

/*
322. Coin Change - Return the fewest number of coins to make sum
complexity - o(n*sum)
 */
public int coinChange(int[] coins, int sum) {

    int n = coins.length;
    int[][] dp = new int[n+1][sum+1];

    //base case

    for(int i = 0; i < n+1; i++) {
        for(int j = 0; j < sum+1; j++) {
            if(j == 0) {
                dp[i][j] = 0; // if sum = 0, the fewer no. of coins to make 0 is 0.
            }
            if(i == 0) {
                dp[i][j] = Integer.MAX_VALUE - 1; // if no coins, the fewer no. of coins to make any sum is infinite
            }
            if(i == 1) { // if any sum is divisible by first coin, that will be the smallest size of coins to reach sum
                if(j % coins[i-1] == 0) {
                    dp[i][j] = j / coins[i-1];
                } else {
                    dp[i][j] = Integer.MAX_VALUE - 1; // else infinite
                }
            }
        }
    }


    for(int i = 1; i < n+1; i++) {
        for(int j = 1; j < sum+1; j++) {
            if(coins[i-1] <= j) {
                dp[i][j] = Math.min(1 + dp[i][j - coins[i-1]],
                        dp[i-1][j]);
            } else {
                dp[i][j] = dp[i-1][j];
            }
        }
    }
    return dp[n][sum] == Integer.MAX_VALUE - 1 ? -1 : dp[n][sum];
}

    /*
 518. Coin Change II - return max number of ways to reach sum
 complexity - o(n*sum)
  */
    public int coinChange11(int coins[], int n, int sum) {
        int[][] dp = new int[n+1][sum+1];

        //base case
//if coins[] is empty
        for(int i = 0; i < n+1; i++) {
            dp[i][0] = 1;
        }
        // if sum = 0
        for(int i = 0; i < n+1; i++) {
            dp[0][i] = 0;
        }

        for(int i = 1; i < n+1; i++) {
            for(int j = 1; j < sum+1; j++) {
                if(coins[i-1] <= j) {
                    dp[i][j] = dp[i][j - coins[i-1]] +
                            dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][sum];
    }


    public static void main(String[] args) {
        System.out.println(new CoinChange().coinChange(new int[] {1,3,4,5}, 4));
        System.out.println(new CoinChange().coinChange11(new int[] {1,3,4,5}, 4, 6));
    }
}
/*
1+1+1+1+1+1
 */