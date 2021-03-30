//1. DP Time:O(n^2) Space:O(n)
//f[i] is LIS including nums[i], result is the max element of f
class Solution {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] f = new int[len];
        int max = 1;
        for (int i = 0; i < len; i++) {
            f[i] = 1;
            for (int j = 0; j < i; j++){
                if ((nums[i] > nums[j]) && (f[i] < f[j] + 1)){
                    f[i] = f[j] + 1;
                }
            }
            if (f[i] > max) max= f[i];
        }
        return max;
    }
}

//2. DP with Binary search Time:O(n log n) Space:O(n)
public class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }
}
