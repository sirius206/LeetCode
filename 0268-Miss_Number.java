// Time O(n), Space O(1)
class Solution {
    public int missingNumber(int[] nums) {
        int sum_actual = 0;
        int sum_expected = 0;
        for (int num : nums) sum_actual += num;
        for (int i = 0; i < nums.length + 1; i++) {
            sum_expected += i;
        }
        // or
        // sum = nums.length*(nums.length + 1)/2;
        return sum_expected - sum_actual;
    }
}
