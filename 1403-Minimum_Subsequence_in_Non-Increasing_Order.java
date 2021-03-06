
// Time O(nlogn), Space O(n)

class Solution {
    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        int sum_sub = 0;
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            res.add(nums[i]);
            sum_sub += nums[i];
            if (sum_sub > sum - sum_sub) {
                return res;
            }
        }return  new ArrayList<Integer>();
    }
}
