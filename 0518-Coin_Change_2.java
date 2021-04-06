//1. DP Time:O(A*n) Space:O(A*n)
class Solution {
    public int change(int amount, int[] coins) {
        int len = coins.length;
        if (amount == 0) return 1;
        int[][] dp = new int[len + 1][amount + 1];
        for (int i = 0; i <= len; i++){
            dp[i][0] = 1;
        }
        for (int i = 1; i <= len; i++){
            for (int j = 1; j <= amount; j++){
                dp[i][j] = dp[i - 1][j];
                int val = coins[i - 1];
                if (val <= j){
                    dp[i][j] += dp[i][j - val];
                }
            }
        }
        return dp[len][amount];
    }
}

//2. better DP answer, Time:O(A*n) Space:O(A)
class Solution {
  public int change(int amount, int[] coins) {
    int[] dp = new int[amount + 1];
    dp[0] = 1;

    for (int coin : coins) {
      for (int x = coin; x < amount + 1; ++x) {
        dp[x] += dp[x - coin];
      }
    }
    return dp[amount];
  }
}
