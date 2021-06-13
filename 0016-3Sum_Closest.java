// Two pointers , Time O(N^2) Space: O(n) or O(logn) depending on sorting

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++){
            int lo = i + 1;
            int hi = len - 1;
            while (lo < hi){
                int sum = nums[i] + nums[lo] + nums[hi];
                if (Math.abs(diff) > Math.abs(target - sum)){
                    diff = target - sum;
                }
                if (sum > target) {
                    hi--;
                }
                else if (sum < target){
                    lo++;
                }
                else{
                    return sum;
                }
            }
        }
        return target - diff;
    }
}
