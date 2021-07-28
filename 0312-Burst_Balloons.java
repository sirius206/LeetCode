
// Time O(n^3) There are O(N^2) states. For each state, determining the maximum coins requires iterating over all balloons in the range [left, right]
// Space O(n^2)

//1.Bottom up DP, better
/*
Handle the special cases (all numbers are the same) if you want.

Add one balloon at the start of nums and one at the end to handle edge cases.

Define an array dp, where dp[left][right] represents the maximum coins obtainable, if we burst all balloons on the interval [left, right], inclusively.

Iterate over the dp array such that dp[left][i - 1] and dp[i + 1][right] are visited before dp[left][right] is visited. For dp[left][right]:

We iterate over every index i in the range [left, right], and mark it as the last burst balloon.

First, we burst all balloons except the ith balloon. What we gain is:

dp[left][i - 1] + dp[i + 1][right]
Then, we burst the ith balloon and gain:

nums[left - 1] * nums[i] * nums[right + 1]
Let dp[left][right] be the maximum sum of these two values among all possible is.

Finally, return dp[1][len(nums) - 2].

Note: Do not return dp[0][len(nums) - 1] because the first and the last balloons were added by us and we cannot be popped.

*/
class Solution {
    public int maxCoins(int[] nums) {
        // add 1 before and after nums
        int n = nums.length + 2;
        int[] newNums = new int[n];
        System.arraycopy(nums, 0, newNums, 1, n - 2);
        newNums[0] = 1;
        newNums[n - 1] = 1;
        // dp[i][j] represents
        // maximum if we burst all nums[left]...nums[right], inclusive
        int[][] dp = new int[n][n];
        // do not include the first one and the last one
        // since they are both fake balloons added by ourselves and we can not burst
        // them
        for (int left = n - 2; left >= 1; left--) {
            for (int right = left; right <= n - 2; right++) {
                // find the last burst one in newNums[left]...newNums[right]
                for (int i = left; i <= right; i++) {
                    // newNums[i] is the last burst one
                    int gain = newNums[left - 1] * newNums[i] * newNums[right + 1];
                    // recursively call left side and right side
                    int remaining = dp[left][i - 1] + dp[i + 1][right];
                    // update
                    dp[left][right] = Math.max(remaining + gain, dp[left][right]);
                }
            }
        }
        // burst newNums[1]...newNums[n-2], excluding the first one and the last one
        return dp[1][n - 2];
    }
}

// 2. Top down DP, (recursion with memo)
class Solution {
    public int maxCoins(int[] nums) {
        // add 1 before and after nums
        int n = nums.length + 2;
        int[] newNums = new int[n];
        System.arraycopy(nums, 0, newNums, 1, n - 2);
        newNums[0] = 1;
        newNums[n - 1] = 1;

        // cache the results of dp
        int[][] memo = new int[n][n];

        // we can not burst the first one and the last one
        // since they are both fake balloons added by ourselves
        return dp(memo, newNums, 1, n - 2);
    }

    public int dp(int[][] memo, int[] nums, int left, int right) {
        // return maximum if we burst all nums[left]...nums[right], inclusive
        if (right - left < 0) {
            return 0;
        }

        // we've already seen this, return from cache
        if (memo[left][right] > 0) {
            return memo[left][right];
        }

        // find the last burst one in nums[left]...nums[right]
        int result = 0;
        for (int i = left; i <= right; i++) {
            // nums[i] is the last burst one
            int gain = nums[left - 1] * nums[i] * nums[right + 1];
            // nums[i] is fixed, recursively call left side and right side
            int remaining = dp(memo, nums, left, i - 1) + dp(memo, nums, i + 1, right);
            result = Math.max(result, remaining + gain);
        }
        // add to the cache
        memo[left][right] = result;
        return result;
    }
}
