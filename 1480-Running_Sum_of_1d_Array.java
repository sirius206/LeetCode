
//Time O(n), Space O(n)  
class Solution {
    public int[] runningSum(int[] nums) {
        if (nums.length == 0) return new int [] {nums[0]};
        int [] rs = new int[nums.length];
        rs[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            rs[i] = rs[i-1] + nums[i];
        }
        return rs;
    }
}
