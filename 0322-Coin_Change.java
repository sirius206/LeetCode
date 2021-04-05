// 1. DP Bottom up, Time:O(S*n) Space:O(S)
// dp[i] min number of coins achiving value of i
// initialize dp[0] = 0, dp[i] = -1
// for all v, if (v <= i && dp[i - v] != -1), dp[i] = 1 + min{dp[i - v]}
class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        int [] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i < amount + 1; i++){
            dp[i] = -1;
        }
        for (int i = 1; i < amount + 1; i++){
            int min = Integer.MAX_VALUE;
            for (int v : coins){
                if (v <= i && dp[i - v] != -1) {
                    min = Math.min(min, dp[i - v]);
                }
            }
            if (min != Integer.MAX_VALUE) dp[i] = min + 1; 
        }
        return dp[amount];
    }
}

//1b
public class Solution {
  public int coinChange(int[] coins, int amount) {
    int max = amount + 1;
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, max);
    dp[0] = 0;
    for (int i = 1; i <= amount; i++) {
      for (int j = 0; j < coins.length; j++) {
        if (coins[j] <= i) {
          dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
        }
      }
    }
    return dp[amount] > amount ? -1 : dp[amount];
  }
}

//2. DP Top down Time:O(S*n) Space:O(S)
public class Solution {

  public int coinChange(int[] coins, int amount) {
    if (amount < 1) return 0;
    return coinChange(coins, amount, new int[amount]);
  }

  private int coinChange(int[] coins, int rem, int[] count) {
    if (rem < 0) return -1;
    if (rem == 0) return 0;
    if (count[rem - 1] != 0) return count[rem - 1];
    int min = Integer.MAX_VALUE;
    for (int coin : coins) {
      int res = coinChange(coins, rem - coin, count);
      if (res >= 0 && res < min)
        min = 1 + res;
    }
    count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
    return count[rem - 1];
  }
}
