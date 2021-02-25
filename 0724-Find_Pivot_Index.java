
//Time O(n), Space O(n)
class Solution {
    public int pivotIndex(int[] nums) {
        int [] left = new int[nums.length];
        int [] right = new int[nums.length];
        left[0] = 0;
        right[nums.length - 1] = 0;
        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i-1] + nums[i-1];
            right[nums.length - i - 1] = right[nums.length - i] + nums[nums.length - i];
        }
        for (int i = 0; i < nums.length; i++) {
            if (left[i] == right[i]) {
                return i;
            }
        }
        return -1;
    }
}

////Time O(n), Space O(1)
class Solution {
    public int pivotIndex(int[] nums) {
        int sum = 0, leftsum = 0;
        for (int x: nums) sum += x;
        for (int i = 0; i < nums.length; ++i) {
            if (leftsum == sum - leftsum - nums[i]) return i;
            leftsum += nums[i];
        }
        return -1;
    }
}
