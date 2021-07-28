// 1. DP, Time: O(n) Space: O(n)
/*
dp[i] means, if we ignore before A[i],
what's the highest score that Alex can win over the Bob？


There are three option for Alice to choose.
Take A[i], win take - dp[i+1] (when calculating dp[i] for Alice, dp[i+1] should be viewed as the amount Bob wins over Alice)
Take A[i] + A[i+1], win take - dp[i+2]
Take A[i] + A[i+1] + A[i+2], win take - dp[i+3]
dp[i] equals the best outcome of these three solutions.
*/
class Solution {
    public String stoneGameIII(int[] A) {
        int n = A.length, dp[] = new int[n+1]; // n + 1, so dp[n] 0, start from n - 1 
        for (int i = n - 1; i >= 0; --i) {
            dp[i] = Integer.MIN_VALUE;
            int take = 0; 
            for (int k = 0; k < 3 && i + k < n; ++k) {
                take += A[i + k];
                dp[i] = Math.max(dp[i], take - dp[i + k + 1]);
            }
        }
        if (dp[0] > 0) return "Alice";
        if (dp[0] < 0) return "Bob";
        return "Tie";
    }
}


// 2. DP don't understand
/*
Step1. Calculate the suffix sum of stone values.
Step2. For i = n-1, n-2, …, 0, calculate dp[i]. We need to enumerate three possible scenarios which correspond to taking 1, 2, 3 stones at this round.
state transition: dp[i] = max(dp[i], suffixSum[i]-suffixSum[k+1] + suffixSum[k+1] - dp[k+1]) = max(dp[i], suffixSum[i] - dp[k+1])，
for k = i, i+1, i+2, where (suffixSum[k+1] - dp[k+1]) means the score one can get when he/she takes stones secondly at the position k+1.
Step3. Compare suffixSum[0] with dp[0]*2. (Alice score: dp[0], Bob score: suffixSum[0]-dp[0])
*/

class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] suffixSum = new int[n+1];
        int[] dp = new int[n+1];
        suffixSum[n] = 0;
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--)
            suffixSum[i] = suffixSum[i + 1] + stoneValue[i];
        for (int i = n-1; i >= 0; i--) {
            dp[i] = stoneValue[i] + suffixSum[i+1] - dp[i+1];
            for (int k = i+1; k < i+3 && k < n; k++) {
                dp[i] = Math.max(dp[i], suffixSum[i]-dp[k+1]);
            }
        }
        if (dp[0]*2 == suffixSum[0])
            return "Tie";
        else if (dp[0]*2 > suffixSum[0])
            return "Alice";
        else
            return "Bob";
    }
}
