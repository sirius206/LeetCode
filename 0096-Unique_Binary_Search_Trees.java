//1.DP Time:O(n^2) Space:O(n)
//dp[i]表示the number of structurally unique BST's for number i
//每个数字轮流当root， 左边有j个小于root的数，右边i - 1 - j个数，dp[j]*dp[i - 1 - j]种组合
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++){
            for (int j = 0; j < i; j++){
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }
        return dp[n];
    }
}

//2. Math Time:O(n) Space:O(1)
class Solution {
  public int numTrees(int n) {
    // Note: we should use long here instead of int, otherwise overflow
    long C = 1;
    for (int i = 0; i < n; ++i) {
      C = C * 2 * (2 * i + 1) / (i + 2);
    }
    return (int) C;
  }
}
