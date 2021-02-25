
// Time O(n), Space O(1)
class Solution {
    public int minStartValue(int[] nums) {
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int x : nums) {
            sum += x;
            min = Math.min(min, sum);
        }
        return Math.max(1, 1 - min);
    }
}

//2 min_sum=0
public int minStartValue(int[] nums) {
    int sum = 0, min_sum = 0;
    for (var n : nums) {
        sum += n;
        min_sum = Math.min(min_sum, sum);
    }
    return 1 - min_sum;
}
