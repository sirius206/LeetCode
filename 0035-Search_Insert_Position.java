// Binary search Time O(log n) Space O(1)
//the loop will be stopped at the moment when right < left and nums[right] < target < nums[left]. 
//Hence, the proper position to insert the target is at the index left.
class Solution {
    public int searchInsert(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (target == nums[mid]) return mid;
            if (target > nums[mid]){
                lo = mid + 1;
            }
            else {
                hi = mid - 1;
            }
        }
        return lo;
    }
}
