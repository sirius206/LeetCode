//DP
//a[i]: max sum ending in element i
//Time O(n), Space O(n)
class Solution {
    public int maxSubArray(int[] nums) {
        int [] a = new int[nums.length];
        a[0] = nums[0];
        for (int i = 1; i < nums.length; i++){
            if (a[i-1] < 0) {
                a[i] = nums[i];
            }
            else {
                a[i] = nums[i] + a[i-1];
            }
        }
        int max = a[0];
        for (int i = 1; i < nums.length; i++){
            if (a[i] > max){
                max = a[i];
            }
        }
        return max;
    }
}
