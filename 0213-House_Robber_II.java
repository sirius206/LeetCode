// DP Time:O(n) Space: O(n)
// the problem becomes to rob either House[1]-House[n-1] or House[2]-House[n], 
// depending on which choice offers more money. Now the problem has degenerated to the House Robber".
class Solution {
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[0];
        int [] nums1 = new int[len - 1];
        int [] nums2 = new int[len - 1];
        nums1[0] = nums[0];
        for (int i = 1; i < len - 1; i++){
            nums1[i] = nums[i];
            nums2[i - 1] = nums[i];
        }
        nums2[len - 2] = nums[len - 1];
        return Math.max(helper(nums1), helper(nums2));
    }
    
    private int helper(int[] nums){
        int len = nums.length;
        if (len == 0) return 0;
        int[] dp = new int[len];
        dp[0] = nums[0];
        if (len == 1) return dp[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++){
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[len -1];        
    }
}

//2. DP Time:O(n) Space: O(1)
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0)
            return 0;

        if (nums.length == 1)
            return nums[0];

        int max1 = rob_simple(nums, 0, nums.length - 2);
        int max2 = rob_simple(nums, 1, nums.length - 1);

        return Math.max(max1, max2);
    }

    public int rob_simple(int[] nums, int start, int end) {
        int t1 = 0;
        int t2 = 0;

        for (int i = start; i <= end; i++) {
            int temp = t1;
            int current = nums[i];
            t1 = Math.max(current + t2, t1);
            t2 = temp;
        }

        return t1;
    }
}
