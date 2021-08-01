//从右向左搜索到i，找到dip， 就是前面一个比现在小的位置， 然后从最右到i，找到刚好比i-1个数大的位置j， 交换i-1和j，
//从i到最右是递减的 把这个区间reverse
// Time O(n), Space O(1)
//be careful with duplicates like [2,3,1,3,3] and [2,1,2,2,2,2,2,1]
// mine, 
class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if (n <= 1) return;
        int left = 0;
        int right = n - 1;
        for (int i = n - 1; i > 0; i--){
            if (nums[i - 1] < nums[i]){
            // search the element just larger than nums[i-1]
            // important: search from right to left to avoid duplicates, use <= not <
                int j = n - 1;
                while (nums[j] <= nums[i - 1]){
                    j--;
                }
                swap(i - 1, j, nums);
                left = i;
                break;
            }
        }
        while (left < right){
            swap(left, right, nums);
            left++;
            right--;
        }         
    }
    public void swap(int i, int j, int[] nums){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

//2. answer, faster
public class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

//3. brutal force Time O(n!)
