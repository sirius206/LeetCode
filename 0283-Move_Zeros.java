//Solution 1:
// If the current element is not 0, then we need to append it just in front of last non 0 element we found. 
class Solution {
    public void moveZeroes(int[] nums) {
        int lastNonZeroFoundAt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[lastNonZeroFoundAt] = nums[i];
                lastNonZeroFoundAt++;
            }
        }
        for (int i = lastNonZeroFoundAt; i < nums.length; i++) {
            nums[i] = 0;
        }

    }
}

//Solution 2: swap
class Solution {
    public void moveZeroes(int[] nums) {
        int left= 0;
        int right = 0;
        int temp = 0;
        while (right < nums.length) {
            if (nums[right] != 0) { 
                temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            ++right;
        }

    }
}
